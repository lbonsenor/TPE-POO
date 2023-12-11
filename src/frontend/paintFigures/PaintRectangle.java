package frontend.paintFigures;

import backend.model.Figure;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintRectangle extends PaintFigure{

    Rectangle localRectangle;
    public PaintRectangle(Figure figure, GraphicsContext gc, Color filColor, Color borderColor) {
        super(figure, gc, filColor, borderColor);
        localRectangle = (Rectangle) figure;
    }

    @Override
    public void draw() {
        gc.fillRect(localRectangle.getTopLeft().getX(), localRectangle.getTopLeft().getY(),
                localRectangle.getBottomRight().getX() - localRectangle.getTopLeft().getX(),
                localRectangle.getBottomRight().getY() - localRectangle.getTopLeft().getY());
        gc.strokeRect(localRectangle.getTopLeft().getX(), localRectangle.getTopLeft().getY(),
                localRectangle.getBottomRight().getX() - localRectangle.getTopLeft().getX(),
                localRectangle.getBottomRight().getY() - localRectangle.getTopLeft().getY());
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
