package com.mauriciotogneri.escaperoom.interactions;

public interface RegisteredClick
{
    boolean isValid(int x, int y);

    void execute();

    interface OnRegionClick
    {
        void onClick();
    }
}