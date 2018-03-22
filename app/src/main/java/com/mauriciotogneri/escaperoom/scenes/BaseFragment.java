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

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.activities.GameActivity;
import com.mauriciotogneri.escaperoom.app.EscapeRoom;
import com.mauriciotogneri.escaperoom.interactions.RectRegisteredClick;
import com.mauriciotogneri.escaperoom.interactions.RegisteredClick;
import com.mauriciotogneri.escaperoom.interactions.RegisteredClick.OnRegionClick;
import com.mauriciotogneri.escaperoom.interactions.RoundRegisteredClick;
import com.mauriciotogneri.escaperoom.state.BaseSceneState;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;
import com.mauriciotogneri.escaperoom.widget.MenuBar;
import com.mauriciotogneri.escaperoom.widget.SceneLayout;
import com.mauriciotogneri.escaperoom.widget.SceneLayout.OnInitialized;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment<T extends BaseSceneState> extends Fragment implements OnTouchListener, OnInitialized
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

    protected abstract BaseSceneState state();

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
        MenuBar menuBar = MenuBar.create(canvas);
        menuBar.init(() -> gameActivity().openMenu());

        stateScene = (T) state();

        initialize(stateScene);

        update();
    }
}