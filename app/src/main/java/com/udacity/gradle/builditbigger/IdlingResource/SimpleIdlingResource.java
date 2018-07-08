package com.udacity.gradle.builditbigger.IdlingResource;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;

public class SimpleIdlingResource implements IdlingResource {
    @Nullable private volatile ResourceCallback callback;
    private AtomicBoolean isIdleNow = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return this.isIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }

    public void setIsIdleNow(boolean b) {
        AtomicBoolean isIdleNow = new AtomicBoolean(b);
        this.isIdleNow = isIdleNow;
    }
}
