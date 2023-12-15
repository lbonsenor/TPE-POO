package frontend.paintFigures;

import backend.model.Circle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintCircle extends PaintEllipse{
    public PaintCircle(Circle figure, Color fillColor, Color borderColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected) {
        super(figure, fillColor, borderColor, shadowSelected, gradSelected, biselSelected);
    }

    @Override
    public void draw(GraphicsContext gc){
        Circle aux = (Circle) model;
        double diameter = aux.getRadius() * 2;

	    gc.fillOval(aux.getCenterPoint().getX() - aux.getRadius(), 
                    aux.getCenterPoint().getY() - aux.getRadius(), 
                    diameter, diameter);
		gc.strokeOval(aux.getCenterPoint().getX() - aux.getRadius(), 
                    aux.getCenterPoint().getY() - aux.getRadius(), diameter, diameter);
    }
}
