package example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.io.File;

import example.classloader.SteganographyClassLoader;
import example.classloader.SteganographyClassLoaderLocal;

public class Scene {

    public static void main(String[] args) throws Exception {

        Line line = new Line(7);
        line.put(Gourd.ONE, 6);
        line.put(Gourd.TWO, 3);
        line.put(Gourd.THREE, 1);
        line.put(Gourd.FOUR, 5);
        line.put(Gourd.FIVE, 2);
        line.put(Gourd.SIX, 4);
        line.put(Gourd.SEVEN, 0);

        Geezer theGeezer = Geezer.getTheGeezer();

        // SteganographyClassLoader loader = new SteganographyClassLoader(
        //         new URL("https://cdn.njuics.cn/example.BubbleSorter.png"));
        // Class c = loader.loadClass("example.BubbleSorter");

        // SteganographyClassLoaderLocal loader = new SteganographyClassLoaderLocal(
        //         new File("example.QuickSorter.png"));
        // Class c = loader.loadClass("example.QuickSorter");

        SteganographyClassLoaderLocal loader = new SteganographyClassLoaderLocal(
            new File("example.SelectSorter.png"));
        Class c = loader.loadClass("example.SelectSorter");

        Sorter sorter = (Sorter) c.newInstance();

        theGeezer.setSorter(sorter);

        String log = theGeezer.lineUp(line);

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("result.txt"));
        writer.write(log);
        writer.flush();
        writer.close();

    }

}
