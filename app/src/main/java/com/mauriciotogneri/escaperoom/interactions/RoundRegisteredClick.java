package com.mauriciotogneri.escaperoom.interactions;

public class RoundRegisteredClick implements RegisteredClick
{
    private final int x;
    private final int y;
    private final int radius;
    private final OnRegionClick regionClick;

    public RoundRegisteredClick(int x, int y, int radius, OnRegionClick regionClick)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.regionClick = regionClick;
    }

    @Override
    public boolean isValid(int x, int y)
    {
        int distance = (int) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));

        return distance <= radius;
    }

    @Override
    public void execute()
    {
        regionClick.onClick();
    }
}