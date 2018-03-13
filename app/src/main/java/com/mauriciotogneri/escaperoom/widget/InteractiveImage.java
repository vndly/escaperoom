package com.mauriciotogneri.escaperoom.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class InteractiveImage extends AppCompatImageView implements InteractiveObject
{
    private static final int PADDING = 50;

    public InteractiveImage(Context context)
    {
        super(context);
    }

    public InteractiveImage(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public InteractiveImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void callback(OnClick onClick)
    {
        setOnClickListener(view -> onClick.onClick());
    }

    @Override
    public void addTo(ViewGroup canvas, float x, float y)
    {
        setPadding(PADDING, PADDING, PADDING, PADDING);

        float width = canvas.getWidth();
        float height = canvas.getHeight();

        setX(width * (x / 100f) - PADDING);
        setY(height * (y / 100f) - PADDING);

        canvas.addView(this);
    }

    public static InteractiveImage fromWidget(Context context, @LayoutRes int resId)
    {
        return (InteractiveImage) LayoutInflater.from(context).inflate(resId, null);
    }

    public static InteractiveImage fromResource(Context context, @DrawableRes int resId)
    {
        InteractiveImage object = new InteractiveImage(context);
        object.setImageResource(resId);

        return object;
    }
}