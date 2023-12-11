package backend;

import backend.model.Figure;
import backend.model.GroupedFigure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.paintFigures.PaintFigure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CanvasState implements Iterable<PaintFigure>{

    private List<PaintFigure> figuresList = new ArrayList<>();

    public void addFigure(PaintFigure figure) {
        figuresList.add(figure);
    }

    public int listSize(){
        return figuresList.size();
    }

    // public void groupFigures(Set<Figure> figures){
    //     // Si es solo una figura, no tiene sentido agruparla
    //     // OBS: Puedo agrupar dos figuras agrupadas, pues 
    //     if (figures.size() > 1) {
    //         figuresList.add(new GroupedFigure(figures));
    //         figuresList.removeAll(figures); 
    //     }
    // }

    // public void ungroupFigures(Set<Figure> figures){
    //     // Puedo seleccionar más de una figura agrupada y desagrupar ambas, eso sí, si esa figura agrupada contenía una figura agrupada dentro, esta no se desagrupa
    //     for (Figure figure : figures){
    //         if (figure instanceof GroupedFigure) {
    //             figuresList.remove(figure);
    //             GroupedFigure group = (GroupedFigure) figure;
    //             figuresList.addAll(group.getFiguresCopy());
    //         }
    //     }
    // }
    
    // borrar con conjunto de figuras nos ahorra iterar el conjunto de figuras original
    // ventaja? -> mas claridad de codigo y reutilizacion de metodos de List<>
    public boolean deleteFigures(Collection<PaintFigure> figures) {
        return figuresList.removeAll(figures);
    }

    public Figure getFigureAt(Point point) {
        for (PaintFigure figure : figuresList) {
            if (figure.contains(point)){
                return figure;
            }
        }

        return null;
    }

    public void getFiguresOnRectangle(Rectangle rectangle, Collection<PaintFigure> result) {
        for (PaintFigure f : figuresList) {
            if (f.isContainedIn(rectangle))
            {
                result.add(f);
            }
        }
    }

    @Override
    public Iterator<PaintFigure> iterator() {
        return figuresList.iterator();
    }

}
