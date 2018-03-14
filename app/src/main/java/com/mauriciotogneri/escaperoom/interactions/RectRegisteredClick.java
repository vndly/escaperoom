package com.mauriciotogneri.escaperoom.interactions;

public class RectRegisteredClick implements RegisteredClick
{
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final OnRegionClick regionClick;

    public RectRegisteredClick(int x1, int y1, int x2, int y2, OnRegionClick regionClick)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.regionClick = regionClick;
    }

    @Override
    public boolean isValid(int x, int y)
    {
        return (x >= x1) && (x <= x2) && (y >= y1) && (y <= y2);
    }

    @Override
    public void execute()
    {
        regionClick.onClick();
    }
}