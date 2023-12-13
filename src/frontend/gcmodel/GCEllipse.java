package frontend.gcmodel;

import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GCEllipse extends Ellipse implements GCFigure{

    private Color color;
    private boolean shadow, bisel, grad;

    public GCEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        super(centerPoint, sMayorAxis, sMinorAxis);
    }

    @Override
    public void createFigure(GraphicsContext gc) {
        gc.strokeOval(getCenterPoint().getX() - (getsMayorAxis() / 2), 
			getCenterPoint().getY() - (getsMinorAxis() / 2), 
			getsMayorAxis(), getsMinorAxis());
		gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2), 
			getCenterPoint().getY() - (getsMinorAxis() / 2), 
			getsMayorAxis(), getsMinorAxis());
    }

    @Override
    public void setFillColor(Color color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFillColor'");
    }

    @Override
    public Color getFillColor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFillColor'");
    }

    @Override
    public void setShadow(boolean value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setShadow'");
    }

    @Override
    public boolean getShadow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShadow'");
    }

    @Override
    public void setBisel(boolean value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBisel'");
    }

    @Override
    public boolean getBisel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBisel'");
    }

    @Override
    public void setGrad(boolean value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGrad'");
    }

    @Override
    public boolean getGrad() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGrad'");
    }


    
}
