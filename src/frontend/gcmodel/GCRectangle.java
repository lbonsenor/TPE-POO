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
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
			Math.abs(getTopLeft().getX() - getBottomRight().getX()), 
			Math.abs(getTopLeft().getY() - getBottomRight().getY()));
		gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
			Math.abs(getTopLeft().getX() - getBottomRight().getX()), 
			Math.abs(getTopLeft().getY() - getBottomRight().getY()));
    }

}
