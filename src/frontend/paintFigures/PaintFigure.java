package frontend.paintFigures;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintFigure implements Movable, Drawable{

    private Color fillColor;
    private Color borderColor;
    protected Figure figure;

    public PaintFigure(Color fillColor, Color borderColor, Figure figure) {
        this.fillColor = fillColor;
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

    @Override
    public void draw(GraphicsContext gc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public void changePos(double diffX, double diffY) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePos'");
    }
}
