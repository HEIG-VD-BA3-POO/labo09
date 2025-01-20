package app.model;

import app.util.ColorPalette;

import java.awt.Point;
import java.awt.Color;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Optional;

public class DiskManager {
    private LinkedList<Disk> disks;

    public DiskManager() {
        disks = new LinkedList<>();
    }

    // Add a new disk
    public void addDisk(Point center, int radius, Color color) {
        disks.add(new Disk(center, radius, color));
    }

    // Remove all disks and reset color palette
    public void clearDisks() {
        disks.clear();
        ColorPalette.reset();
    }

    // Get all disks for drawing
    public LinkedList<Disk> getAllDisks() {
        return disks;
    }

    // Find the topmost disk at a given point
    public Optional<Disk> getDiskAt(Point p) {
        ListIterator<Disk> it = disks.listIterator(disks.size());

        // Iterate backwards to find the topmost disk (last added)
        while (it.hasPrevious()) {
            Disk disk = it.previous();
            if (disk.contains(p)) {
                return Optional.of(disk);
            }
        }
        return Optional.empty();
    }

    // Remove the topmost disk at a given point
    public boolean removeTopDiskAt(Point p) {
        ListIterator<Disk> it = disks.listIterator(disks.size());

        // Iterate backwards to find the topmost disk
        while (it.hasPrevious()) {
            if (it.previous().contains(p)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    // Get the next color for preview
    public Color getNextColor() {
        return ColorPalette.getColorAt(disks.size() % ColorPalette.getColorCount());
    }

    // Move a disk to a new position
    public void moveDisk(Disk disk, Point newCenter) {
        if (disks.contains(disk)) {
            disk.move(newCenter);
        }
    }

    // Calculate radius between two points
    public static int calculateRadius(Point center, Point edge) {
        int dx = edge.x - center.x;
        int dy = edge.y - center.y;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

}
