package app.view;

import javax.swing.*;

import app.model.Disk;
import app.model.DiskManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
    private DiskManager diskManager;
    private Point startPoint;
    private Point currentPoint;
    private Disk selectedDisk;
    private boolean isDrawing;
    private boolean isMoving;

    public DrawingPanel() {
        diskManager = new DiskManager();
        setBackground(Color.WHITE);

        // Register mouse listeners
        addMouseListener(this);
        addMouseMotionListener(this);

        // Enable keyboard events
        setFocusable(true);
    }

    public Object clearDisks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearDisks'");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw all existing disks
        diskManager.getAllDisks().forEach(disk -> disk.draw(g));

        // Draw disk preview while creating
        if (isDrawing && startPoint != null && currentPoint != null) {
            int radius = calculateRadius(startPoint, currentPoint);
            g.setColor(diskManager.getNextColor());
            g.drawOval(
                    startPoint.x - radius,
                    startPoint.y - radius,
                    radius * 2,
                    radius * 2);
        }

        // Draw disk preview while moving
        if (isMoving && selectedDisk != null && currentPoint != null) {
            // Draw temporary disk at new position
            int radius = selectedDisk.getRadius();
            g.setColor(selectedDisk.getColor());
            g.drawOval(
                    currentPoint.x - radius,
                    currentPoint.y - radius,
                    radius * 2,
                    radius * 2);
        }
    }

    private int calculateRadius(Point startPoint2, Point currentPoint2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateRadius'");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}
