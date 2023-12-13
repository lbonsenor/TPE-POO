package frontend.gcmodel;

import java.util.Set;

import backend.model.GroupedFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GCGroupedFigure extends GroupedFigure<GCFigure> implements GCFigure{

    public GCGroupedFigure(Set<GCFigure> figures) {
        super(figures);
    }

    @Override
    public void createFigure(GraphicsContext gc) {
        for (GCFigure figure : this.getFiguresCopy()){
			figure.createFigure(gc);
		}
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
