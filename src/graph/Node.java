package graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Node extends Interactive{

    private String value;
    private boolean isVisited = false;
    private List<Edge> edgeList = new ArrayList<>();

    public Node(String value, int xpos, int ypos) {
        this.value = value;
        sizeX = 50;
        xPos = xpos;
        yPos = ypos;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                //", isVisited=" + isVisited +
                //", edgeList=" + edgeList +
                '}';
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(31, 25, 49));
        int offset = sizeX/2;
        graphics2D.fillOval(xPos - offset, yPos - offset, sizeX, sizeX);
        graphics2D.setColor(Color.LIGHT_GRAY);
        graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        graphics2D.drawString(value,xPos-7 , yPos+10  );

    }

    public void setPos(int x, int y)
    {
        xPos = x;
        yPos = y;
    }

    @Override
    public boolean pick(int x, int y) {
        double length = Math.sqrt(Math.pow(x-xPos, 2) + Math.pow(y-yPos, 2));
        return length < sizeX;
    }
}
