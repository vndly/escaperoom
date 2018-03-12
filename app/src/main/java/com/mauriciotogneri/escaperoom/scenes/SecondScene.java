package com.mauriciotogneri.escaperoom.scenes;

import com.mauriciotogneri.escaperoom.R;

public class SecondScene extends BaseFragment
{
    @Override
    protected void initialize()
    {
        view.findViewById(R.id.button_back).setOnClickListener(view1 -> openFirstScene());

        view.findViewById(R.id.button_1).setOnClickListener(view1 -> {});
        view.findViewById(R.id.button_2).setOnClickListener(view1 -> {});
        view.findViewById(R.id.button_3).setOnClickListener(view1 -> {});
        view.findViewById(R.id.button_4).setOnClickListener(view1 -> {});
        view.findViewById(R.id.button_5).setOnClickListener(view1 -> {});
        view.findViewById(R.id.button_6).setOnClickListener(view1 -> {});
        view.findViewById(R.id.button_7).setOnClickListener(view1 -> {});
        view.findViewById(R.id.button_8).setOnClickListener(view1 -> {});
        view.findViewById(R.id.button_9).setOnClickListener(view1 -> {});
    }

    @Override
    protected int layout()
    {
        return R.layout.scene_second;
    }
}