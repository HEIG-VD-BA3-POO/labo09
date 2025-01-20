package app.view;

import javax.swing.*;

import app.model.Disk;
import app.model.DiskManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
    private DiskManager diskManager;
    private Point startPoint;
    private Point currentPoint;
    private Disk selectedDisk;
    private boolean isDrawing;
    private boolean isMoving;
    private Point offset;

    public DrawingPanel() {
        diskManager = new DiskManager();
        setBackground(Color.WHITE);

        // Register mouse listeners
        addMouseListener(this);
        addMouseMotionListener(this);

        // Enable keyboard events
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw all existing disks
        diskManager.getAllDisks().forEach(disk -> disk.draw(g));

        // Draw disk preview while creating
        if (isDrawing && startPoint != null && currentPoint != null) {
            int radius = DiskManager.calculateRadius(startPoint, currentPoint);
            g.setColor(diskManager.getNextColor());
            g.fillOval(
                    startPoint.x - radius,
                    startPoint.y - radius,
                    radius * 2,
                    radius * 2);
        }

        // Draw disk preview while moving
        if (isMoving && selectedDisk != null && currentPoint != null) {
            g.setColor(selectedDisk.getColor());
            int radius = selectedDisk.getRadius();
            g.fillOval(
                    currentPoint.x - offset.x - radius,
                    currentPoint.y - offset.y - radius,
                    radius * 2,
                    radius * 2);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        requestFocusInWindow();
        startPoint = e.getPoint();
        currentPoint = startPoint;

        if (e.getButton() == MouseEvent.BUTTON1) { // Left click
            if (e.isShiftDown()) {
                // Start moving a disk
                Optional<Disk> disk = diskManager.getDiskAt(startPoint);
                if (disk.isPresent()) {
                    selectedDisk = disk.get();
                    // Calculate the offset between mouse position and disk center
                    offset = new Point(
                            startPoint.x - selectedDisk.getCenter().x,
                            startPoint.y - selectedDisk.getCenter().y);
                    diskManager.removeTopDiskAt(startPoint);
                    isMoving = true;
                }
            } else {
                // Start drawing a new disk
                isDrawing = true;
            }
        } else if (e.getButton() == MouseEvent.BUTTON3 || e.isPopupTrigger()) { // Right click
            // Remove topmost disk at click point
            diskManager.removeTopDiskAt(e.getPoint());
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isDrawing) {
            // Create new disk
            if (startPoint != null && currentPoint != null) {
                int radius = DiskManager.calculateRadius(startPoint, currentPoint);
                if (radius > 0) {
                    diskManager.addDisk(new Disk(startPoint, radius, diskManager.getNextColor()));
                }
            }
            isDrawing = false;
        } else if (isMoving && selectedDisk != null) {
            Point newCenter = new Point(
                    e.getPoint().x - offset.x,
                    e.getPoint().y - offset.y);
            diskManager.addDisk(new Disk(newCenter, selectedDisk.getRadius(), selectedDisk.getColor()));
            isMoving = false;
            selectedDisk = null;
        }

        startPoint = null;
        currentPoint = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currentPoint = e.getPoint();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Not needed for this implementation
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Not needed for this implementation
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Not needed for this implementation
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Not needed for this implementation
    }

    public void clearDisks() {
        diskManager.clearDisks();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}
