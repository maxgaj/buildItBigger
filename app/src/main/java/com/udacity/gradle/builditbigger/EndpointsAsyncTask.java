package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.IdlingResource.SimpleIdlingResource;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.maxgaj.builditbigger.jokesdisplaylib.DisplayActivity;

import java.io.IOException;

// https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/77e9910911d5412e5efede5fa681ec105a0f02ad/HelloEndpoints#2-connecting-your-android-app-to-the-backend
public class EndpointsAsyncTask extends AsyncTask<Pair<EndpointsAsyncTask.EndpointCallback, SimpleIdlingResource>, Void, String> {
    private static MyApi myApiService = null;
    private EndpointCallback callback;
    private SimpleIdlingResource idlingResource;

    @Override
    protected String doInBackground(Pair<EndpointCallback, SimpleIdlingResource>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")

                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        callback = params[0].first;
        idlingResource = params[0].second;

        if (idlingResource != null){
            idlingResource.setIsIdleNow(false);
        }


        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result == null)
            result = "";
        if (callback != null) {
            callback.onDone(result);
            if (idlingResource != null) {
                idlingResource.setIsIdleNow(true);
            }
        }
    }

    interface EndpointCallback {
        void onDone(String result);
    }
}
