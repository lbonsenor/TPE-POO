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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rotate'");
    }

    @Override
    public void scale(double multiplier) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scale'");
    }

    @Override
    public void flipH() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'flipH'");
    }

    @Override
    public void flipV() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'flipV'");
    }
}
