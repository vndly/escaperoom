package com.mauriciotogneri.escaperoom.state;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.mauriciotogneri.androidutils.Preferences;
import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.preferences.NormalPreferences;

import java.util.ArrayList;
import java.util.List;

public class BaseSceneState
{
    private final Context context;
    private ImageView selectedItem;
    protected Preferences preferences;

    private boolean hasKey;

    private static final String FIELD_HAS_KEY = "has.key";

    public BaseSceneState(Context context)
    {
        this.context = context;
        this.preferences = new NormalPreferences(context);

        this.hasKey = preferences.load(FIELD_HAS_KEY, false);
    }

    public void reset()
    {
        preferences.clear();
    }

    public boolean hasKey()
    {
        return hasKey;
    }

    public boolean isKeySelected()
    {
        return (selectedItem != null) && selectedItem.getTag().toString().equals(FIELD_HAS_KEY);
    }

    public void collectKey()
    {
        preferences.save(FIELD_HAS_KEY, hasKey = true);
    }

    public void useKey()
    {
        preferences.save(FIELD_HAS_KEY, hasKey = false);
    }

    public List<ImageView> items()
    {
        List<ImageView> items = new ArrayList<>();

        if (hasKey())
        {
            ImageView item = item(R.drawable.scene1c_key);
            item.setOnClickListener(view -> selectItem(item, items));
            item.setTag(FIELD_HAS_KEY);
            items.add(item);
        }

        return items;
    }

    private ImageView item(@DrawableRes int resId)
    {
        ImageView item = (ImageView) ImageView.inflate(context, R.layout.view_menu_item, null);
        item.setImageResource(resId);

        return item;
    }

    private void selectItem(ImageView currentItem, List<ImageView> items)
    {
        for (ImageView item : items)
        {
            if (item != currentItem)
            {
                item.setSelected(false);
            }
        }

        currentItem.setSelected(!currentItem.isSelected());
        selectedItem = currentItem.isSelected() ? currentItem : null;
    }
}