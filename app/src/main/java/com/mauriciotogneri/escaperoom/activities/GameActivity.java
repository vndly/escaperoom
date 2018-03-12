package com.mauriciotogneri.escaperoom.activities;

import android.os.Bundle;

import com.mauriciotogneri.escaperoom.R;

public class GameActivity extends BaseActivity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen_game);

        findViewById(R.id.button_menu).setOnClickListener(view -> finish());
    }
}