package com.mauriciotogneri.escaperoom.scenes;

import com.mauriciotogneri.escaperoom.R;

public class FirstScene extends BaseFragment
{
    @Override
    protected void initialize()
    {
        registerClick(14, 48, this::openSecondScene);
    }

    @Override
    protected int layout()
    {
        return R.layout.scene_first;
    }
}