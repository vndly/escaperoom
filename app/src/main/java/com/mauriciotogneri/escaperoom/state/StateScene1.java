package com.mauriciotogneri.escaperoom.state;

public class StateScene1 implements StateScene
{
    private boolean lightOn = true;

    public boolean isLightOn()
    {
        return lightOn;
    }

    public void toggleLight()
    {
        lightOn = !lightOn;
    }
}