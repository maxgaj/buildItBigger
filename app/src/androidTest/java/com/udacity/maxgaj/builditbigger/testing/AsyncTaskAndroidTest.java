package com.udacity.maxgaj.builditbigger.testing;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class AsyncTaskAndroidTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void registerIdlingResource(){
        this.idlingResource = activityRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(this.idlingResource);
    }

    @Test
    public void testAsyncTaskNotEmptyResult() {
        // if asynctask return null
        // tv_joke will display an empty string
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.tv_joke)).check(matches(not(withText(""))));

    }

    @After
    public void unregisterIdlingResource(){
        if (this.idlingResource != null){
            IdlingRegistry.getInstance().unregister(this.idlingResource);
        }
    }
}
