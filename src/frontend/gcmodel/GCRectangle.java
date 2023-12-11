package frontend.gcmodel;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class GCRectangle extends Rectangle implements GCFigure{

    public GCRectangle(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public void createFigure(GraphicsContext gc) {
        gc.fillRect(this.getTopLeft().getX(), this.getTopLeft().getY(),
			Math.abs(this.getTopLeft().getX() - this.getBottomRight().getX()), 
			Math.abs(this.getTopLeft().getY() - this.getBottomRight().getY()));
		gc.strokeRect(this.getTopLeft().getX(), this.getTopLeft().getY(),
			Math.abs(this.getTopLeft().getX() - this.getBottomRight().getX()), 
			Math.abs(this.getTopLeft().getY() - this.getBottomRight().getY()));
    }

}
