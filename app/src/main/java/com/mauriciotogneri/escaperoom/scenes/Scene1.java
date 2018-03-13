package com.mauriciotogneri.escaperoom.scenes;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

public class Scene1 extends BaseFragment
{
    @Override
    protected void initialize()
    {
        InteractiveObject pad = new InteractiveObject(getContext());
        pad.init(R.drawable.ic_scene2_pad, this::openSecondScene);
        add(pad, 13, 48);
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scene1;
    }
}