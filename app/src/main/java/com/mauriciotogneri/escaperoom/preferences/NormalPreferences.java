package com.mauriciotogneri.escaperoom.preferences;

import android.content.Context;
import android.preference.PreferenceManager;

import com.mauriciotogneri.androidutils.Preferences;

public class NormalPreferences extends Preferences
{
    public NormalPreferences(Context context)
    {
        super(PreferenceManager.getDefaultSharedPreferences(context));
    }
}