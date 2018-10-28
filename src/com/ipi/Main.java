package com.ipi;

import com.ipi.fileManager.FileManager;
import com.ipi.imageManeger.Filter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        try {
            BufferedImage image = FileManager.read("src/com/ipi/images/1.bmp");
            BufferedImage output = Filter.medianFilter(image);
            List<BufferedImage> images = FileManager.readAll();
            Filter.toCalculateAvarage(images, output);
            String name = LocalDateTime.now().toString() + ".bmp";
            FileManager.write(output, name);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
