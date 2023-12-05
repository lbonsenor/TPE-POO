package backend.model;

import javafx.scene.canvas.GraphicsContext;

public interface Figure {
    public abstract void changePos(double diffX, double diffY);
    public abstract Figure getFigureBasedOnPoints(Point startPoint, Point endPoint);
    public abstract void redraw(GraphicsContext gc);
    public abstract boolean found(Point eventPoint);
}
