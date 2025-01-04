package special_data_structures;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
/*
 * 1) create a world with uniform quad-tree with depth of 10
 * 2) create a 10000 random list of business across with lat lng
 * 3) create a method to list 10 nearest business
 */
public class QuadTree {
    int nodeId = 1;
    private class Coord {
        double x;
        double y;
        public Coord (double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private class Node {
        int nodeId;
        Coord topLeft;
        Coord bottomRight;
        Node first;
        Node second;
        Node third;
        Node fourth;
        Boolean isLeaf = false;
        public Node (Coord topLeft, Coord bottomRight) {
            this.topLeft = topLeft;
            this.bottomRight = bottomRight;
        }
    }

    private Node createQuadTree(int depth, Node root) {
        if (depth == 0) {
            root.isLeaf = true;
            return root;
        }
        root.nodeId = nodeId;
        
        nodeId++;
        Coord tL = root.topLeft;
        Coord bR = root.bottomRight;
        double x1 = tL.x;
        double y1 = tL.y;
        double x2 = bR.x;
        double y2 = bR.y;
        Coord p0 = new Coord((x1 + x2)/2, (y1 + y2)/2);
        Coord p1 = new Coord(x1, (y1 + y2)/2);
        Coord p2 = new Coord((x1 + x2)/2, y1);
        Coord p3 =  new Coord(x2, (y1 + y2)/2);
        Coord p4 =  new Coord((x1 + x2)/2, y2);
        root.first = new Node(tL, p0);
        root.second = new Node(p1, p4);
        root.third = new Node(p2, p3);
        root.fourth = new Node(p0, bR);
        createQuadTree(depth - 1, root.first);
        createQuadTree(depth - 1, root.second);
        createQuadTree(depth - 1, root.third);
        createQuadTree(depth - 1, root.fourth);
        return root;
    }

    private class Buisness {
        int businessId;
        Coord location;
        int quadId;

        public String toString() {
            String str = "";
            str += "ID => " + businessId;
            str +="location => " + location;
            str += "quadId => " + quadId;
            return str;
        }
    }

    public static double getRandomNumber(double min, double max) {
        Random random = new Random();
        double randomValue = min + (max - min) * random.nextDouble();
        return Math.round(randomValue * 100.0) / 100.0;
    }

    public Node findQuadForLocation(Node root, Coord loc) {
        Coord tL = root.topLeft;
        Coord bR = root.bottomRight;
        double x = loc.x;
        double y = loc.y;
        double x1 = tL.x;
        double y1 = tL.y;
        double x2 = bR.x;
        double y2 = bR.y;
        if (x <= x1 && x >= x2 && y <= y1 && y >= y2) {
            if (root.isLeaf) return root;
            Node fromFirst = findQuadForLocation(root.first, loc);
            if (fromFirst != null) return fromFirst;
            Node fromSecond= findQuadForLocation(root.second, loc);
            if (fromSecond != null) return fromSecond;
            Node fromthird = findQuadForLocation(root.third, loc);
            if (fromthird != null) return fromthird;
            Node fromfourth = findQuadForLocation(root.fourth, loc);
            if (fromfourth != null) return fromfourth;
            
        } return null;
    }

    private  Map<Integer, Buisness> getRandomListOfBusinessesAcrossTheWorld(int n, Node earthNode) {
        Map<Integer, Buisness> bMap = new HashMap<>();
        QuadTree q = new QuadTree();
        for (int i = 0 ; i < n; i ++) {
            Buisness b = q.new Buisness();
            b.businessId = i;
            Coord location = new Coord(getRandomNumber(90, -90), getRandomNumber(180, -180));
            Node qNode = q.findQuadForLocation(earthNode, location);
            b.quadId = qNode.nodeId;
            b.location = location;
            bMap.put(i, b);
        }
        return bMap;
    }

    public static void main(String args[]) {
        QuadTree q = new QuadTree();
        Node earthNode = q.new Node(q.new Coord(90, 180), q.new Coord(-90, -180));
        earthNode = q.createQuadTree(10, earthNode);
        System.out.println(q.nodeId);
        Map<Integer, Buisness> bMap = q.getRandomListOfBusinessesAcrossTheWorld(100, earthNode);
        for (Map.Entry<Integer, Buisness> entry : bMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        // System.out.println(bMap.get(10));
    }

}