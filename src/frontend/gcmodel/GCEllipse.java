package frontend.gcmodel;

import backend.model.Ellipse;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class GCEllipse extends GCFigure{
    
    public GCEllipse(Ellipse figure, Color fillColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected) {
        super(figure, fillColor, shadowSelected, gradSelected, biselSelected);
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (effectMap.get(Effects.SHADOW)) shadowDraw(gc);
        if (effectMap.get(Effects.BEVEL)) biselDraw(gc);
        gc.setFill( (effectMap.get(Effects.GRADIENT)) ? gradColor() : fillColor );

        Ellipse aux = (Ellipse) model;
        
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
