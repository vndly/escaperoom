package com.mauriciotogneri.escaperoom.scenes;

import com.mauriciotogneri.escaperoom.R;

public class FirstScene extends BaseFragment
{
    @Override
    protected void initialize()
    {
        view.findViewById(R.id.open_second).setOnClickListener(view1 -> openSecondScene());
    }

    @Override
    protected int layout()
    {
        return R.layout.scene_first;
    }
}