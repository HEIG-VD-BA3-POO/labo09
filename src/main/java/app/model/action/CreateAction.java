package app.model.action;

import app.model.DiskManager;
import java.awt.*;

public class CreateAction implements Action {
    private final Point startPoint;
    private Point currentPoint;
    private final DiskManager diskManager;

    public CreateAction(Point startPoint, DiskManager diskManager) {
        this.startPoint = startPoint;
        this.currentPoint = startPoint;
        this.diskManager = diskManager;
    }

    @Override
    public void update(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    @Override
    public void finish(Point endPoint) {
        int radius = DiskManager.calculateRadius(startPoint, endPoint);
        if (radius > 0) {
            diskManager.addDisk(startPoint, radius, diskManager.getNextColor());
        }
    }

    @Override
    public void drawPreview(Graphics g) {
        if (startPoint != null && currentPoint != null) {
            int radius = DiskManager.calculateRadius(startPoint, currentPoint);
            g.setColor(diskManager.getNextColor());
            g.fillOval(
                    startPoint.x - radius,
                    startPoint.y - radius,
                    radius * 2,
                    radius * 2);
        }
    }
}
