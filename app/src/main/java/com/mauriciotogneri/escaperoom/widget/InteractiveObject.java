package com.mauriciotogneri.escaperoom.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class InteractiveObject extends AppCompatImageView
{
    private static final int PADDING = 50;

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

    public void callback(OnClick onClick)
    {
        setOnClickListener(view -> onClick.onClick());
    }

    public void addTo(ViewGroup canvas, float x, float y)
    {
        setPadding(PADDING, PADDING, PADDING, PADDING);

        float width = canvas.getWidth();
        float height = canvas.getHeight();

        setX(width * (x / 100f) - PADDING);
        setY(height * (y / 100f) - PADDING);

        canvas.addView(this);
    }

    public static InteractiveObject fromWidget(Context context, @LayoutRes int resId)
    {
        return (InteractiveObject) LayoutInflater.from(context).inflate(resId, null);
    }

    public static InteractiveObject fromResource(Context context, @DrawableRes int resId)
    {
        InteractiveObject object = new InteractiveObject(context);
        object.setImageResource(resId);

        return object;
    }

    public interface OnClick
    {
        void onClick();
    }
}