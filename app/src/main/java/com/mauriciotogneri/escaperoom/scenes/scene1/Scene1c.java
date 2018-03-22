package com.mauriciotogneri.escaperoom.scenes.scene1;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.audio.Sound.Scene1;
import com.mauriciotogneri.escaperoom.scenes.BaseFragment;
import com.mauriciotogneri.escaperoom.state.StateScene1;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

public class Scene1c extends BaseFragment<StateScene1>
{
    private InteractiveObject dark;
    private InteractiveObject chestClose;
    private InteractiveObject chestOpen;
    private InteractiveObject key;

    @Override
    protected void initialize(StateScene1 stateScene)
    {
        background(R.drawable.scene1c_background);

        InteractiveObject back = objectLayout(R.layout.widget_button_back_left);
        back.position(38, 43);
        back.size(100, 100);
        back.callback(() -> openScene(new Scene1a()));
        add(back);

        chestClose = objectDrawable(R.drawable.scene1c_chest_drawers_close);
        chestClose.position(537, 302);
        chestClose.size(800, 300);
        chestClose.callback(this::openChest);
        add(chestClose);

        chestOpen = objectDrawable(R.drawable.scene1c_chest_drawers_open);
        chestOpen.position(345, 259);
        chestOpen.size(1000, 600);
        chestOpen.callback(this::openChest);
        add(chestOpen);

        key = objectDrawable(R.drawable.scene1c_key);
        key.position(768, 356);
        key.size(200, 200);
        key.callback(this::getKey);
        add(key);

        dark = objectLayout(R.layout.widget_scene1_dark);
        add(dark);
    }

    @Override
    protected void onUpdate(StateScene1 state)
    {
        if (stateScene.isLightOn())
        {
            gone(dark);
        }
        else
        {
            visible(dark);
        }

        if (stateScene.isChestOpen())
        {
            gone(chestClose);
            visible(chestOpen);
        }
        else
        {
            visible(chestClose);
            gone(chestOpen);
        }

        if (stateScene.isChestOpen() && !stateScene.hasKey())
        {
            visible(key);
        }
        else
        {
            gone(key);
        }
    }

    private void openChest()
    {
        if (!stateScene.isChestOpen())
        {
            playSound(Scene1.DRAWER);
            stateScene.openChest();
            update();
        }
    }

    private void getKey()
    {
        if (!stateScene.hasKey())
        {
            playSound(Scene1.KEY);
            stateScene.getKey();
            update();
        }
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scene;
    }

    @Override
    protected int id()
    {
        return 1;
    }
}