package com.mauriciotogneri.escaperoom.scenes.scene1;

import android.view.View;

import com.mauriciotogneri.escaperoom.R;
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

        dark = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1_dark);
        addObject(dark, 0, 0);

        setup(stateScene);
    }

    private void onButtonClick(int digit)
    {
        if (digitsInput.add(digit))
        {
            stateScene.openDoor();
            stateScene.lightOn();

            playSound("scene1/unlocked.ogg");
        }
        else
        {
            playSound("scene1/dial.ogg");
        }
    }

    private void setup(StateScene1 stateScene)
    {
        if (stateScene.isLightOn())
        {
            dark.setVisibility(View.GONE);
        }
        else
        {
            dark.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scene1b;
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