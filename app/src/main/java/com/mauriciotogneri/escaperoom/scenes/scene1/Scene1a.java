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
    private InteractiveObject door;
    private InteractiveObject code;
    private InteractiveObject pad;
    private InteractiveObject drawer;

    @Override
    protected void initialize(StateScene1 stateScene)
    {
        InteractiveObject interrupter = objectDrawable(R.drawable.scene1a_interrupter);
        interrupter.position(576, 432);
        interrupter.size(200, 200);
        interrupter.callback(this::toggleLight);
        add(interrupter);

        pad = objectDrawable(R.drawable.scene1a_pad_close);
        pad.position(192, 475);
        pad.size(100, 100);
        pad.callback(() -> openScene(new Scene1b()));
        add(pad);

        door = objectDrawable(R.drawable.scene1a_door_close);
        door.position(322, 270);
        door.size(295, 530);
        door.callback(this::openDoor);
        add(door);

        drawer = objectDrawable(R.drawable.scene1a_drawer_close);
        drawer.position(1240, 432);
        drawer.size(500, 500);
        drawer.callback(() -> openScene(new Scene1c()));
        add(drawer);

        code = objectDrawable(R.drawable.scene1a_code);
        code.position(1008, 194);
        code.size(400, 200);
        add(code);

        dark = objectLayout(R.layout.widget_interactive_object);
        dark.color(R.color.scene1_dark);
        add(dark);
    }

    @Override
    protected void onUpdate(StateScene1 state)
    {
        if (stateScene.isDoorOpen())
        {
            background(R.drawable.scene1a_background_open);
        }
        else
        {
            background(R.drawable.scene1a_background_close);
        }

        conditionalImage(pad, stateScene.isPadOpen(), R.drawable.scene1a_pad_open, R.drawable.scene1a_pad_close);
        conditionalImage(drawer, stateScene.isDrawerOpen(), R.drawable.scene1a_drawer_open, R.drawable.scene1a_drawer_close);
        conditionalImage(door, stateScene.isDoorOpen(), R.drawable.scene1a_door_open, R.drawable.scene1a_door_close);

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
    protected StateScene1 state()
    {
        return new StateScene1(getContext());
    }
}