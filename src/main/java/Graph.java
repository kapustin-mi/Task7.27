import java.util.*;

public class Graph {
    private final Set<String> vertices = new HashSet<>();
    private final HashMap<String, Integer> allWays = new HashMap<>();
    private final HashMap<String, Integer> paths = new HashMap<>();

    public Graph() {
    }

    public boolean addVertex(String vertexName) {
        if (!(vertexName.hashCode() == 0)) {
            vertices.add(vertexName);
            return true;
        }

        return false;
    }

    public boolean addWay(String start, String end, int length) {
        if (!(start.hashCode() == 0 || end.hashCode() == 0 || length <= 0)) {
            if (!(vertices.contains(start) && vertices.contains(end))) {
                vertices.add(start);
                vertices.add(end);
            }

            String way = start + "-" + end;
            if (!(allWays.containsKey(new StringBuilder(way).reverse().toString()))) {
                allWays.put(way, length);
                return true;
            }
        }

        return false;
    }

    public List<String> findAllPathsBetweenVertices(String start, String end) {
        if (!(start.hashCode() == 0 || end.hashCode() == 0)) {
            if (vertices.contains(start) && vertices.contains(end)) {

                int wayLength = 0;
                findWay(start, start, end, start, wayLength);

                return sortPaths();
            }
        }
        return null;
    }

    private List<String> sortPaths() {
        List<String> pathsList = new ArrayList<>();
        int numberOfPaths = paths.size();
        if (numberOfPaths == 0) {
            return null;
        }

        while (pathsList.size() != numberOfPaths) {
            pathsList.add(findMaxWay());
        }

        Collections.reverse(pathsList);
        return pathsList;
    }

    private String findMaxWay() {
        Set<String> keys = paths.keySet();
        String maxPath = "";
        int maxLength = 0;

        for (String key : keys) {
            if (paths.get(key) > maxLength) {
                maxLength = paths.get(key);
                maxPath = key;
            }
        }

        paths.remove(maxPath);
        return maxPath + ":" + maxLength;
    }

    private void findWay(String point, String way, String end, String start, int wayLength) {
        Set<String> keys = allWays.keySet();
        StringBuilder sb = new StringBuilder(way);

        for (String key : keys) {
            String[] points = key.split("-");
            if (Objects.equals(points[0], point)) {
                sb.append("-");
                sb.append(points[1]);

                wayLength += allWays.get(key);
                if (Objects.equals(points[1], end)) {
                    paths.put(sb.toString(), wayLength);
                } else {
                    findWay(points[1], sb.toString(), end, start, wayLength);

                    String[] wayPoints = sb.toString().split("-");
                    wayLength -= allWays.get(key);
                    sb = new StringBuilder(start);

                    for (int i = 1; i < wayPoints.length - 1; i++) {
                        sb.append("-");
                        sb.append(wayPoints[i]);
                    }
                }
            }
        }
    }
}

