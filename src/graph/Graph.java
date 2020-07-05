package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Graph extends JPanel implements MouseMotionListener, MouseListener {

    private List<Node> nodeList = new ArrayList<>();
    private int vertexID = 0;
    Node selectNode;
    public Graph()
    {
        selectNode = null;
        addMouseListener(this);
        addMouseMotionListener(this);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0,1);

    }

    public void add(int x, int y) {
        vertexID++;
        Node current = new Node(String.valueOf(vertexID), x, y);
        nodeList.add(current);
    }

    public void printCycles() {
        for (int i = 0; i < cycles.size(); i++) {
            System.out.println((i + 1) + ". " + cycles.get(i));
        }
    }

    public void printCycles(List<List<Node>> cycles) {
        System.out.println("Cycles");
        for (int i = 0; i < cycles.size(); i++) {
            System.out.println((i + 1) + ". " + cycles.get(i));
        }
    }

    public void printNodes() {
        System.out.println("Nodes");
        for (int i = 0; i < nodeList.size(); i++) {
            System.out.println((i + 1) + ". " + nodeList.get(i));
        }
    }

    public void printCyclesLength(List<List<Node>> cycles) {

        System.out.println("Cycles Length");
        for (int i = 0; i < cycles.size(); i++) {
            System.out.println((i + 1) + ". size = " + cycles.get(i).size());
        }
    }

    public void connect(String first, String second, int value) {
        Node from = findNodeByValue(first);
        Node to = findNodeByValue(second);
        if (from != null && to != null) {
            Edge current = new Edge(from, to, value);
            from.getEdgeList().add(current);
        } else {
            System.out.println("There is no node with this value!");
        }
    }

    public Node findNodeByValue(String value) {
        Node result = null;
        for (Node node :
                nodeList) {
            if (node.getValue().equals(value)) {
                return node;
            }
        }
        return result;
    }


    private List<List<Node>> cycles = new ArrayList<>();

    public List<List<Node>> findMinCycles() {
        int minSize = 0;
        List<List<Node>> minCycles = new ArrayList<>();
        if (!cycles.isEmpty()) {
            minSize = cycles.get(0).size();
        } else {
            System.out.println("There are no cycles in the graph!");
            return null;
        }

        for (List<Node> way :
                cycles) {
            if (way.size() < minSize) {
                minSize = way.size();
            }
        }

        for (List<Node> way :
                cycles) {
            if (way.size() == minSize) {
                minCycles.add(way);
            }
        }
        return minCycles;
    }

    public void findCycles() {
        for (int i = 0; i < nodeList.size(); i++) {
            clearAll();
            findCycle(nodeList.get(i), nodeList.get(i), new ArrayList<>());
        }
    }

    public void findCycle(Node start, Node current, List<Node> way) {
        way.add(current);
        current.setVisited(true);

        for (int i = 0; i < current.getEdgeList().size(); i++) {
            Node next = current.getEdgeList().get(i).getTo();
            if (next.equals(start)) {
                List<Node> helper = new ArrayList<>();
                for (Node node :
                        way) {
                    helper.add(node);
                }
                cycles.add(helper);
            } else {
                if (!next.isVisited()) {
                    findCycle(start, next, way);
                }
            }
        }
        way.remove(current);
        current.setVisited(false);
    }

    public List<List<Node>> getCycles() {
        return cycles;
    }

    public void setCycles(List<List<Node>> cycles) {
        this.cycles = cycles;
    }


    public void clearAll() {
        for (Node node :
                nodeList) {
            node.setVisited(false);
        }
    }
    @Override
    public void paint(Graphics g) {

        g.setColor(new Color(21, 18, 18));
        g.fillRect(0,0, getWidth(), getHeight());
        for(Node nod: nodeList)
            for(Edge edge:nod.getEdgeList())
                edge.draw((Graphics2D)g);

        for(Node nod: nodeList)
            nod.draw((Graphics2D)g);

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        for(Node nod:nodeList)
            if(nod.pick(e.getX(), e.getY())){
            selectNode = nod;
            break;
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        selectNode = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(selectNode != null)
            selectNode.setPos(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
