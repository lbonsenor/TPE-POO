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
        gc.strokeOval(this.getCenterPoint().getX() - (this.getsMayorAxis() / 2), 
			this.getCenterPoint().getY() - (this.getsMinorAxis() / 2), 
			this.getsMayorAxis(), this.getsMinorAxis());
		gc.fillOval(this.getCenterPoint().getX() - (this.getsMayorAxis() / 2), 
			this.getCenterPoint().getY() - (this.getsMinorAxis() / 2), 
			this.getsMayorAxis(), this.getsMinorAxis());
    }


    
}
