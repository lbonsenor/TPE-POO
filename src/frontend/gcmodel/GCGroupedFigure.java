package frontend.gcmodel;

import java.util.HashSet;
import java.util.Set;

import backend.model.Point;
import backend.model.Rectangle;
import frontend.TriStateBoolean;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GCGroupedFigure extends GCFigure{

    private final Set<GCFigure> figures = new HashSet<>();

    public GCGroupedFigure(Set<GCFigure> group){
        super(null, null, false, false, false);
        this.figures.addAll(group);
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
    public void draw(GraphicsContext gc) {
        for (GCFigure figure : getFiguresCopy()){
			figure.draw(gc);
		}
    }

    @Override
    protected void shadowDraw(GraphicsContext gc) {
        for (GCFigure figure : getFiguresCopy()){
            figure.shadowDraw(gc);
        }
    }

    @Override
    protected void biselDraw(GraphicsContext gc) {
        for (GCFigure figure : getFiguresCopy()){
            figure.biselDraw(gc);
        }
    }

    @Override
    public int hashCode() {
        return figures.hashCode();
    }

    @Override
    public void setEffect(Effects effect, boolean value){
        effectMap.put(effect, TriStateBoolean.fromBoolean(value));
        for (GCFigure figure : figures){
            figure.setEffect(effect, value);
        }
    }

    @Override
    public TriStateBoolean getEffect(Effects effect){
        TriStateBoolean first = figures.iterator().next().getEffect(effect);

        for (GCFigure figure : figures){
            if (!figure.getEffect(effect).equals(first)) {
                return TriStateBoolean.UNDEFINED;
            }
        }

        return first;
    }

    @Override
    public Color getFillColor(){
        Color first = figures.iterator().next().getFillColor();

        for (GCFigure figure : figures){
            if (!figure.getFillColor().equals(first)) {
                return null;
            }
        }

        return first;
    }

    

}
