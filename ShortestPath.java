public class ShortestPath {
    int numNodes;
    double[][] matrix;

    void parseFile(String filename) {
        try {
            File file = new File(filename);
            Scanner input = new Scanner(file);
            // skip the first two lines and set appropriat values
            numNodes = input.nextInt();
            input.next();
            matrix = new double[numNodes][numNodes];
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++)
                    matrix[i][j] = Double.POSITIVE_INFINITY;
            }
            // adds edges from file to edge list
            while (input.hasNextLine()) {
                matrix[input.nextInt()][input.nextInt()] = input.nextDouble();
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    // helper class to locate next shortest node
    private int getMinimumNode(boolean[] mst, double[] distance) {
        double tempDistance = Double.POSITIVE_INFINITY;
        int node = -1;
        for (int i = 0; i < numNodes; i++) {
            if (mst[i] == false && tempDistance > distance[i]) {
                tempDistance = distance[i];
                node = i;
            }
        }
        return node;
    }

    double[] dijkstra(int source) {
        double[] distance = new double[numNodes];
        boolean[] spt = new boolean[numNodes];

        for (int i = 0; i < numNodes; i++) {
            distance[i] = Double.POSITIVE_INFINITY;
            spt[i] = false;
        }

        distance[source] = 0;

        for (int i = 0; i < numNodes; i++) {
            int nodeU = getMinimumNode(spt, distance);
            if (nodeU == -1)
                return distance;
            spt[nodeU] = true;

            for (int nodeV = 0; nodeV < numNodes; nodeV++) {
                if (matrix[nodeU][nodeV] >= 0) {
                    if (spt[nodeV] == false && matrix[nodeU][nodeV] != Double.POSITIVE_INFINITY) {
                        double newDistance = matrix[nodeU][nodeV] + distance[nodeU];
                        if (newDistance < distance[nodeV]) {
                            distance[nodeV] = newDistance;
                        }
                    }
                }
            }
        }
        return distance;
    }
}
