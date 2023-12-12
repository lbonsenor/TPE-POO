package frontend.gcmodel;

import backend.model.Point;
import backend.model.Square;
import javafx.scene.canvas.GraphicsContext;

public class GCSquare extends Square implements GCFigure{

    public GCSquare(Point topLeft, double size) {
        super(topLeft, size);
    }

    @Override
    public void createFigure(GraphicsContext gc) {
        new GCRectangle(getTopLeft(), getBottomRight()).createFigure(gc);
    }

    @Override
    public void setFillColor(GraphicsContext gc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFillColor'");
    }

    @Override
    public void getFillColor(GraphicsContext gc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFillColor'");
    }

}
