package com.mauriciotogneri.escaperoom.app;

import android.annotation.SuppressLint;
import android.app.Application;

import com.mauriciotogneri.escaperoom.audio.AudioManager;

public class EscapeRoom extends Application
{
    @SuppressLint("StaticFieldLeak")
    private static AudioManager audioManager;

    @Override
    public void onCreate()
    {
        super.onCreate();

        audioManager = new AudioManager(this);
    }

    public static AudioManager audioManager()
    {
        return audioManager;
    }
}