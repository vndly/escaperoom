package com.mauriciotogneri.escaperoom.scenes.scene1;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.audio.Sound;
import com.mauriciotogneri.escaperoom.scenes.BaseFragment;
import com.mauriciotogneri.escaperoom.state.StateScene1;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

public class Scene1b extends BaseFragment<StateScene1>
{
    private InteractiveObject dark;
    private final DigitsInput digitsInput = new DigitsInput();

    @Override
    protected void initialize(StateScene1 stateScene)
    {
        background(R.drawable.scene1b_background);

        InteractiveObject back = objectLayout(R.layout.widget_button_back_left);
        back.position(38, 43);
        back.size(100, 100);
        back.callback(this::openScene1a);
        add(back);

        addButton(1, 595, 216);
        addButton(2, 845, 216);
        addButton(3, 1094, 216);

        addButton(4, 595, 464);
        addButton(5, 845, 464);
        addButton(6, 1094, 464);

        addButton(7, 595, 712);
        addButton(8, 845, 712);
        addButton(9, 1094, 712);

        dark = objectLayout(R.layout.widget_scene1_dark);
        add(dark);

        setup(stateScene);
    }

    private void addButton(int digit, int x, int y)
    {
        InteractiveObject button = objectLayout(R.layout.widget_scene1b_button_pad);
        button.text(String.valueOf(digit));
        button.position(x, y);
        button.size(230, 230);
        button.callback(() -> onButtonClick(digit));
        add(button);
    }

    private void onButtonClick(int digit)
    {
        if (digitsInput.add(digit))
        {
            stateScene.openDoor();
            stateScene.lightOn();

            playSound(Sound.Scene1.UNLOCKED);
        }
        else
        {
            playSound(Sound.Scene1.DIAL);
        }
    }

    private void setup(StateScene1 stateScene)
    {
        if (stateScene.isLightOn())
        {
            gone(dark);
        }
        else
        {
            visible(dark);
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