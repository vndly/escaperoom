package com.mauriciotogneri.escaperoom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.scenes.scene1.Scene1a;
import com.mauriciotogneri.escaperoom.scenes.scene1.Scene1b;

public class GameActivity extends BaseActivity
{
    private static final int PAUSE_REQUEST_CODE = 1001;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen_game);

        findViewById(R.id.button_menu).setOnClickListener(view ->
        {
            Intent intent = new Intent(getApplicationContext(), PauseActivity.class);
            startActivityForResult(intent, PAUSE_REQUEST_CODE);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        openScene1a();
    }

    public void openScene1a()
    {
        openScene(new Scene1a());
    }

    public void openScene1b()
    {
        openScene(new Scene1b());
    }

    private void openScene(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_scene, fragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if ((requestCode == PAUSE_REQUEST_CODE) && (resultCode == RESULT_OK))
        {
            finish();
        }
    }
}