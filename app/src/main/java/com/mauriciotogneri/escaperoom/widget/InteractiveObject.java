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
    private float x = 0;
    private float y = 0;
    private int width = 0;
    private int height = 0;

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

    public void position(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void size(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void callback(OnClick onClick)
    {
        setOnClickListener(view -> onClick.onClick());
    }

    public void addTo(ViewGroup canvas, double defaultRatio)
    {
        double canvasWidth = canvas.getLayoutParams().width;
        double canvasHeight = canvas.getLayoutParams().height;
        double ratio = defaultRatio / (canvasWidth / canvasHeight);

        int x = (int) (canvasWidth * (this.x / 100f));
        int y = (int) (canvasHeight * (this.y / 100f));

        int width = (this.width != 0) ? (int) (this.width * ratio) : (int) canvasWidth;
        int height = (this.height != 0) ? (int) (this.height * ratio) : (int) canvasHeight;

        LayoutParams layoutParams = new LayoutParams(width, height);
        layoutParams.setMargins(x, y, 0, 0);

        canvas.addView(this, layoutParams);
    }

    public static InteractiveObject fromLayout(Context context, @LayoutRes int resId)
    {
        return (InteractiveObject) LayoutInflater.from(context).inflate(resId, null);
    }

    public static InteractiveObject fromResource(Context context, @DrawableRes int resId)
    {
        InteractiveObject object = fromLayout(context, R.layout.widget_interactive_object);
        object.image(resId);

        return object;
    }

    public interface OnClick
    {
        void onClick();
    }
}