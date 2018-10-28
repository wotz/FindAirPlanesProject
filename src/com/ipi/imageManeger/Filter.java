package com.ipi.imageManeger;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {


    public static void toCalculateAvarage(List<BufferedImage> images, BufferedImage image) {
        int width = images.get(0).getWidth();
        int height = images.get(0).getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                final int a = i;
                final int b = j;
                List<Double> ycbcr = new ArrayList<>();
                for (int dim = 0; dim < 3; dim++) {
                    final int c = dim;
                    double value = images.stream()
                            .mapToDouble( m -> (Converter.rgbToYCbCr(m.getRGB(a,b)).get(c))/100.0).sum();
                    ycbcr.add(dim, value);
                }
                image.setRGB(i, j, Converter.yCbCrToRgb(ycbcr));
            }
        }
    }

    public static BufferedImage medianFilter(BufferedImage image) {
        int x = 1;
        int y = 1;
        BufferedImage filterImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for ( ; Boolean.TRUE ; ) {
            List<Double> ycbcr = Converter.rgbToYCbCr((new Color(image.getRGB(x, y))).getRGB());
            for (int i = 0; i < 3; i++) {
                List<Double> window = doWindow(x, y, image, i);
                Double medianValue = takeMedian(window);
                ycbcr.set(i, medianValue);
            }
            filterImage.setRGB(x, y, Converter.yCbCrToRgb(ycbcr));
            List<Integer> positions = moveWindow(x, y, image);
            if (positions.size() != 2) {
                break;
            }
            x = positions.get(0);
            y = positions.get(1);
        }
        return filterImage;
    }

    private static List<Integer> moveWindow(int i, int j, BufferedImage image) {
        List<Integer> position = new ArrayList<>();
        int width = image.getWidth();
        int height = image.getHeight();
        if (i < width - 2) {
            position.add(i + 1);
            position.add(j);
            return position;
        }
        if (i >= width -2 && j < height - 2) {
            position.add(1);
            position.add(j + 1);
            return position;
        }
        return position;
    }

    private static List<Double> doWindow(int x, int y, BufferedImage image, int channel) {
        List<Double> window = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                window.add(Converter.rgbToYCbCr(image.getRGB(i, j)).get(channel));
            }
        }
        return window;
    }

    private static Double takeMedian(List<Double> values) {
        values = values.stream().sorted().collect(Collectors.toList());
        return values.get(4);
    }
}
