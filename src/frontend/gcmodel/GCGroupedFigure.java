package frontend.gcmodel;

import java.util.HashSet;
import java.util.Set;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class GCGroupedFigure implements GCFigure{

    private final Set<GCFigure> figures = new HashSet<>();

    public GCGroupedFigure(Set<GCFigure> figures){
        this.figures.addAll(figures);
    }

    @Override
    public void changePos(double diffX, double diffY){
        for (GCFigure figure : figures){
            figure.changePos(diffX, diffY);
        }
    }

    public Set<GCFigure> getFiguresCopy(){
        return new HashSet<>(figures);
    }

    @Override
    public boolean found(Point eventPoint){
        for (GCFigure figure : figures){
            if (figure.found(eventPoint)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean found(Rectangle rectangle){
        for (GCFigure figure : figures){
            if (!figure.found(rectangle)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void rotate(){
        for (GCFigure figure : figures){
            figure.rotate();
        }
    }

    @Override
    public void scale(double multiplier){
        for (GCFigure figure : figures){
            figure.scale(multiplier);
        }
    }
    @Override
    public void flipH(){
        for (GCFigure figure : figures){
            figure.flipH();
        }
    }
    @Override
    public void flipV(){
        for (GCFigure figure : figures){
            figure.flipV();
        }
    }

    @Override
    public String toString(){
        return "Grouped Figure";
    }


    @Override
    public void createFigure(GraphicsContext gc) {
        for (GCFigure figure : getFiguresCopy()){
			figure.createFigure(gc);
		}
    }

}
