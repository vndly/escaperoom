package com.mauriciotogneri.escaperoom.state;

import android.content.Context;

public class StateScene1 extends BaseSceneState
{
    private boolean padOpen;
    private boolean lightOn;
    private boolean drawerOpen;
    private boolean doorOpen;

    private static final String FIELD_PAD_OPEN = "pad.open";
    private static final String FIELD_LIGHT_ON = "light.on";
    private static final String FIELD_DRAWER_OPEN = "drawer.open";
    private static final String FIELD_DOOR_OPEN = "door.open";

    public StateScene1(Context context)
    {
        super(context);

        this.padOpen = preferences.load(FIELD_PAD_OPEN, false);
        this.lightOn = preferences.load(FIELD_LIGHT_ON, true);
        this.drawerOpen = preferences.load(FIELD_DRAWER_OPEN, false);
        this.doorOpen = preferences.load(FIELD_DOOR_OPEN, false);
    }

    public boolean isPadOpen()
    {
        return padOpen;
    }

    public void openPad()
    {
        preferences.save(FIELD_PAD_OPEN, padOpen = true);
    }

    public boolean isLightOn()
    {
        return lightOn;
    }

    public void toggleLight()
    {
        lightOn = !lightOn;

        preferences.save(FIELD_LIGHT_ON, lightOn);
    }

    public boolean isDrawerOpen()
    {
        return drawerOpen;
    }

    public void openDrawer()
    {
        preferences.save(FIELD_DRAWER_OPEN, drawerOpen = true);
    }

    public boolean isDoorOpen()
    {
        return doorOpen;
    }

    public void openDoor()
    {
        preferences.save(FIELD_DOOR_OPEN, doorOpen = true);
    }
}