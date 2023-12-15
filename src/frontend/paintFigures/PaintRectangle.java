package frontend.paintFigures;

import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintRectangle extends PaintFigure{

    public PaintRectangle(Rectangle figure, Color fillColor, Color borderColor) {
        super(figure, fillColor, borderColor);
    }

    @Override
    public void draw(GraphicsContext gc) {
        Rectangle aux = (Rectangle) model;

        gc.fillRect(aux.getTopLeft().getX(), aux.getTopLeft().getY(),
                aux.getBottomRight().getX() - aux.getTopLeft().getX(),
                aux.getBottomRight().getY() - aux.getTopLeft().getY());
        gc.strokeRect(aux.getTopLeft().getX(), aux.getTopLeft().getY(),
                aux.getBottomRight().getX() - aux.getTopLeft().getX(),
                aux.getBottomRight().getY() - aux.getTopLeft().getY());
    }

    @Override
    public void rotate() {
        model.rotate();
    }

    @Override
    public void scale(double multiplier) {
        model.scale(multiplier);
    }

    @Override
    public void flipH() {
        model.flipH();
    }

    @Override
    public void flipV() {
        model.flipV();
    }
}
