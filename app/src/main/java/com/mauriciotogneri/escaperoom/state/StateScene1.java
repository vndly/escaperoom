package com.mauriciotogneri.escaperoom.state;

public class StateScene1 implements StateScene
{
    private boolean lightOn = false;

    public boolean isLightOn()
    {
        return lightOn;
    }

    public void lightOn(boolean value)
    {
        lightOn = value;
    }
}