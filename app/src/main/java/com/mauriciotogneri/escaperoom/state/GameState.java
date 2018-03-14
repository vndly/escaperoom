package com.mauriciotogneri.escaperoom.state;

import android.content.Context;

public class GameState
{
    private static GameState instance;

    private final StateScene1 stateScene1;
    private final StateScene2 stateScene2;

    private GameState(Context context)
    {
        this.stateScene1 = new StateScene1();
        this.stateScene2 = new StateScene2();
        // load from preferences
    }

    public static void initialize(Context context)
    {
        GameState.instance = new GameState(context);
    }

    public static GameState getInstance()
    {
        return GameState.instance;
    }

    public StateScene stateScene(int id)
    {
        if (id == 1)
        {
            return stateScene1();
        }
        else if (id == 2)
        {
            return stateScene2();
        }

        throw new RuntimeException("Invalid state scene id");
    }

    public StateScene1 stateScene1()
    {
        return stateScene1;
    }

    public StateScene2 stateScene2()
    {
        return stateScene2;
    }
}