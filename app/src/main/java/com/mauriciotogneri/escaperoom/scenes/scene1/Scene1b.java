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
        background(R.drawable.scene1b_background);

        InteractiveObject back = objectLayout(R.layout.widget_button_back_left);
        back.position(2, 4);
        back.callback(this::openScene1a);
        add(back);

        InteractiveObject b1 = objectLayout(R.layout.widget_scene1b_button_pad);
        b1.text("1");
        b1.position(31, 18);
        b1.callback(() -> onButtonClick(1));
        add(b1);

        InteractiveObject b2 = objectLayout(R.layout.widget_scene1b_button_pad);
        b2.text("2");
        b2.position(44, 18);
        b2.callback(() -> onButtonClick(2));
        add(b2);

        InteractiveObject b3 = objectLayout(R.layout.widget_scene1b_button_pad);
        b3.text("3");
        b3.position(57, 18);
        b3.callback(() -> onButtonClick(3));
        add(b3);

        /*InteractiveObject b4 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "4");
        addObject(b4, 31, 39, () -> onButtonClick(4));

        InteractiveObject b5 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "5");
        addObject(b5, 44, 39, () -> onButtonClick(5));

        InteractiveObject b6 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "6");
        addObject(b6, 57, 39, () -> onButtonClick(6));

        InteractiveObject b7 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "7");
        addObject(b7, 31, 60, () -> onButtonClick(7));

        InteractiveObject b8 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "8");
        addObject(b8, 44, 60, () -> onButtonClick(8));

        InteractiveObject b9 = InteractiveObject.fromWidget(getContext(), R.layout.widget_scene1b_button_pad, "9");
        addObject(b9, 57, 60, () -> onButtonClick(9));*/

        dark = objectLayout(R.layout.widget_scene1_dark);
        dark.position(0, 0);
        add(dark);

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