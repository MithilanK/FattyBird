package com.mithilank.fattybird.util;

public class UtilFunc {

    public static float closestMultiple(float n, float x)
    {
        if(x>n)
            return x;
        n = n + x/2;
        n = n - (n%x);
        return n;
    }
}
