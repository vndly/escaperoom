package com.mauriciotogneri.escaperoom.activities;

import android.content.Intent;
import android.os.Bundle;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.state.BaseSceneState;

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
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        findViewById(R.id.button_exit).setOnClickListener(view -> finish());

        BaseSceneState state = new BaseSceneState(this);
        state.reset();
    }
}