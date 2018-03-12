package com.mauriciotogneri.escaperoom.scenes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mauriciotogneri.escaperoom.activities.GameActivity;

public abstract class BaseFragment extends Fragment
{
    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        view = inflater.inflate(layout(), viewGroup, false);

        return view;
    }

    @Override
    public final void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        initialize();
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
}