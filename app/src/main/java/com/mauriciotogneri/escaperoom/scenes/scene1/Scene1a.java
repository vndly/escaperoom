package com.mauriciotogneri.escaperoom.scenes.scene1;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.scenes.BaseFragment;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

public class Scene1a extends BaseFragment
{
    @Override
    protected void initialize()
    {
        addObject(InteractiveObject.fromResource(getContext(), R.drawable.ic_scene1b_pad), 13, 48, this::openScene1b);
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scene1a;
    }
}