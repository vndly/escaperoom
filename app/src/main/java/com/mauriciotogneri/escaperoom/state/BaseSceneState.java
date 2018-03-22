package com.mauriciotogneri.escaperoom.state;

import android.content.Context;

import com.mauriciotogneri.androidutils.Preferences;
import com.mauriciotogneri.escaperoom.preferences.NormalPreferences;

public class BaseSceneState
{
    protected Preferences preferences;

    private boolean hasKey;

    private static final String FIELD_HAS_KEY = "has.key";

    public BaseSceneState(Context context)
    {
        this.preferences = new NormalPreferences(context);

        this.hasKey = preferences.load(FIELD_HAS_KEY, false);
    }

    public void reset()
    {
        preferences.clear();
    }

    public boolean hasKey()
    {
        return hasKey;
    }

    public void getKey()
    {
        hasKey = true;

        preferences.save(FIELD_HAS_KEY, hasKey);
    }
}