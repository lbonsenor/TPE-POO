package frontend.gcmodel;

import backend.model.Circle;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class GCCircle extends Circle implements GCFigure{

    public GCCircle(Point centerPoint, double radius) {
        super(centerPoint, radius);
    }

    @Override
    public void createFigure(GraphicsContext gc){
        double diameter = getRadius() * 2;
	    gc.fillOval(getCenterPoint().getX() - getRadius(), 
                    getCenterPoint().getY() - getRadius(), 
                    diameter, diameter);
		gc.strokeOval(getCenterPoint().getX() - getRadius(), 
                    getCenterPoint().getY() - getRadius(), diameter, diameter);
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
