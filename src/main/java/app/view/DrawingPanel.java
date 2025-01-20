package app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

import javax.swing.JPanel;

import app.model.Disk;
import app.model.DiskManager;
import app.model.action.Action;
import app.model.action.CreateAction;
import app.model.action.MoveAction;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
    private final DiskManager diskManager;
    private Action currentAction;

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

        // Delegate preview drawing to the current action, if any
        if (currentAction != null) {
            currentAction.drawPreview(g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        requestFocusInWindow();
        Point point = e.getPoint();

        if (e.getButton() == MouseEvent.BUTTON1) {
            if (e.isShiftDown()) {
                // Initialize move action
                Optional<Disk> disk = diskManager.getDiskAt(point);
                if (disk.isPresent()) {
                    currentAction = new MoveAction(disk.get(), point, diskManager);
                }
            } else {
                // Initialize create action
                currentAction = new CreateAction(point, diskManager);
            }
        } else if (e.getButton() == MouseEvent.BUTTON3 || e.isPopupTrigger()) {
            // Remove the topmost disk
            diskManager.removeTopDiskAt(point);
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentAction != null) {
            currentAction.finish(e.getPoint());
            currentAction = null;
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentAction != null) {
            currentAction.update(e.getPoint());
        }
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
