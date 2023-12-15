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
        if (shadow) shadowDraw(gc);
        if (bisel) biselDraw(gc);
        Rectangle aux = (Rectangle) model;
        gc.setFill( (grad) ? gradColor() : fillColor );
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
