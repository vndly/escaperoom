package com.mauriciotogneri.escaperoom.scenes;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.activities.GameActivity;
import com.mauriciotogneri.escaperoom.app.EscapeRoom;
import com.mauriciotogneri.escaperoom.interactions.RectRegisteredClick;
import com.mauriciotogneri.escaperoom.interactions.RegisteredClick;
import com.mauriciotogneri.escaperoom.interactions.RegisteredClick.OnRegionClick;
import com.mauriciotogneri.escaperoom.interactions.RoundRegisteredClick;
import com.mauriciotogneri.escaperoom.state.BaseScene;
import com.mauriciotogneri.escaperoom.state.GameState;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;
import com.mauriciotogneri.escaperoom.widget.SceneLayout;
import com.mauriciotogneri.escaperoom.widget.SceneLayout.OnInitialized;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment<T extends BaseScene> extends Fragment implements OnTouchListener, OnInitialized
{
    protected View view;
    protected T stateScene;
    private SceneLayout canvas;
    private final List<RegisteredClick> registeredClicks = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        view = inflater.inflate(layout(), viewGroup, false);

        canvas = view.findViewById(R.id.canvas);
        canvas.setOnTouchListener(this);
        canvas.onInitialized(this);

        return view;
    }

    protected InteractiveObject objectLayout(@LayoutRes int resId)
    {
        return InteractiveObject.fromLayout(getContext(), resId);
    }

    protected InteractiveObject objectDrawable(@DrawableRes int resId)
    {
        return InteractiveObject.fromResource(getContext(), resId);
    }

    protected void visible(View view)
    {
        view.setVisibility(View.VISIBLE);
    }

    protected void gone(View view)
    {
        view.setVisibility(View.GONE);
    }

    protected void playSound(String name)
    {
        EscapeRoom.audioManager().playAudio(name, false);
    }

    protected void background(@DrawableRes int resId)
    {
        canvas.setBackgroundResource(resId);
    }

    protected void openScene(Fragment fragment)
    {
        gameActivity().openScene(fragment);
    }

    private GameActivity gameActivity()
    {
        return (GameActivity) getActivity();
    }

    protected void update()
    {
        onUpdate(stateScene);

        if (stateScene.hasKey())
        {
            //visible(bag);
        }
        else
        {
            //gone(bag);
        }
    }

    protected void initialize(T stateScene)
    {
    }

    protected abstract void onUpdate(T state);

    protected abstract int layout();

    protected abstract int id();

    protected void add(InteractiveObject object)
    {
        object.addTo(canvas, SceneLayout.BASE_RATIO);
    }

    protected void registerClick(int x, int y, int radius, OnRegionClick onRegionClick)
    {
        registeredClicks.add(new RoundRegisteredClick(x, y, radius, onRegionClick));
    }

    protected void registerClick(int x1, int y1, int x2, int y2, OnRegionClick onRegionClick)
    {
        registeredClicks.add(new RectRegisteredClick(x1, y1, x2, y2, onRegionClick));
    }

    @Override
    public boolean onTouch(View view, MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        float width = view.getWidth();
        float height = view.getHeight();

        int xPos = (int) (x / width * SceneLayout.BASE_WIDTH);
        int yPos = (int) (y / height * SceneLayout.BASE_HEIGHT);

        Log.d("onTouch", String.format("x: %s, y: %s", xPos, yPos));

        for (RegisteredClick registeredClick : registeredClicks)
        {
            if (registeredClick.isValid(xPos, yPos))
            {
                registeredClick.execute();
                break;
            }
        }

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onInitialized()
    {
        View menuBag = View.inflate(getContext(), R.layout.view_bag, null);

        LayoutParams layoutParams = new LayoutParams(canvas.getMeasuredWidth() / 12, LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        canvas.addView(menuBag, layoutParams);

        stateScene = (T) GameState.getInstance().stateScene(id());
        initialize(stateScene);

        InteractiveObject menu = objectDrawable(R.drawable.ic_menu);
        menu.position(1780, 40);
        menu.size(100, 100);
        menu.callback(() -> gameActivity().openMenu());
        add(menu);

        update();
    }
}