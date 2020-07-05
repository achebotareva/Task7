package graph;

import java.awt.*;

public abstract class Interactive {
    int sizeX;
    int sizeY;
    int xPos;
    int yPos;
    abstract  void draw(Graphics2D graphics2D);
    abstract  boolean pick(int x, int y);
}
