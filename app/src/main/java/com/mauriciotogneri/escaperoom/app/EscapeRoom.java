package com.mauriciotogneri.escaperoom.app;

import android.annotation.SuppressLint;
import android.app.Application;

import com.mauriciotogneri.escaperoom.audio.AudioManager;
import com.mauriciotogneri.escaperoom.state.GameState;

public class EscapeRoom extends Application
{
    @SuppressLint("StaticFieldLeak")
    private static AudioManager audioManager;

    @Override
    public void onCreate()
    {
        super.onCreate();

        audioManager = new AudioManager(this);
        GameState.initialize(this);
    }

    public static AudioManager audioManager()
    {
        return audioManager;
    }
}