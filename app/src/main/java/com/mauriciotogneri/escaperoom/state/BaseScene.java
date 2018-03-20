package com.mauriciotogneri.escaperoom.state;

public class BaseScene
{
    private boolean hasKey = false;

    public boolean hasKey()
    {
        return hasKey;
    }

    public void getKey()
    {
        hasKey = true;
    }
}