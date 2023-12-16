package frontend.paintFigures;

import backend.model.Ellipse;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class PaintEllipse extends PaintFigure{
    
    public PaintEllipse(Ellipse figure, Color fillColor, Color borderColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected) {
        super(figure, fillColor, borderColor, shadowSelected, gradSelected, biselSelected);
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (shadow) shadowDraw(gc);
        if (bisel) biselDraw(gc);
        Ellipse aux = (Ellipse) model;
        gc.setFill( (grad) ? gradColor() : fillColor );
        gc.setLineWidth(1);
        gc.fillOval(aux.getCenterPoint().getX() - aux.getsMayorAxis() / 2,
                aux.getCenterPoint().getY() - aux.getsMinorAxis() / 2  ,
                aux.getsMayorAxis(), aux.getsMinorAxis());
        gc.strokeOval(aux.getCenterPoint().getX() - aux.getsMayorAxis() / 2 ,
                aux.getCenterPoint().getY() - aux.getsMinorAxis() / 2,
                aux.getsMayorAxis(), aux.getsMinorAxis());
    }

    @Override
    protected void shadowDraw(GraphicsContext gc) {
        Ellipse aux = (Ellipse) model;
        gc.setFill(Color.GRAY);
        gc.fillOval(aux.getCenterPoint().getX() - (aux.getsMayorAxis() / 2) + 10, 
			aux.getCenterPoint().getY() - (aux.getsMinorAxis() / 2) + 10, 
			aux.getsMayorAxis(), aux.getsMinorAxis());
    }

    @Override
    protected void biselDraw(GraphicsContext gc) {
        Ellipse aux = (Ellipse) model;
        double arcX = aux.getCenterPoint().getX() - (aux.getsMayorAxis() / 2);
        double arcY = aux.getCenterPoint().getY() - (aux.getsMinorAxis() / 2);
        gc.setLineWidth(10);
        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeArc(arcX, arcY, aux.getsMayorAxis(), aux.getsMinorAxis(), 45, 180, ArcType.OPEN);
        gc.setStroke(Color.BLACK);
        gc.strokeArc(arcX, arcY, aux.getsMayorAxis(), aux.getsMinorAxis(), 225, 180, ArcType.OPEN);
    }

}
