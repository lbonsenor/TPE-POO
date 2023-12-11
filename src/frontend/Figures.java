package frontend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import backend.CanvasState;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.paintFigures.PaintFigure;
import frontend.paintFigures.PaintRectangle;

public class Figures extends ArrayList<PaintFigure>{

    private CanvasState canvasState;

    public Figures(CanvasState canvasState){
        this.canvasState = canvasState;
    }
    
    public void addFigure(PaintFigure figure) {
        super.add(figure);
        canvasState.addFigure(figure.getModel());
    }
    
    // borrar con conjunto de figuras nos ahorra iterar el conjunto de figuras original
    // ventaja? -> mas claridad de codigo y reutilizacion de metodos de List<>
    public void deleteFigures(Collection<PaintFigure> figures) {
        for(PaintFigure figure : figures){
            canvasState.deleteFigure(figure.getModel());
        }
        super.removeAll(figures);
    }

    public PaintFigure getFigureAt(Point point) {
        Iterator<PaintFigure> iterator = this.iterator();
        while (iterator.hasNext()) {
            PaintFigure figure = iterator.next();
            if (figure.contains(point)) {
                return figure;
            }
        }
        return null;
    }

    public void getFiguresOnRectangle(Rectangle rectangle, Collection<PaintFigure> result) {

        Iterator<PaintFigure> iterator = this.iterator();
        while (iterator.hasNext()) {
            PaintFigure figure = iterator.next();
            if (figure.isContainedIn(rectangle)) {
                result.add(figure);
            }
        }
    }

    public void groupFigures(Set<PaintFigure> figures){

    }

    public void ungroupFigures(Set<PaintFigure> figures){
        
    }
}
