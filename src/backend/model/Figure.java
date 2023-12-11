package backend.model;

import frontend.paintFigures.Colorable;
import frontend.paintFigures.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Figure{

    private final Point[] keyPoints;
    private Color fillColor;
    private Color borderColor;

    protected final static int SHADOWOFFSET = 10;

    protected Figure(Point[] keyPoints) {
        this.keyPoints = keyPoints;
    }

    // muevo los puntos que definen a una figura
    // -> la misma cambiara su posicion al ser dibujada
    public void changePos(double deltaX, double deltaY) {
        for (Point p : keyPoints)
            p.changePos(deltaX, deltaY);
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBorderColor() {
        return borderColor;
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
    
    // nueva forma de utilizar "found" con un punto
    public abstract boolean contains(Point point);

    // nueva forma de utilizar "found" con respecto al rectangulo invisible
    public abstract boolean isContainedIn(Rectangle rectangle);

    public abstract void rotate();
    public abstract void scale(double multiplier);
    public abstract void flipH();
    public abstract void flipV();
}
