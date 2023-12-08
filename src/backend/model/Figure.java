package backend.model;

import backend.Colorable;
import backend.Drawable;
import backend.Movable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Figure implements Movable, Drawable, Colorable{

    private final Point[] keyPoints;
    private Color fillColor;

    protected final static int SHADOWOFFSET = 10;

    protected Figure(Point[] keyPoints) {
        this.keyPoints = keyPoints;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public abstract void shadow(GraphicsContext gc);
    public abstract void gradient(GraphicsContext gc);
    public abstract void bisel(GraphicsContext gc);
    
    public abstract boolean contains(Point point);
    public abstract boolean isContainedIn(Rectangle rectangle);
    
    public abstract boolean found(Point eventPoint);
    public abstract boolean found(Point startPoint, Point endPoint);

    public abstract void rotate();
    public abstract void scale(double multiplier);
    public abstract void flipH();
    public abstract void flipV();
}
