package graph;

import java.awt.*;

public class Edge extends Interactive {
    private Node from;
    private Node to;
    private int value;
    private boolean isVisited = false;

    public Edge(Node from, Node to, int value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public String toString() {
        return "{" +
                "" + value +
                ", " + isVisited +
                '}';
    }

    @Override
    void draw(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(49, 100, 163));
        graphics2D.setStroke(new BasicStroke(10));
        graphics2D.drawLine(from.xPos, from.yPos, to.xPos, to.yPos);
        graphics2D.setColor(new Color(70, 165, 97));
        graphics2D.setFont(new Font("TimesRoman", Font.ITALIC, 24));
        graphics2D.drawString(String.valueOf(value), (from.xPos + to.xPos)/2, (from.yPos + to.yPos)/2);
    }

    @Override
    boolean pick(int x, int y) {
        return false;
    }
}
