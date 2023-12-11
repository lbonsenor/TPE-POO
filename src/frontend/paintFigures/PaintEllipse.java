package frontend.paintFigures;

import backend.model.Ellipse;
import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintEllipse extends PaintFigure{
    Ellipse localEllipse;
    public PaintEllipse(Figure figure, GraphicsContext gc, Color fillColor, Color borderColor) {
        super(figure, gc, fillColor, borderColor);
        localEllipse = (Ellipse) figure;
    }

    @Override
    public void draw() {
        gc.fillOval(localEllipse.getCenterPoint().getX() - localEllipse.getsMayorAxis() / 2,
                localEllipse.getCenterPoint().getY() - localEllipse.getsMinorAxis() / 2  ,
                localEllipse.getsMayorAxis(), localEllipse.getsMinorAxis());
        gc.strokeOval(localEllipse.getCenterPoint().getX() - localEllipse.getsMayorAxis() / 2 ,
                localEllipse.getCenterPoint().getY() - localEllipse.getsMinorAxis() / 2,
                localEllipse.getsMayorAxis(), localEllipse.getsMinorAxis());
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
