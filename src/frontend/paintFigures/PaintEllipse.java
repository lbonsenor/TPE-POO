package frontend.paintFigures;

import backend.model.Ellipse;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintEllipse extends PaintFigure{
    
    public PaintEllipse(Ellipse figure, Color fillColor, Color borderColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected) {
        super(figure, fillColor, borderColor, shadowSelected, gradSelected, biselSelected);
    }

    @Override
    public void draw(GraphicsContext gc) {
        Ellipse aux = (Ellipse) model;
        
        gc.fillOval(aux.getCenterPoint().getX() - aux.getsMayorAxis() / 2,
                aux.getCenterPoint().getY() - aux.getsMinorAxis() / 2  ,
                aux.getsMayorAxis(), aux.getsMinorAxis());
        gc.strokeOval(aux.getCenterPoint().getX() - aux.getsMayorAxis() / 2 ,
                aux.getCenterPoint().getY() - aux.getsMinorAxis() / 2,
                aux.getsMayorAxis(), aux.getsMinorAxis());
    }

}
