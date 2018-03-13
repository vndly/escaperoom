package com.mauriciotogneri.escaperoom.widget;

import android.view.ViewGroup;

public interface InteractiveObject
{
    void callback(InteractiveObject.OnClick onClick);

    void addTo(ViewGroup canvas, float x, float y);

    interface OnClick
    {
        void onClick();
    }
}