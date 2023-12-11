package backend;

import backend.model.Figure;
import backend.model.GroupedFigure;
import backend.model.Point;
import backend.model.Rectangle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CanvasState implements Iterable<Figure>{

    private final List<Figure> list = new ArrayList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    public void groupFigures(Set<Figure> figures){
        // Si es solo una figura, no tiene sentido agruparla
        // OBS: Puedo agrupar dos figuras agrupadas, pues 
        if (figures.size() > 1) {
            list.add(new GroupedFigure(figures));
            list.removeAll(figures); 
        }
    }

    public void ungroupFigures(Set<Figure> figures){
        // Puedo seleccionar más de una figura agrupada y desagrupar ambas, eso sí, si esa figura agrupada contenía una figura agrupada dentro, esta no se desagrupa
        for (Figure figure : figures){
            if (figure instanceof GroupedFigure) {
                list.remove(figure);
                GroupedFigure group = (GroupedFigure) figure;
                list.addAll(group.getFiguresCopy());
            }
        }
    }
    
    // borrar con conjunto de figuras nos ahorra iterar el conjunto de figuras original
    // ventaja? -> mas claridad de codigo y reutilizacion de metodos de List<>
    public boolean deleteFigures(Collection<Figure> figures) {
        return list.removeAll(figures);
    }

    public Figure getFigureAt(Point point) {
        for (Figure figure : list) {
            if (figure.contains(point)){
                return figure;
            }
        }

        return null;
    }

    public void getFiguresOnRectangle(Rectangle rectangle, Collection<Figure> result) {
        for (Figure f : list) {
            if (f.isContainedIn(rectangle))
            {
                result.add(f);
            }
        }
    }

    @Override
    public Iterator<Figure> iterator() {
        return list.iterator();
    }

}
