package com.mauriciotogneri.escaperoom.scenes.scene1;

import android.support.annotation.DrawableRes;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.audio.Sound;
import com.mauriciotogneri.escaperoom.audio.Sound.Scene1;
import com.mauriciotogneri.escaperoom.scenes.BaseFragment;
import com.mauriciotogneri.escaperoom.state.StateScene1;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

import java.util.ArrayList;
import java.util.List;

public class Scene1b extends BaseFragment<StateScene1>
{
    private InteractiveObject dark;
    private final DigitsInput digitsInput = new DigitsInput();
    private final List<InteractiveObject> numbers = new ArrayList<>();

    @Override
    protected void initialize(StateScene1 stateScene)
    {
        InteractiveObject back = objectLayout(R.layout.widget_button_back_left);
        back.position(38, 43);
        back.size(100, 100);
        back.callback(this::openScene1a);
        add(back);

        numbers.add(addButton(1, 595, 216, R.drawable.scene1b_pad_number1));
        numbers.add(addButton(2, 845, 216, R.drawable.scene1b_pad_number2));
        numbers.add(addButton(3, 1094, 216, R.drawable.scene1b_pad_number3));

        numbers.add(addButton(4, 595, 464, R.drawable.scene1b_pad_number4));
        numbers.add(addButton(5, 845, 464, R.drawable.scene1b_pad_number5));
        numbers.add(addButton(6, 1094, 464, R.drawable.scene1b_pad_number6));

        numbers.add(addButton(7, 595, 712, R.drawable.scene1b_pad_number7));
        numbers.add(addButton(8, 845, 712, R.drawable.scene1b_pad_number8));
        numbers.add(addButton(9, 1094, 712, R.drawable.scene1b_pad_number9));

        registerClick(562, 175, 1360, 974, this::openPad);

        dark = objectLayout(R.layout.widget_scene1_dark);
        add(dark);
    }

    @Override
    protected void onUpdate(StateScene1 state)
    {
        if (stateScene.isPadOpen())
        {
            for (InteractiveObject button : numbers)
            {
                visible(button);
            }

            background(R.drawable.scene1b_background_open);
        }
        else
        {
            for (InteractiveObject button : numbers)
            {
                gone(button);
            }

            background(R.drawable.scene1b_background_close);
        }

        if (stateScene.isLightOn())
        {
            gone(dark);
        }
        else
        {
            visible(dark);
        }
    }

    private InteractiveObject addButton(int digit, int x, int y, @DrawableRes int imageId)
    {
        InteractiveObject button = objectDrawable(imageId);
        button.position(x, y);
        button.size(230, 230);
        button.callback(() -> onButtonClick(digit));
        add(button);

        return button;
    }

    private void openPad()
    {
        if (stateScene.hasKey())
        {
            stateScene.openPad();
            update();
        }
        else if (!stateScene.isPadOpen())
        {
            playSound(Scene1.PAD_LOCKED);
        }
    }

    private void onButtonClick(int digit)
    {
        if (digitsInput.add(digit))
        {
            stateScene.openDoor();

            playSound(Sound.Scene1.UNLOCKED);
        }
        else
        {
            playSound(Sound.Scene1.DIAL);
        }
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scene;
    }

    @Override
    protected int id()
    {
        return 1;
    }

    private static class DigitsInput
    {
        private final int[] input;
        private int index = 0;

        private DigitsInput()
        {
            this.input = new int[4];
        }

        public boolean add(int digit)
        {
            input[index++] = digit;

            if (index >= input.length)
            {
                index = 0;
            }

            return ((input[0] == 6) && (input[1] == 3) && (input[2] == 5) && (input[3] == 7)) ||
                    ((input[0] == 7) && (input[1] == 6) && (input[2] == 3) && (input[3] == 5)) ||
                    ((input[0] == 5) && (input[1] == 7) && (input[2] == 6) && (input[3] == 3)) ||
                    ((input[0] == 3) && (input[1] == 5) && (input[2] == 7) && (input[3] == 6));
        }
    }
}