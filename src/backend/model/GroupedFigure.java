package backend.model;

import java.util.HashSet;
import java.util.Set;

public class GroupedFigure implements Figure{
    private final Set<Figure> figures = new HashSet<>();

    public GroupedFigure(Set<Figure> figures){
        this.figures.addAll(figures);
    }

    @Override
    public void changePos(double diffX, double diffY){
        for (Figure figure : figures){
            figure.changePos(diffX, diffY);
        }
    }

    public Set<Figure> getFiguresCopy(){
        return new HashSet<>(figures);
    }

    @Override
    public boolean found(Point eventPoint){
        for (Figure figure : figures){
            if (figure.found(eventPoint)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean found(Point startPoint, Point endPoint){
        for (Figure figure : figures){
            if (!figure.found(startPoint, endPoint)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void rotate(){
        for (Figure figure : figures){
            figure.rotate();
        }
    }

    @Override
    public void scale(double multiplier){
        for (Figure figure : figures){
            figure.scale(multiplier);
        }
    }
    @Override
    public void flipH(){
        for (Figure figure : figures){
            figure.flipH();
        }
    }
    @Override
    public void flipV(){
        for (Figure figure : figures){
            figure.flipV();
        }
    }

    @Override
    public String toString(){
        return "Grouped Figure";
    }
}