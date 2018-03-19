package com.mauriciotogneri.escaperoom.scenes.scene1;

import android.view.View;

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

    @Override
    protected void initialize(StateScene1 stateScene)
    {
        background(R.drawable.scene1c_background);

        InteractiveObject back = objectLayout(R.layout.widget_button_back_left);
        back.position(2, 4);
        back.size(100, 100);
        back.callback(this::openScene1a);
        add(back);

        chestClose = objectDrawable(R.drawable.scene1c_chest_drawers_close);
        chestClose.position(28, 28);
        chestClose.size(800, 300);
        chestClose.callback(this::openChest);
        add(chestClose);

        chestOpen = objectDrawable(R.drawable.scene1c_chest_drawers_open);
        chestOpen.position(18, 24);
        chestOpen.size(1000, 600);
        chestOpen.callback(this::openChest);
        add(chestOpen);

        dark = objectLayout(R.layout.widget_scene1_dark);
        add(dark);

        setup(stateScene);
    }

    private void openChest()
    {
        if (!stateScene.isChestOpen())
        {
            playSound(Scene1.DRAWER);
            stateScene.openChest();
            setup(stateScene);
        }
    }

    private void setup(StateScene1 stateScene)
    {
        if (stateScene.isLightOn())
        {
            dark.setVisibility(View.GONE);
        }
        else
        {
            dark.setVisibility(View.VISIBLE);
        }

        if (stateScene.isChestOpen())
        {
            chestClose.setVisibility(View.GONE);
            chestOpen.setVisibility(View.VISIBLE);
        }
        else
        {
            chestClose.setVisibility(View.VISIBLE);
            chestOpen.setVisibility(View.GONE);
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