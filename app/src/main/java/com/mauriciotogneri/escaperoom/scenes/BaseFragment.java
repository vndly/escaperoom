package com.mauriciotogneri.escaperoom.scenes;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.mauriciotogneri.escaperoom.R;
import com.mauriciotogneri.escaperoom.activities.GameActivity;
import com.mauriciotogneri.escaperoom.models.RegisteredClick;
import com.mauriciotogneri.escaperoom.models.RegisteredClick.OnRegionClick;
import com.mauriciotogneri.escaperoom.widget.InteractiveObject;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment extends Fragment implements OnTouchListener
{
    protected View view;
    private ViewGroup canvas;
    private final List<RegisteredClick> registeredClicks = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState)
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
            public void onGlobalLayout()
            {
                initialize();
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    protected void openFirstScene()
    {
        gameActivity().openFirstScene();
    }

    protected void openSecondScene()
    {
        gameActivity().openSecondScene();
    }

    private GameActivity gameActivity()
    {
        return (GameActivity) getActivity();
    }

    protected void initialize()
    {
    }

    protected abstract int layout();

    protected void addObject(@DrawableRes int resId, int x, int y, InteractiveObject.OnClick onClick)
    {
        InteractiveObject pad = new InteractiveObject(getContext());
        pad.init(resId, onClick);
        pad.addTo(canvas, x, y);
    }

    protected void registerClick(int x, int y, OnRegionClick onRegionClick)
    {
        registeredClicks.add(new RegisteredClick(x, y, onRegionClick));
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