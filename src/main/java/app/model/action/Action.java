package app.model.action;

import java.awt.*;

public interface Action {
    // Called when the mouse is dragged
    void update(Point currentPoint);

    // Called when the mouse is released
    void finish(Point endPoint);

    // Draws a preview of the action
    void drawPreview(Graphics g);
}
