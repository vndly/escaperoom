package com.mauriciotogneri.escaperoom.scenes;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.audio.AudioManager;
import com.mauriciotogneri.escaperoom.widget.InteractiveImage;
import com.mauriciotogneri.escaperoom.widget.InteractiveImageText;

public class Scene2 extends BaseFragment
{
    @Override
    protected void initialize()
    {
        addObject(InteractiveImage.fromWidget(getContext(), R.layout.widget_button_back), 2, 5, this::openFirstScene);

        InteractiveImageText b1 = InteractiveImageText.fromWidget(getContext(), R.layout.widget_button_pad, "1");
        addObject(b1, 31, 20, () -> onButtonClick(1));

        InteractiveImageText b2 = InteractiveImageText.fromWidget(getContext(), R.layout.widget_button_pad, "2");
        addObject(b2, 44, 20, () -> onButtonClick(2));

        InteractiveImageText b3 = InteractiveImageText.fromWidget(getContext(), R.layout.widget_button_pad, "3");
        addObject(b3, 57, 20, () -> onButtonClick(3));

        InteractiveImageText b4 = InteractiveImageText.fromWidget(getContext(), R.layout.widget_button_pad, "4");
        addObject(b4, 31, 43, () -> onButtonClick(4));

        InteractiveImageText b5 = InteractiveImageText.fromWidget(getContext(), R.layout.widget_button_pad, "5");
        addObject(b5, 44, 43, () -> onButtonClick(5));

        InteractiveImageText b6 = InteractiveImageText.fromWidget(getContext(), R.layout.widget_button_pad, "6");
        addObject(b6, 57, 43, () -> onButtonClick(6));

        InteractiveImageText b7 = InteractiveImageText.fromWidget(getContext(), R.layout.widget_button_pad, "7");
        addObject(b7, 31, 66, () -> onButtonClick(7));

        InteractiveImageText b8 = InteractiveImageText.fromWidget(getContext(), R.layout.widget_button_pad, "8");
        addObject(b8, 44, 66, () -> onButtonClick(8));

        InteractiveImageText b9 = InteractiveImageText.fromWidget(getContext(), R.layout.widget_button_pad, "9");
        addObject(b9, 57, 66, () -> onButtonClick(9));
    }

    private void onButtonClick(int id)
    {
        AudioManager.getInstance().playSound("scene2/button.ogg");
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scene2;
    }
}