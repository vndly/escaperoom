package com.mauriciotogneri.escaperoom.scenes;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.activities.GameActivity;
import com.mauriciotogneri.escaperoom.audio.AudioManager;
import com.mauriciotogneri.escaperoom.interactions.RectRegisteredClick;
import com.mauriciotogneri.escaperoom.interactions.RegisteredClick;
import com.mauriciotogneri.escaperoom.interactions.RegisteredClick.OnRegionClick;
import com.mauriciotogneri.escaperoom.interactions.RoundRegisteredClick;
import com.mauriciotogneri.escaperoom.state.GameState;
import com.mauriciotogneri.escaperoom.state.StateScene;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment<T extends StateScene> extends Fragment implements OnTouchListener
{
    protected View view;
    protected T stateScene;
    private ViewGroup canvas;
    private final List<RegisteredClick> registeredClicks = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        view = inflater.inflate(layout(), viewGroup, false);
        view.setOnTouchListener(this);

        canvas = view.findViewById(R.id.canvas);

        return view;
    }

    @Override
    public final void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener()
        {
            @Override
            @SuppressWarnings("unchecked")
            public void onGlobalLayout()
            {
                stateScene = (T) GameState.getInstance().stateScene(id());
                initialize(stateScene);
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    protected void playSound(String name)
    {
        AudioManager.getInstance().playSound(name);
    }

    protected void background(@DrawableRes int resId)
    {
        canvas.setBackgroundResource(resId);
    }

    protected void openScene1a()
    {
        gameActivity().openScene1a();
    }

    protected void openScene1b()
    {
        gameActivity().openScene1b();
    }

    protected void openScene2a()
    {
        gameActivity().openScene2a();
    }

    private GameActivity gameActivity()
    {
        return (GameActivity) getActivity();
    }

    protected void initialize(T stateScene)
    {
    }

    protected abstract int layout();

    protected abstract int id();

    protected void addObject(InteractiveObject object, int x, int y, InteractiveObject.OnClick onClick)
    {
        object.callback(onClick);
        object.addTo(canvas, x, y);
    }

    protected void addObject(InteractiveObject object, int x, int y)
    {
        object.addTo(canvas, x, y);
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

        int xPos = (int) (x * 100 / width);
        int yPos = (int) (y * 100 / height);

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
}