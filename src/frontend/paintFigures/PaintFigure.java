package frontend.paintFigures;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class PaintFigure{

    protected Color fillColor, borderColor;
    protected Figure model;

    public PaintFigure(Figure model, Color fillColor, Color borderColor) {
        this.model = model;
        setBorderColor(borderColor);
        setFillColor(fillColor);
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

    public Figure getModel(){
        return this.model;
    }

    @Override
    public String toString(){
        return model.toString();
    }

    public boolean contains(Point point){
        return model.contains(point);
    }

    // nueva forma de utilizar "found" con respecto al rectangulo invisible
    public boolean isContainedIn(Rectangle rectangle){
        return model.isContainedIn(rectangle);
    }

    public abstract void draw();

    @Override
    public void changePos(double diffX, double diffY) {
        model.changePos(diffX, diffY);
    }

    public abstract void rotate();
    public abstract void scale(double multiplier);
    public abstract void flipH();
    public abstract void flipV();
    
}
