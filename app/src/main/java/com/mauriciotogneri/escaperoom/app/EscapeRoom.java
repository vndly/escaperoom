package com.mauriciotogneri.escaperoom.app;

import android.app.Application;

import com.mauriciotogneri.escaperoom.audio.AudioManager;
import com.mauriciotogneri.escaperoom.state.GameState;

public class EscapeRoom extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        AudioManager.initialize(this);
        GameState.initialize(this);
    }
}