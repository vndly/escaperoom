package com.mauriciotogneri.escaperoom.state;

public class StateScene1 implements StateScene
{
    private boolean lightOn = true;
    private boolean doorOpen = false;

    public boolean isLightOn()
    {
        return lightOn;
    }

    public void toggleLight()
    {
        lightOn = !lightOn;
    }

    public void lightOn()
    {
        lightOn = true;
    }

    public boolean isDoorOpen()
    {
        return doorOpen;
    }

    public void openDoor()
    {
        doorOpen = true;
    }
}