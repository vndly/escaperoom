package com.mauriciotogneri.escaperoom.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION_CODES;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mauriciotogneri.escaperoom.R;

public class MenuBar extends LinearLayout
{
    public MenuBar(Context context)
    {
        super(context);
    }

    public MenuBar(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MenuBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    public MenuBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static MenuBar create(ViewGroup root)
    {
        MenuBar menuBar = (MenuBar) View.inflate(root.getContext(), R.layout.view_bag, null);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(root.getMeasuredWidth() / 12, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        root.addView(menuBar, layoutParams);

        return menuBar;
    }
}