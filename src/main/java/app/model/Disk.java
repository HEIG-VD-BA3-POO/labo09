package app.model;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.*;
import java.awt.geom.Point2D;

public class Disk {
    private Point center;
    private int radius;
    private Color color;

    public Disk(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(
                center.x - radius,
                center.y - radius,
                radius * 2,
                radius * 2);
    }

    public void move(Point newCenter) {
        this.center = newCenter;
    }

    public boolean contains(Point p) {
        // Calculate if the point is inside the disk using distance formula
        return Point2D.distance(
                center.x,
                center.y,
                p.x,
                p.y) <= radius;
    }

    // Getters
    public Point getCenter() {
        return new Point(center); // Return a copy to prevent modification
    }

    public int getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    // For comparing disks positions (useful for overlapping)
    public boolean overlaps(Disk other) {
        double centerDistance = Point2D.distance(
                center.x,
                center.y,
                other.center.x,
                other.center.y);
        return centerDistance <= (radius + other.radius);
    }

    @Override
    public String toString() {
        return String.format("Disk[center=(%d,%d), radius=%d, color=%s]",
                center.x, center.y, radius, color);
    }
}
