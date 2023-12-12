package frontend.gcmodel;

import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class GCEllipse extends Ellipse implements GCFigure{

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
    public void setFillColor(GraphicsContext gc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFillColor'");
    }

    @Override
    public void getFillColor(GraphicsContext gc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFillColor'");
    }


    
}
