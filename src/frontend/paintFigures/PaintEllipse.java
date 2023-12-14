package frontend.paintFigures;

import backend.model.Ellipse;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintEllipse extends PaintFigure{
    
    public PaintEllipse(Ellipse figure, Color fillColor, Color borderColor) {
        super(figure, fillColor, borderColor);
    }

    @Override
    public void draw(GraphicsContext gc) {
        Ellipse aux = (Ellipse) model;
        
        gc.fillOval(aux.getCenterPoint().getX() - aux.getsMayorAxis() / 2,
                aux.getCenterPoint().getY() - aux.getsMinorAxis() / 2  ,
                aux.getsMayorAxis(), aux.getsMinorAxis());
        gc.strokeOval(aux.getCenterPoint().getX() - aux.getsMayorAxis() / 2 ,
                aux.getCenterPoint().getY() - aux.getsMinorAxis() / 2,
                aux.getsMayorAxis(), aux.getsMinorAxis());
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
