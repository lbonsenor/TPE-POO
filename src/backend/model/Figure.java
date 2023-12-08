package backend.model;

import backend.Movable;
import javafx.scene.canvas.GraphicsContext;

public abstract class Figure implements Movable{

    protected final static int SHADOWOFFSET = 10;


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
