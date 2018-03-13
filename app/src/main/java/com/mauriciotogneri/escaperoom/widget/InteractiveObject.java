package com.mauriciotogneri.escaperoom.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class InteractiveObject extends AppCompatImageView
{
    public InteractiveObject(Context context)
    {
        super(context);
    }

    public InteractiveObject(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public InteractiveObject(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void init(@DrawableRes int resId, OnClick onClick)
    {
        setImageResource(resId);
        setOnClickListener(view -> onClick.onClick());
    }

    public interface OnClick
    {
        void onClick();
    }
}