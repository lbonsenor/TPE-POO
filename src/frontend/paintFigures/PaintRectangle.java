package frontend.paintFigures;

import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintRectangle extends PaintFigure{

    public PaintRectangle(Rectangle figure, Color fillColor, Color borderColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected) {
        super(figure, fillColor, borderColor, shadowSelected, gradSelected, biselSelected);
    }

    @Override
    public void draw(GraphicsContext gc) {
        Rectangle aux = (Rectangle) model;

        gc.fillRect(aux.getTopLeft().getX(), aux.getTopLeft().getY(),
                aux.getBottomRight().getX() - aux.getTopLeft().getX(),
                aux.getBottomRight().getY() - aux.getTopLeft().getY());
        gc.strokeRect(aux.getTopLeft().getX(), aux.getTopLeft().getY(),
                aux.getBottomRight().getX() - aux.getTopLeft().getX(),
                aux.getBottomRight().getY() - aux.getTopLeft().getY());
    }

}
