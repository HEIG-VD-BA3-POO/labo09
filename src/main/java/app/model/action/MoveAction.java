package app.model.action;

import app.model.Disk;
import app.model.DiskManager;
import java.awt.*;

public class MoveAction implements Action {
    private final Disk disk;
    private Point currentPoint;
    private final Point offset;
    private final DiskManager diskManager;

    public MoveAction(Disk disk, Point startPoint, DiskManager diskManager) {
        this.disk = disk;
        this.diskManager = diskManager;

        // Calculate the offset between the mouse point and the disk center
        this.offset = new Point(
                startPoint.x - disk.getCenter().x,
                startPoint.y - disk.getCenter().y);

        // Remove the disk temporarily while moving
        diskManager.removeTopDiskAt(startPoint);
    }

    @Override
    public void update(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    @Override
    public void finish(Point endPoint) {
        Point newCenter = new Point(
                endPoint.x - offset.x,
                endPoint.y - offset.y);
        diskManager.addDisk(newCenter, disk.getRadius(), disk.getColor());
    }

    @Override
    public void drawPreview(Graphics g) {
        if (currentPoint != null) {
            int radius = disk.getRadius();
            g.setColor(disk.getColor());
            g.fillOval(
                    currentPoint.x - offset.x - radius,
                    currentPoint.y - offset.y - radius,
                    radius * 2,
                    radius * 2);
        }
    }
}
