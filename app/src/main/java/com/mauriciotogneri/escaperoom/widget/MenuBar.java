package com.mauriciotogneri.escaperoom.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION_CODES;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.state.BaseSceneState;

import java.util.ArrayList;
import java.util.List;

public class MenuBar extends LinearLayout
{
    private final List<ImageView> items = new ArrayList<>();

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

    public void init(OnMenu onMenu, BaseSceneState state)
    {
        findViewById(R.id.menu).setOnClickListener(view -> onMenu.onMenu());
        update(state);
    }

    public void update(BaseSceneState state)
    {
        LinearLayout container = findViewById(R.id.items);
        container.removeAllViews();

        items.clear();

        if (state.hasKey())
        {
            ImageView item = item(R.drawable.scene1c_key);
            item.setOnClickListener(view -> selectItem(item));

            container.addView(item);
        }
    }

    private ImageView item(@DrawableRes int resId)
    {
        ImageView item = (ImageView) ImageView.inflate(getContext(), R.layout.view_menu_item, null);
        item.setImageResource(resId);

        return item;
    }

    private void selectItem(ImageView selectedItem)
    {
        for (ImageView item : items)
        {
            if (item != selectedItem)
            {
                item.setSelected(false);
            }
        }

        selectedItem.setSelected(!selectedItem.isSelected());
    }

    public static MenuBar create(ViewGroup root)
    {
        MenuBar menuBar = (MenuBar) View.inflate(root.getContext(), R.layout.view_menubar, null);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(root.getMeasuredWidth() / 12, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        root.addView(menuBar, layoutParams);

        return menuBar;
    }

    public interface OnMenu
    {
        void onMenu();
    }
}