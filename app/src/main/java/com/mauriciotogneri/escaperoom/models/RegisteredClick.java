package com.mauriciotogneri.escaperoom.models;

public class RegisteredClick
{
    private final int x;
    private final int y;
    private final OnRegionClick regionClick;

    private static final int MAX_DISTANCE = 5;

    public RegisteredClick(int x, int y, OnRegionClick regionClick)
    {
        this.x = x;
        this.y = y;
        this.regionClick = regionClick;
    }

    public boolean isValid(int x, int y)
    {
        int distance = (int) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));

        return distance <= MAX_DISTANCE;
    }

    public void execute()
    {
        regionClick.onClick();
    }

    public interface OnRegionClick
    {
        void onClick();
    }
}