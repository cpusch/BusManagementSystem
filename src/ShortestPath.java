import java.util.Arrays;

public class ShortestPath {
    public static int dijkstra(int[][] matrix, int source, int dest) {
        int numVertex = matrix[0].length;

        int[] shortestDistances = new int[numVertex];
        boolean[] mst = new boolean[numVertex];

        // Initialize all distances as
        // INFINITE and mst[] as false
        for (int nodeIdx = 0; nodeIdx < numVertex; nodeIdx++) {
            shortestDistances[nodeIdx] = Integer.MAX_VALUE;
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
        for (int i = 1; i < 8717; i++) {
            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int nodeIdx = 0; nodeIdx < numVertex; nodeIdx++) {
                if (!mst[nodeIdx] && shortestDistances[nodeIdx] < shortestDistance) {
                    nearestVertex = nodeIdx;
                    shortestDistance = shortestDistances[nodeIdx];
                }
            }
            // Mark the picked vertex as
            // processed
            if (nearestVertex >= 0) {
                mst[nearestVertex] = true;
            } else {
                break;
            }
            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (int nodeIdx = 0; nodeIdx < numVertex; nodeIdx++) {
                int edgeDistance = matrix[nearestVertex][nodeIdx];

                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[nodeIdx])) {
                    parents[nodeIdx] = nearestVertex;
                    shortestDistances[nodeIdx] = shortestDistance + edgeDistance;
                }
            }
        }
        int startingVertex = source;
        int endingStop = dest;
        int[] shortestDistance = new int[numVertex];
        int[] parent = new int[numVertex];
        for (int nodeIdx = 0; nodeIdx < numVertex; nodeIdx++) {
            shortestDistance[nodeIdx] = shortestDistances[nodeIdx];
            parent[nodeIdx] = parents[nodeIdx];
        }
        return matrix[source][dest];
    }
}
