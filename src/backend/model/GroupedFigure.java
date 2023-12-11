package backend.model;

import java.util.HashSet;
import java.util.Set;

public class GroupedFigure<F extends Figure> implements Figure{
    private final Set<F> figures = new HashSet<>();

    public GroupedFigure(Set<F> figures){
        this.figures.addAll(figures);
    }

    @Override
    public void changePos(double diffX, double diffY){
        for (F figure : figures){
            figure.changePos(diffX, diffY);
        }
    }

    public Set<F> getFiguresCopy(){
        return new HashSet<>(figures);
    }

    @Override
    public boolean found(Point eventPoint){
        for (F figure : figures){
            if (figure.found(eventPoint)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean found(Point startPoint, Point endPoint){
        for (F figure : figures){
            if (!figure.found(startPoint, endPoint)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void rotate(){
        for (F figure : figures){
            figure.rotate();
        }
    }

    @Override
    public void scale(double multiplier){
        for (F figure : figures){
            figure.scale(multiplier);
        }
    }
    @Override
    public void flipH(){
        for (F figure : figures){
            figure.flipH();
        }
    }
    @Override
    public void flipV(){
        for (F figure : figures){
            figure.flipV();
        }
    }

    @Override
    public String toString(){
        return "Grouped Figure";
    }
}
