package frontend.gcmodel;

import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GCRectangle extends GCFigure{

    public GCRectangle(Rectangle figure, Color fillColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected) {
        super(figure, fillColor, shadowSelected, gradSelected, biselSelected);
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (effectMap.get(Effects.SHADOW)) shadowDraw(gc);
        if (effectMap.get(Effects.BEVEL)) biselDraw(gc);
        gc.setFill( (effectMap.get(Effects.GRADIENT)) ? gradColor() : fillColor );

        Rectangle aux = (Rectangle) model;

        gc.setLineWidth(1);
        gc.fillRect(aux.getTopLeft().getX(), aux.getTopLeft().getY(),
                aux.getBottomRight().getX() - aux.getTopLeft().getX(),
                aux.getBottomRight().getY() - aux.getTopLeft().getY());
        gc.strokeRect(aux.getTopLeft().getX(), aux.getTopLeft().getY(),
                aux.getBottomRight().getX() - aux.getTopLeft().getX(),
                aux.getBottomRight().getY() - aux.getTopLeft().getY());
        
    }

    @Override
    protected void shadowDraw(GraphicsContext gc) {
        Rectangle aux = (Rectangle) model;
        gc.setFill(Color.GRAY);
        gc.fillRect(aux.getTopLeft().getX() + 10.0,
            aux.getTopLeft().getY() + 10.0,
            Math.abs(aux.getTopLeft().getX() - aux.getBottomRight().getX()),
            Math.abs(aux.getTopLeft().getY() - aux.getBottomRight().getY()));
    }

    @Override
    protected void biselDraw(GraphicsContext gc) {
        Rectangle aux = (Rectangle) model;
        double x = aux.getTopLeft().getX();
        double y = aux.getTopLeft().getY();
        double width = Math.abs(x - aux.getBottomRight().getX());
        double height = Math.abs(y - aux.getBottomRight().getY());
        gc.setLineWidth(10);
        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeLine(x, y, x + width, y);
        gc.strokeLine(x, y, x, y + height);
        gc.setStroke(Color.BLACK);
        gc.strokeLine(x + width, y, x + width, y + height);
        gc.strokeLine(x, y + height, x + width, y + height);
    }

}
