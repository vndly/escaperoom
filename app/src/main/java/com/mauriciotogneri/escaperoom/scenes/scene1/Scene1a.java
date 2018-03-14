package com.mauriciotogneri.escaperoom.scenes.scene1;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.scenes.BaseFragment;
import com.mauriciotogneri.escaperoom.state.StateScene1;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

public class Scene1a extends BaseFragment<StateScene1>
{
    @Override
    protected void initialize(StateScene1 stateScene)
    {
        addObject(InteractiveObject.fromResource(getContext(), R.drawable.ic_scene1a_pad), 12, 48, this::openScene1b);
        addObject(InteractiveObject.fromResource(getContext(), R.drawable.ic_scene1a_switch), 90, 48, this::toggleLight);

        registerClick(18, 22, 36, 76, this::doorLocked);

        setup(stateScene);
    }

    private void toggleLight()
    {
        playSound("scene1/switch.ogg");

        stateScene.toggleLight();

        setup(stateScene);
    }

    private void setup(StateScene1 stateScene)
    {
        if (stateScene.isLightOn())
        {
            background(R.drawable.scene1a_background_on);
        }
        else
        {
            background(R.drawable.scene1a_background_off);
        }
    }

    private void doorLocked()
    {
        playSound("scene1/door_locked.ogg");
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scene1a;
    }

    @Override
    protected int id()
    {
        return 1;
    }
}