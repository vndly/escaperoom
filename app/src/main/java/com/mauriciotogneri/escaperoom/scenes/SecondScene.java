package com.mauriciotogneri.escaperoom.scenes;

import com.mauriciotogneri.escaperoom.R;

public class SecondScene extends BaseFragment
{
    @Override
    protected void initialize()
    {
        view.findViewById(R.id.button_back).setOnClickListener(view1 -> openFirstScene());
    }

    @Override
    protected int layout()
    {
        return R.layout.scene_second;
    }
}