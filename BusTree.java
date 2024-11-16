import java.util.ArrayList;
import java.util.List;

class BusTree {
    private class Node {
        BusRecord data;
        Node left, right;

        Node(BusRecord data) {
            this.data = data;
            left = right = null;
        }
    }

    private Node root;

    // Insert a new bus record
    public void insert(BusRecord bus) {
        root = insertRec(root, bus);
    }

    private Node insertRec(Node root, BusRecord bus) {
        if (root == null) {
            return new Node(bus);
        }
        // Compare by bus name, you may change this to bus number as needed
        int comparison = bus.getBusName().compareTo(root.data.getBusName());
        if (comparison < 0) {
            root.left = insertRec(root.left, bus);
        } else if (comparison > 0) {
            root.right = insertRec(root.right, bus);
        } else {
            // Handle the case where the bus is already present (duplicate)
            System.out.println("Bus with name " + bus.getBusName() + " already exists.");
        }
        return root;
    }

    // Delete a bus record
    public void delete(BusRecord bus) {
        root = deleteRec(root, bus);
    }

    private Node deleteRec(Node root, BusRecord bus) {
        if (root == null) {
            return null;
        }
        int comparison = bus.getBusName().compareTo(root.data.getBusName());
        if (comparison < 0) {
            root.left = deleteRec(root.left, bus);
        } else if (comparison > 0) {
            root.right = deleteRec(root.right, bus);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Node with two children, get the inorder successor
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    private BusRecord minValue(Node root) {
        BusRecord minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // Get all buses in sorted order
    public List<BusRecord> getAllSorted() {
        List<BusRecord> buses = new ArrayList<>();
        inOrder(root, buses);
        return buses;
    }

    private void inOrder(Node root, List<BusRecord> buses) {
        if (root != null) {
            inOrder(root.left, buses);
            buses.add(root.data);
            inOrder(root.right, buses);
        }
    }

    // Additional method to search for a bus by name
    public BusRecord searchBusByName(String busName) {
        return searchRec(root, busName);
    }

    private BusRecord searchRec(Node root, String busName) {
        if (root == null) {
            return null;
        }
        int comparison = busName.compareTo(root.data.getBusName());
        if (comparison < 0) {
            return searchRec(root.left, busName);
        } else if (comparison > 0) {
            return searchRec(root.right, busName);
        } else {
            return root.data; // Bus found
        }
    }
}
