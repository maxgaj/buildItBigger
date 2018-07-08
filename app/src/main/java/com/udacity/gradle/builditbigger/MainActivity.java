package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udacity.gradle.builditbigger.IdlingResource.SimpleIdlingResource;
import com.udacity.maxgaj.builditbigger.jokesdisplaylib.DisplayActivity;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.EndpointCallback {

    @Nullable private SimpleIdlingResource idlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute(new Pair<EndpointsAsyncTask.EndpointCallback, SimpleIdlingResource>(this, this.idlingResource));

    }


    @Override
    public void onDone(String result) {
        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra("Joke", result);
        this.startActivity(intent);
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (this.idlingResource == null) {
            this.idlingResource = new SimpleIdlingResource();
        }
        return this.idlingResource;
    }
}
