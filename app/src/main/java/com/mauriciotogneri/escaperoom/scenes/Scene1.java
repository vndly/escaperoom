package com.mauriciotogneri.escaperoom.scenes;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

public class Scene1 extends BaseFragment
{
    @Override
    protected void initialize()
    {
        addObject(InteractiveObject.fromResource(getContext(), R.drawable.ic_scene2_pad), 13, 48, this::openSecondScene);
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scene1;
    }
}