package com.mauriciotogneri.escaperoom.activities;

import android.app.Activity;
import android.os.Bundle;

import com.mauriciotogneri.escaperoom.R;

public class PauseActivity extends BaseActivity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen_pause);

        findViewById(R.id.button_resume).setOnClickListener(view -> finish());
        findViewById(R.id.button_quit).setOnClickListener(view ->
        {
            setResult(Activity.RESULT_OK);
            finish();
        });
    }
}