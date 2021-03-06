package com.mauriciotogneri.escaperoom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.scenes.scene1.Scene1a;

public class GameActivity extends BaseActivity
{
    private static final int PAUSE_REQUEST_CODE = 1001;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen_game);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        openScene(new Scene1a());
    }

    public void openMenu()
    {
        Intent intent = new Intent(getApplicationContext(), PauseActivity.class);
        startActivityForResult(intent, PAUSE_REQUEST_CODE);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void openScene(Fragment fragment)
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