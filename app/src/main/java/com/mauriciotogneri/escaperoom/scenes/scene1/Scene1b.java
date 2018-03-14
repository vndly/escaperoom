package com.mauriciotogneri.escaperoom.scenes.scene1;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.audio.AudioManager;
import com.mauriciotogneri.escaperoom.scenes.BaseFragment;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

public class Scene1b extends BaseFragment
{
    @Override
    protected void initialize()
    {
        addObject(InteractiveObject.fromWidget(getContext(), R.layout.widget_button_back_left), 2, 5, this::openScene1a);

        InteractiveObject b1 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "1");
        addObject(b1, 31, 20, () -> onButtonClick(1));

        InteractiveObject b2 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "2");
        addObject(b2, 44, 20, () -> onButtonClick(2));

        InteractiveObject b3 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "3");
        addObject(b3, 57, 20, () -> onButtonClick(3));

        InteractiveObject b4 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "4");
        addObject(b4, 31, 43, () -> onButtonClick(4));

        InteractiveObject b5 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "5");
        addObject(b5, 44, 43, () -> onButtonClick(5));

        InteractiveObject b6 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "6");
        addObject(b6, 57, 43, () -> onButtonClick(6));

        InteractiveObject b7 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "7");
        addObject(b7, 31, 66, () -> onButtonClick(7));

        InteractiveObject b8 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "8");
        addObject(b8, 44, 66, () -> onButtonClick(8));

        InteractiveObject b9 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "9");
        addObject(b9, 57, 66, () -> onButtonClick(9));
    }

    private void onButtonClick(int id)
    {
        AudioManager.getInstance().playSound("scene1/dial.ogg");
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scene1b;
    }
}