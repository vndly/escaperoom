package com.mauriciotogneri.escaperoom.state;

public class StateScene1 extends BaseScene
{
    private boolean isPadOpen = false;
    private boolean lightOn = true;
    private boolean isChestOpen = false;
    private boolean doorOpen = false;

    public boolean isPadOpen()
    {
        return isPadOpen;
    }

    public void openPad()
    {
        isPadOpen = true;
    }

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

    public boolean isChestOpen()
    {
        return isChestOpen;
    }

    public void openChest()
    {
        isChestOpen = true;
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