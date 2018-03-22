package com.mauriciotogneri.escaperoom.scenes.scene1;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.audio.Sound;
import com.mauriciotogneri.escaperoom.scenes.BaseFragment;
import com.mauriciotogneri.escaperoom.scenes.scene2.Scene2a;
import com.mauriciotogneri.escaperoom.state.StateScene1;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

public class Scene1a extends BaseFragment<StateScene1>
{
    private InteractiveObject dark;
    private InteractiveObject code;
    private InteractiveObject pad;
    private InteractiveObject chestDrawersClose;
    private InteractiveObject chestDrawersOpen;

    @Override
    protected void initialize(StateScene1 stateScene)
    {
        pad = objectDrawable(R.drawable.scene1a_pad_close);
        pad.position(192, 475);
        pad.size(100, 100);
        pad.callback(() -> openScene(new Scene1b()));
        add(pad);

        InteractiveObject interrupter = objectDrawable(R.drawable.scene1a_interrupter);
        interrupter.position(576, 432);
        interrupter.size(200, 200);
        interrupter.callback(this::toggleLight);
        add(interrupter);

        chestDrawersClose = objectDrawable(R.drawable.scene1a_chest_drawers_close);
        chestDrawersClose.position(1240, 432);
        chestDrawersClose.size(500, 500);
        chestDrawersClose.callback(() -> openScene(new Scene1c()));
        add(chestDrawersClose);

        chestDrawersOpen = objectDrawable(R.drawable.scene1a_chest_drawers_open);
        chestDrawersOpen.position(1240, 432);
        chestDrawersOpen.size(500, 500);
        chestDrawersOpen.callback(() -> openScene(new Scene1c()));
        add(chestDrawersOpen);

        code = objectDrawable(R.drawable.scene1a_code);
        code.position(1008, 194);
        code.size(400, 200);
        add(code);

        dark = objectLayout(R.layout.widget_interactive_object);
        dark.color(R.color.scene1_dark);
        add(dark);

        registerClick(320, 254, 602, 782, this::openDoor);
    }

    @Override
    protected void onUpdate(StateScene1 state)
    {
        if (stateScene.isPadOpen())
        {
            pad.setImageResource(R.drawable.scene1a_pad_open);
        }
        else
        {
            pad.setImageResource(R.drawable.scene1a_pad_close);
        }

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

        if (stateScene.isDoorOpen())
        {
            background(R.drawable.scene1a_background_open);
        }
        else
        {
            background(R.drawable.scene1a_background_close);
        }

        if (stateScene.isLightOn())
        {
            gone(code);
            gone(dark);
        }
        else
        {
            visible(code);
            visible(dark);
        }
    }

    private void toggleLight()
    {
        playSound(Sound.Scene1.SWITCH);
        stateScene.toggleLight();
        update();
    }

    private void openDoor()
    {
        if (stateScene.isDoorOpen())
        {
            openScene(new Scene2a());
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