package com.ipi.imageManeger;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {

    public static BufferedImage filter(List<BufferedImage> imageList) {
        return toCalculateAvarage(imageList);
    }

    private static BufferedImage toCalculateAvarage(List<BufferedImage> images) {
        int width = images.get(0).getWidth();
        int height = images.get(0).getHeight();
        int imageType = images.get(0).getType();
        BufferedImage retorno = new BufferedImage(width, height, imageType);
        for (int i = 0; i < width * height; i++) {

        }
        return retorno;
    }

    public static BufferedImage medianFilter(BufferedImage image) {
        int x = 1;
        int y = 1;
        BufferedImage filterImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for ( ; Boolean.TRUE ; ) {
            List<Integer> window = doWindow(x, y, image);
            Integer medianValue = takeMedian(window);
            filterImage.setRGB(x, y, medianValue);
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

    private static List<Integer> doWindow(int x, int y, BufferedImage image) {
        List<Integer> window = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                window.add(image.getRGB(i, j));
            }
        }
        return window;
    }

    private static Integer takeMedian(List<Integer> values) {
        values = values.stream().sorted().collect(Collectors.toList());
        return values.get(4);
    }
}
