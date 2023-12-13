package frontend.gcmodel;

import java.util.Set;

import backend.model.GroupedFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
    public void setFillColor(Paint color) {
        for (GCFigure figure : this.getFiguresCopy()){
			figure.setFillColor(color);
		}
    }

    @Override
    public Paint getFillColor() {
        int iteration = 0;
        Paint commonValue = null;
        for (GCFigure figure : this.getFiguresCopy()){
			if (iteration != 0 && commonValue!=figure.getFillColor()) {
                return null;
            }
            commonValue = figure.getFillColor();
            iteration++;
		}
        return commonValue;
    }

    @Override
    public void setShadow(boolean value) {
        for (GCFigure figure : this.getFiguresCopy()){
			figure.setShadow(value);
		}
    }

    @Override
    public boolean getShadow() {
        int iteration = 0;
        boolean commonValue = true;
        for (GCFigure figure : this.getFiguresCopy()){
			if (iteration != 0 && commonValue!=figure.getShadow()) {
                return false;
            }
            commonValue = figure.getShadow();
            iteration++;
		}
        return commonValue;
    }

    @Override
    public void setBisel(boolean value) {
        for (GCFigure figure : this.getFiguresCopy()){
			figure.setBisel(value);
		}
    }

    @Override
    public boolean getBisel() {
        int iteration = 0;
        boolean commonValue = true;
        for (GCFigure figure : this.getFiguresCopy()){
			if (iteration != 0 && commonValue!=figure.getBisel()) {
                return false;
            }
            commonValue = figure.getBisel();
            iteration++;
		}
        return commonValue;
    }

    @Override
    public void setGrad(boolean value) {
        for (GCFigure figure : this.getFiguresCopy()){
			figure.setGrad(value);
		}
    }

    @Override
    public boolean getGrad() {
        int iteration = 0;
        boolean commonValue = true;
        for (GCFigure figure : this.getFiguresCopy()){
			if (iteration != 0 && commonValue!=figure.getGrad()) {
                return false;
            }
            commonValue = figure.getGrad();
            iteration++;
		}
        return commonValue;
    }    

}