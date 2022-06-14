import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static Graph readGraphFromFile(File file) throws FileNotFoundException {
        Graph graph = new Graph();
        List<String> lines = readLines(file);
        for (String line : lines) {
            String[] ways = line.split(", ");
            for (String way : ways) {
                Object[] dataOfWay = way.split("[-:]");
                graph.addWay(dataOfWay[0].toString(), dataOfWay[1].toString(), Integer.parseInt(dataOfWay[2].toString()));
            }
        }

        return graph;
    }

    private static List<String> readLines(File file) throws FileNotFoundException {
        Scanner scn = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scn.hasNext()) {
            lines.add(scn.nextLine());
        }

        return lines;
    }
}
