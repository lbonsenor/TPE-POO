package frontend.paintFigures;

import backend.model.Circle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class PaintCircle extends PaintEllipse{
    public PaintCircle(Circle figure, Color fillColor, Color borderColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected) {
        super(figure, fillColor, borderColor, shadowSelected, gradSelected, biselSelected);
    }

    @Override
    public void draw(GraphicsContext gc){
        if (shadow) shadowDraw(gc);
        if (bisel) biselDraw(gc);
        Circle aux = (Circle) model;
        double diameter = aux.getRadius() * 2;
        gc.setFill( (grad) ? gradColor() : fillColor );
        gc.setLineWidth(1);
	    gc.fillOval(aux.getCenterPoint().getX() - aux.getRadius(), 
                    aux.getCenterPoint().getY() - aux.getRadius(), 
                    diameter, diameter);
		gc.strokeOval(aux.getCenterPoint().getX() - aux.getRadius(), 
                    aux.getCenterPoint().getY() - aux.getRadius(), diameter, diameter);
    }

    @Override
    protected void biselDraw(GraphicsContext gc) {
        Circle aux = (Circle) model;
        double diameter = aux.getRadius() * 2;
        double arcX = aux.getCenterPoint().getX() - aux.getRadius();
        double arcY = aux.getCenterPoint().getY() - aux.getRadius();
        gc.setLineWidth(10);
        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeArc(arcX, arcY, diameter, diameter, 45, 180, ArcType.OPEN);
        gc.setStroke(Color.BLACK);
        gc.strokeArc(arcX, arcY, diameter, diameter, 225, 180, ArcType.OPEN);
    }

    @Override
    protected void shadowDraw(GraphicsContext gc) {
        Circle aux = (Circle) model;
        double diameter = aux.getRadius() * 2;
        gc.setFill(Color.GRAY);
        gc.fillOval(aux.getCenterPoint().getX() - aux.getRadius() + 10.0,
            aux.getCenterPoint().getY() - aux.getRadius() + 10.0, diameter, diameter);
    }

}
