package com.mauriciotogneri.escaperoom.widget;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class SceneLayout extends RelativeLayout
{
    private Display display;
    private OnInitialized onInitialized = null;

    public static final double BASE_WIDTH = 1920;
    public static final double BASE_HEIGHT = 1080;
    public static final double BASE_RATIO = BASE_WIDTH / BASE_HEIGHT;

    public SceneLayout(Context context)
    {
        super(context);
        init();
    }

    public SceneLayout(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public SceneLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init()
    {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

        if (windowManager != null)
        {
            display = windowManager.getDefaultDisplay();
        }
    }

    public void onInitialized(OnInitialized onInitialized)
    {
        this.onInitialized = onInitialized;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Point size = new Point();
        display.getRealSize(size);

        double width = size.x;
        double height = size.y;

        if ((width != 0) && (height != 0))
        {
            double ratio = width / height;

            int finalWidth;
            int finalHeight;

            if (ratio > BASE_RATIO)
            {
                finalWidth = (int) (height * BASE_RATIO);
                finalHeight = (int) height;
            }
            else if (ratio < BASE_RATIO)
            {
                finalWidth = (int) width;
                finalHeight = (int) (width / BASE_RATIO);
            }
            else
            {
                finalWidth = (int) width;
                finalHeight = (int) height;
            }

            setMeasuredDimension(finalWidth, finalHeight);

            if (onInitialized != null)
            {
                onInitialized.onInitialized();
                onInitialized = null;
            }
        }
    }

    public interface OnInitialized
    {
        void onInitialized();
    }
}