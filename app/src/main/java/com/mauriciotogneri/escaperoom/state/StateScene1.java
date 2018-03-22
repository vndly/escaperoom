package com.mauriciotogneri.escaperoom.state;

import android.content.Context;

public class StateScene1 extends BaseSceneState
{
    private boolean padOpen = false;
    private boolean lightOn = true;
    private boolean chestOpen = false;
    private boolean doorOpen = false;

    private static final String FIELD_PAD_OPEN = "pad.open";
    private static final String FIELD_LIGHT_ON = "light.on";
    private static final String FIELD_CHEST_OPEN = "chest.open";
    private static final String FIELD_DOOR_OPEN = "door.open";

    public StateScene1(Context context)
    {
        super(context);

        this.padOpen = preferences.load(FIELD_PAD_OPEN, false);
        this.lightOn = preferences.load(FIELD_LIGHT_ON, true);
        this.chestOpen = preferences.load(FIELD_CHEST_OPEN, false);
        this.doorOpen = preferences.load(FIELD_DOOR_OPEN, false);
    }

    public boolean isPadOpen()
    {
        return padOpen;
    }

    public void openPad()
    {
        padOpen = true;

        preferences.save(FIELD_PAD_OPEN, padOpen);
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

    public boolean isChestOpen()
    {
        return chestOpen;
    }

    public void openChest()
    {
        chestOpen = true;

        preferences.save(FIELD_CHEST_OPEN, chestOpen);
    }

    public boolean isDoorOpen()
    {
        return doorOpen;
    }

    public void openDoor()
    {
        doorOpen = true;

        preferences.save(FIELD_DOOR_OPEN, doorOpen);
    }
}