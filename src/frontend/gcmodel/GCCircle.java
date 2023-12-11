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
        double diameter = this.getRadius() * 2;
	    gc.fillOval(this.getCenterPoint().getX() - this.getRadius(), 
                    this.getCenterPoint().getY() - this.getRadius(), 
                    diameter, diameter);
		gc.strokeOval(this.getCenterPoint().getX() - this.getRadius(), 
                    this.getCenterPoint().getY() - this.getRadius(), diameter, diameter);
    }

}
