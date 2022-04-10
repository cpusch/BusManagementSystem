public class TST {
    private Node root;

    public void put(String key, Stop stop) {
        root = put(root, key, stop, 0);
    }

    private Node put(Node node, String key, Stop stop, int i) {
        char c = key.charAt(i);
        if (node == null) {
            node = new Node(c);
        }
        if (c < node.c) {
            node.left = put(node.left, key, stop, i);
        } else if (c > node.c) {
            node.right = put(node.right, key, stop, i);
        } else if (i < key.length() - 1) {
            node.mid = put(node.mid, key, stop, i + 1);
        } else {
            node.stop = stop;
        }
        return node;
    }

    public Node get(String key) {
        return get(root, key, 0);
    }

    private Node get(Node node, String key, int i) {

        if (node == null)
            return null;
        char c = key.charAt(i);
        if (c < node.c)
            return get(node.left, key, i);
        else if (c > node.c)
            return get(node.right, key, i);
        else if (i < key.length() - 1)
            return get(node.mid, key, i + 1);
        else
            return node;

    }

    private class Node {
        char c;
        Node left;
        Node mid;
        Node right;
        Stop stop;

        Node(char c) {
            this.c = c;
        }
    }
}