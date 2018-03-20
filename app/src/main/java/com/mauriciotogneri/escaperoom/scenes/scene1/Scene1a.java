package com.mauriciotogneri.escaperoom.scenes.scene1;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.audio.Sound;
import com.mauriciotogneri.escaperoom.scenes.BaseFragment;
import com.mauriciotogneri.escaperoom.state.StateScene1;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

public class Scene1a extends BaseFragment<StateScene1>
{
    private InteractiveObject dark;
    private InteractiveObject code;
    private InteractiveObject chestDrawersClose;
    private InteractiveObject chestDrawersOpen;

    @Override
    protected void initialize(StateScene1 stateScene)
    {
        background(R.drawable.scene1a_background);

        InteractiveObject pad = objectDrawable(R.drawable.scene1a_pad);
        pad.position(192, 475);
        pad.size(100, 100);
        pad.callback(this::openScene1b);
        add(pad);

        InteractiveObject interrupter = objectDrawable(R.drawable.scene1a_interrupter);
        interrupter.position(576, 432);
        interrupter.size(200, 200);
        interrupter.callback(this::toggleLight);
        add(interrupter);

        chestDrawersClose = objectDrawable(R.drawable.scene1a_chest_drawers_close);
        chestDrawersClose.position(1440, 432);
        chestDrawersClose.size(500, 500);
        chestDrawersClose.callback(this::openScene1c);
        add(chestDrawersClose);

        chestDrawersOpen = objectDrawable(R.drawable.scene1a_chest_drawers_open);
        chestDrawersOpen.position(1440, 432);
        chestDrawersOpen.size(500, 500);
        chestDrawersOpen.callback(this::openScene1c);
        add(chestDrawersOpen);

        code = objectDrawable(R.drawable.scene1a_code);
        code.position(1008, 194);
        code.size(400, 200);
        add(code);

        dark = objectLayout(R.layout.widget_scene1_dark);
        add(dark);

        registerClick(16, 24, 31, 72, this::openDoor);

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
        if (stateScene.isChestOpen())
        {
            gone(chestDrawersClose);
            visible(chestDrawersOpen);
        }
        else
        {
            visible(chestDrawersClose);
            gone(chestDrawersOpen);
        }

        if (stateScene.isLightOn())
        {
            gone(code);
            gone(dark);

            if (stateScene.isDoorOpen())
            {
                background(R.drawable.scene1a_background_door_open);
            }
            else
            {
                //
            }
        }
        else
        {
            visible(code);
            visible(dark);
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