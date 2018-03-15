package com.mauriciotogneri.escaperoom.scenes.scene2;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.scenes.BaseFragment;
import com.mauriciotogneri.escaperoom.state.StateScene2;

public class Scene2a extends BaseFragment<StateScene2>
{
    @Override
    protected void initialize(StateScene2 stateScene)
    {
        background(R.drawable.scene2a_background);
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scene;
    }

    @Override
    protected int id()
    {
        return 2;
    }
}