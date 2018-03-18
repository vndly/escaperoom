package com.mauriciotogneri.escaperoom.scenes.scene1;

import android.view.View;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.audio.Sound;
import com.mauriciotogneri.escaperoom.scenes.BaseFragment;
import com.mauriciotogneri.escaperoom.state.StateScene1;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

public class Scene1a extends BaseFragment<StateScene1>
{
    private InteractiveObject dark;

    @Override
    protected void initialize(StateScene1 stateScene)
    {
        background(R.drawable.scene1a_background_on);

        InteractiveObject pad = objectDrawable(R.drawable.ic_scene1a_pad);
        pad.position(12, 48);
        pad.size(100, 100);
        pad.callback(this::openScene1b);
        add(pad);

        InteractiveObject interrupter = objectDrawable(R.drawable.ic_scene1a_switch);
        interrupter.position(90, 48);
        interrupter.size(50, 50);
        interrupter.callback(this::toggleLight);
        add(interrupter);

        dark = objectLayout(R.layout.widget_scene1_dark);
        add(dark);

        registerClick(18, 22, 36, 76, this::openDoor);

        setup(stateScene);
    }

    private void toggleLight()
    {
        playSound(Sound.Scene1.SWITCH);

        stateScene.toggleLight();

        setup(stateScene);
    }

    private void setup(StateScene1 stateScene)
    {
        if (stateScene.isLightOn())
        {
            dark.setVisibility(View.GONE);

            if (stateScene.isDoorOpen())
            {
                background(R.drawable.scene1a_background_door_open);
            }
            else
            {
                background(R.drawable.scene1a_background_on);
            }
        }
        else
        {
            dark.setVisibility(View.VISIBLE);
            background(R.drawable.scene1a_background_off);
        }
    }

    private void openDoor()
    {
        if (stateScene.isDoorOpen())
        {
            openScene2a();
        }
        else
        {
            playSound(Sound.Scene1.DOOR_LOCKED);
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