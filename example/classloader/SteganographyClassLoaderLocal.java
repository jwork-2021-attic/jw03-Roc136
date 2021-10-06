package example.classloader;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import example.encoder.SteganographyEncoder;

public class SteganographyClassLoaderLocal extends ClassLoader {

    private File path;

    public SteganographyClassLoaderLocal(File imagePath) {
        super();
        this.path = imagePath;
    }

    public SteganographyClassLoaderLocal(File imagePath, ClassLoader parent) {
        super(parent);
        this.path = imagePath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            BufferedImage img = ImageIO.read(path);

            SteganographyEncoder encoder = new SteganographyEncoder(img);
            byte[] bytes = encoder.decodeByteArray();
            return this.defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            throw new ClassNotFoundException();
        }

    }


}
