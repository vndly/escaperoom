package com.mauriciotogneri.escaperoom.scenes.scene1;

import android.view.View;

import com.mauriciotogneri.escaperoom.R;
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
        chestClose.position(10, 20);
        chestClose.size(2000, 2000);
        chestClose.callback(this::openChest);
        add(chestClose);

        dark = objectLayout(R.layout.widget_scene1_dark);
        add(dark);

        setup(stateScene);
    }

    private void openChest()
    {

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