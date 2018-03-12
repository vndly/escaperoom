package com.mauriciotogneri.escaperoom.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.scenes.FirstScene;
import com.mauriciotogneri.escaperoom.scenes.SecondScene;

public class GameActivity extends BaseActivity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen_game);

        findViewById(R.id.button_menu).setOnClickListener(view -> finish());

        openFirstScene();
    }

    public void openFirstScene()
    {
        openScene(new FirstScene());
    }

    public void openSecondScene()
    {
        openScene(new SecondScene());
    }

    private void openScene(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_scene, fragment);
        transaction.commit();
    }
}