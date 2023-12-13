package frontend.gcmodel;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GCRectangle extends Rectangle implements GCFigure{

    private Color color;
    private boolean shadow, bisel, grad;

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

    @Override
    public void setFillColor(Color color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFillColor'");
    }

    @Override
    public Color getFillColor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFillColor'");
    }

    @Override
    public void setShadow(boolean value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setShadow'");
    }

    @Override
    public boolean getShadow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShadow'");
    }

    @Override
    public void setBisel(boolean value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBisel'");
    }

    @Override
    public boolean getBisel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBisel'");
    }

    @Override
    public void setGrad(boolean value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGrad'");
    }

    @Override
    public boolean getGrad() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGrad'");
    }

}
