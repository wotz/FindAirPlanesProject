package com.ipi.fileManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static String DEFAULT_INPUT_PATH = "src/com/ipi/images/";

    private static String DEFAULT_OUTPUT_PATH = "src/com/ipi/output/";

    private static String DEFAULT_FILE_TYPE = ".bmp";

    // Lê uma imagem no caminho path
    public static BufferedImage read(String path) throws IOException{
        BufferedImage imagem = ImageIO.read(new File(path));
        return imagem;
    }

    // Salva uma imagem no caminho path
    public static void write(BufferedImage imagem, String path) throws IOException{
        File output_file = new File(DEFAULT_OUTPUT_PATH + path);

        ImageIO.write(imagem, "bmp", output_file);
    }

    public static List<BufferedImage> readAll() throws IOException {
        List<BufferedImage> images = new ArrayList<>();
        for(int i = 1; i <= 100; i++) {
            String path = DEFAULT_INPUT_PATH + i + DEFAULT_FILE_TYPE;
            images.add(read(path));

        }
        return images;
    }
}
