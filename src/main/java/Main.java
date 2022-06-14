import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        while (true) {
            printMenuAndPerformAction(graph);
        }
    }

    private static void printMenuAndPerformAction(Graph graph) {
        System.out.print("""
                \t\tMenu:
                1. Enter the vertex.
                2. Enter the edge.
                3. Find distances between points.
                4. Finish work.
                >""");

        Scanner scn = new Scanner(System.in);
        String variant = scn.nextLine();

        switch (Integer.parseInt(variant)) {
            case 1 -> addVertex(graph);
            case 2 -> addEdge(graph);
            case 3 -> {
                String start = readVertex("start"), end = readVertex("end");
                System.out.println(graph.findAllPathsBetweenVertices(start, end));
            }
            case 4 -> System.exit(0);
            default -> System.out.println("Something went wrong... Please, try again.");
        }
    }

    private static void addEdge(Graph graph) {
        String start = readVertex("start"), end = readVertex("end");
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter path length: ");
        int length;
        while(!scn.hasNextInt()) {
            System.out.print("Invalid value! Please, try again: ");
            scn.next();
        }
        length = scn.nextInt();
        graph.addWay(start, end, length);
    }

    private static String readVertex(String vertex) {
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter " + vertex + " vertex: ");
        return scn.nextLine();
    }

    private static void addVertex(Graph graph) {
        System.out.print("Enter the vertex: ");

        Scanner scn = new Scanner(System.in);
        graph.addVertex(scn.nextLine());
    }
}
