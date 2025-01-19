package app.model;

import java.awt.Color;
import java.awt.Graphics;

public class Disk {
    private int id;
    private int center;
    private int radius;

    public Disk(int id, int center, int radius) {
        this.id = id;
        this.center = center;
        this.radius = radius;
    }

    public int getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public Color getColor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getColor'");
    }

    public Object draw(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

}
