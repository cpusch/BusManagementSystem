import java.util.ArrayList;
import java.util.Arrays;

public class ShortestPath {
    public static final int INFINITY = 10000;

    public static ArrayList<Integer> dijkstra(int[][] matrix, int source, int dest) {
        int numVertex = matrix[0].length;

        int[] shortestDistances = new int[numVertex];
        boolean[] mst = new boolean[numVertex];

        // Initialize all distances as
        // INFINITE and mst[] as false
        for (int nodeIdx = 0; nodeIdx < numVertex; nodeIdx++) {
            shortestDistances[nodeIdx] = INFINITY;
            mst[nodeIdx] = false;
        }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[source] = 0;

        // Parent array to store shortest
        // path tree
        int[] parents = new int[numVertex];

        // The starting vertex does not
        // have a parent
        parents[source] = -1;

        // Find shortest path for all
        // vertices
        for (int i = 1; i < numVertex; i++) {
            int nearestVertex = -1;
            int shortestDistance = INFINITY;
            for (int nodeIdx = 0; nodeIdx < numVertex; nodeIdx++) {
                if (!mst[nodeIdx] && shortestDistances[nodeIdx] < shortestDistance) {
                    nearestVertex = nodeIdx;
                    shortestDistance = shortestDistances[nodeIdx];
                }
            }

            if (nearestVertex >= 0) {
                mst[nearestVertex] = true;
            } else {
                break;
            }
            for (int nodeIdx = 0; nodeIdx < numVertex; nodeIdx++) {
                int edgeDistance = matrix[nearestVertex][nodeIdx];

                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[nodeIdx])) {
                    parents[nodeIdx] = nearestVertex;
                    shortestDistances[nodeIdx] = shortestDistance + edgeDistance;
                }
            }
        }
        int[] shortestDistance = new int[numVertex];
        int[] parent = new int[numVertex];
        for (int nodeIdx = 0; nodeIdx < numVertex; nodeIdx++) {
            shortestDistance[nodeIdx] = shortestDistances[nodeIdx];
            parent[nodeIdx] = parents[nodeIdx];
        }
        if (shortestDistance[dest] == INFINITY) {
            System.out.println("No shortest path found between [" + source + "] and [" + dest + "]");
            return null;
        } else {
            System.out.println("Shortest path from stop [" + source + "] to [" + dest + "] is a cost of ["
                    + shortestDistance[dest]
                    + "] using the following route");
            ArrayList<Integer> route = new ArrayList<Integer>();
            getPath(dest, parents, 0, route);
            route.remove(0);
            route.add(dest);
            return route;
        }
    }

    private static void getPath(int vertex, int[] route, int index, ArrayList<Integer> routePath) {
        index++;
        if (vertex == -1) {
            return;
        } else {
            getPath(vertex, route, index, routePath);
            routePath.add(route[vertex]);
        }
    }
}
