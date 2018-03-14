package com.mauriciotogneri.escaperoom.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mauriciotogneri.escaperoom.R;

public class InteractiveObject extends RelativeLayout
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

    public void text(String text)
    {
        TextView textView = findViewById(R.id.text);
        textView.setText(text);
    }

    public void image(@DrawableRes int image)
    {
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageResource(image);
    }

    public void callback(OnClick onClick)
    {
        setOnClickListener(view -> onClick.onClick());
    }

    public void addTo(ViewGroup canvas, float x, float y)
    {
        float width = canvas.getWidth();
        float height = canvas.getHeight();

        setX(width * (x / 100f));
        setY(height * (y / 100f));

        canvas.addView(this);
    }

    public static InteractiveObject fromWidget(Context context, @LayoutRes int resId, String text)
    {
        InteractiveObject object = (InteractiveObject) LayoutInflater.from(context).inflate(resId, null);
        object.text(text);

        return object;
    }

    public static InteractiveObject fromWidget(Context context, @LayoutRes int resId)
    {
        return (InteractiveObject) LayoutInflater.from(context).inflate(resId, null);
    }

    public static InteractiveObject fromResource(Context context, @DrawableRes int resId)
    {
        InteractiveObject object = fromWidget(context, R.layout.widget_interactive_object);
        object.image(resId);

        return object;
    }

    public interface OnClick
    {
        void onClick();
    }
}