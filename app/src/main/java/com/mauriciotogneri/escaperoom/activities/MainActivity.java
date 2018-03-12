package com.mauriciotogneri.escaperoom.activities;

import android.content.Intent;
import android.os.Bundle;

import com.mauriciotogneri.escaperoom.R;

public class MainActivity extends BaseActivity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen_main);

        findViewById(R.id.button_start).setOnClickListener(view ->
        {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.button_exit).setOnClickListener(view ->finish());
    }
}