package com.mauriciotogneri.escaperoom.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mauriciotogneri.escaperoom.R;

public class InteractiveImageText extends RelativeLayout implements InteractiveObject
{
    private static final int PADDING = 50;

    public InteractiveImageText(Context context)
    {
        super(context);
    }

    public InteractiveImageText(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public InteractiveImageText(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void text(String text)
    {
        TextView textView = findViewById(R.id.text);
        textView.setText(text);
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

    public static InteractiveImageText fromWidget(Context context, @LayoutRes int resId, String text)
    {
        InteractiveImageText object = (InteractiveImageText) LayoutInflater.from(context).inflate(resId, null);
        object.text(text);

        return object;
    }
}