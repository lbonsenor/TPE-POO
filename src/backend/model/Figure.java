package backend.model;

import javafx.scene.canvas.GraphicsContext;

public abstract class Figure {

    protected final static int SHADOWOFFSET = 10;

    public abstract void changePos(double diffX, double diffY);

    public abstract void redraw(GraphicsContext gc);
    public abstract void shadow(GraphicsContext gc);
    public abstract void gradient(GraphicsContext gc);
    public abstract void bisel(GraphicsContext gc);
    public abstract boolean found(Point eventPoint);
    public abstract boolean found(Point startPoint, Point endPoint);

    public abstract void rotate();
    public abstract void scale(double multiplier);
    public abstract void flipH();
    public abstract void flipV();
}
