package example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;

import example.classloader.SteganographyClassLoader;

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

        // SteganographyClassLoader loader = new SteganographyClassLoader(
        //         new URL("file:example.QuickSorter.png"));
        // Class c = loader.loadClass("example.QuickSorter");

        SteganographyClassLoader loader = new SteganographyClassLoader(
            new URL("file:example.SelectSorter.png"));
        Class c = loader.loadClass("example.SelectSorter");


        // skycloud36
        // SteganographyClassLoader loader = new SteganographyClassLoader(
        //         new URL("https://pic.imgdb.cn/item/615dc6292ab3f51d91521a25.png"));
        // Class c = loader.loadClass("example.QuickSort");

        // SteganographyClassLoader loader = new SteganographyClassLoader(
        //         new URL("file:tmp/example.QuickSort.png"));
        // Class c = loader.loadClass("example.QuickSort");

        // SteganographyClassLoader loader = new SteganographyClassLoader(
        //         new URL("https://pic.imgdb.cn/item/615dc7552ab3f51d915569da.png"));
        // Class c = loader.loadClass("example.AlterSort");

        // SteganographyClassLoader loader = new SteganographyClassLoader(
        //         new URL("file:tmp/example.AlterSort.png"));
        // Class c = loader.loadClass("example.AlterSort");

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
