package com.ipi.imageManeger;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class Converter {

    public static List<Double> rgbToYCbCr(int rgb) {
        Color color = new Color(rgb);
        double r = color.getRed();
        double g = color.getGreen();
        double b = color.getBlue();

        double y  =  (0.2989   * r  + 0.587  * g + 0.1145 * b);
        double cb =  0.564*(b - y);
        double cr =  0.713*(r - y);
        Double[] retorno = {y, cb, cr};
        return Arrays.asList(retorno);
    }


    public static int yCbCrToRgb(List<Double> components) {
        double y = components.get(0);
        double cb = components.get(1);
        double cr = components.get(2);
        int r = (int) Math.max(0, Math.min(255,  (y + 1.402*cr)));
        int g = (int) Math.max(0, Math.min(255,  (y -0.344*cb -0.714*cr)));
        int b = (int) Math.max(0, Math.min(255,  (y +1.772*cb)));

        return (new Color(r, g, b)).getRGB();
    }
}
