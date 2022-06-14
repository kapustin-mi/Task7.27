import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class GraphTest {
    Graph graph;
    String start;
    String end;
    List<String> ways;

    public GraphTest(Graph graph, String start, String end, List<String> ways) {
        this.graph = graph;
        this.start = start;
        this.end = end;
        this.ways = ways;
    }

    @Parameterized.Parameters
    public static List<Object[]> cases() throws FileNotFoundException {
        return Arrays.asList(new Object[][]{
                {Utils.readGraphFromFile(new File("src/test/resources/inputWithTwoPaths.txt")), "A", "D",
                        Arrays.asList("A-C-D:7", "A-B-D:8")},
                {Utils.readGraphFromFile(new File("src/test/resources/nullInput.txt")), "A", "D", null},
                {Utils.readGraphFromFile(new File("src/test/resources/inputNonExistentPoints.txt")), "E", "F", null},
                {Utils.readGraphFromFile(new File("src/test/resources/inputForPathOfOnePoint.txt")), "A", "A", null},
                {Utils.readGraphFromFile(new File("src/test/resources/inputWithThreePaths.txt")), "A", "E",
                        Arrays.asList("A-D-E:13", "A-B-E:13", "A-C-E:23")},
                {Utils.readGraphFromFile(new File("src/test/resources/inputWithVeryConfusingGraph.txt")), "A", "I",
                        Arrays.asList("A-B-E-F-I:10", "A-C-E-F-I:11", "A-B-D-F-I:12", "A-B-E-G-I:13", "A-B-E-F-G-I:13",
                                "A-C-E-F-G-I:14", "A-B-D-E-F-I:14", "A-C-E-G-I:14", "A-B-D-F-G-I:15", "A-B-D-E-F-G-I:17",
                                "A-B-D-E-G-I:17")},
                {Utils.readGraphFromFile(new File("src/test/resources/inputWithThreePaths2.txt")), "A", "E",
                        Arrays.asList("A-B-E:4", "A-C-E:5", "A-B-D-E:8")}
        });
    }

    @Test
    public void findAllPathsBetweenVerticesTest() {
        Assert.assertEquals(ways, graph.findAllPathsBetweenVertices(start, end));
    }
}