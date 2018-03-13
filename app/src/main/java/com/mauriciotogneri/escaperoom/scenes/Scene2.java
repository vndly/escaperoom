package com.mauriciotogneri.escaperoom.scenes;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.audio.AudioManager;
import com.mauriciotogneri.escaperoom.widget.InteractiveImage;
import com.mauriciotogneri.escaperoom.widget.InteractiveTextView;

public class Scene2 extends BaseFragment
{
    @Override
    protected void initialize()
    {
        addObject(InteractiveImage.fromWidget(getContext(), R.layout.widget_button_back), 2, 5, this::openFirstScene);

        InteractiveTextView b1 = InteractiveTextView.fromWidget(getContext(), R.layout.widget_button_pad);
        b1.setText("1");
        addObject(b1, 35, 25, () -> onButtonClick(1));

        /*view.findViewById(R.id.button_1).setOnClickListener(view1 -> onButtonClick(1));
        view.findViewById(R.id.button_2).setOnClickListener(view1 -> onButtonClick(2));
        view.findViewById(R.id.button_3).setOnClickListener(view1 -> onButtonClick(3));
        view.findViewById(R.id.button_4).setOnClickListener(view1 -> onButtonClick(4));
        view.findViewById(R.id.button_5).setOnClickListener(view1 -> onButtonClick(5));
        view.findViewById(R.id.button_6).setOnClickListener(view1 -> onButtonClick(6));
        view.findViewById(R.id.button_7).setOnClickListener(view1 -> onButtonClick(7));
        view.findViewById(R.id.button_8).setOnClickListener(view1 -> onButtonClick(8));
        view.findViewById(R.id.button_9).setOnClickListener(view1 -> onButtonClick(9));*/
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