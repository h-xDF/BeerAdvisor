package com.hfad.joke;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class DelayedMessageService extends IntentService {
    public static final String EXTRA_MESSAGE = "message";
    private static final String LOG_TAG = "DelayedMessageService";

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String text = intent.getStringExtra(EXTRA_MESSAGE);
            showText(text);
        }
    }

    private void showText(final String text) {
        Log.v(LOG_TAG, "The message is: " + text);
    }
}
