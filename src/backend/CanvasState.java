package backend;

import backend.model.Figure;
import backend.model.GroupedFigure;
import backend.model.Point;
import backend.model.Rectangle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CanvasState {

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

    public void deleteFigure(Figure figure) {
        list.remove(figure);
    }

    // borrar con conjunto de figuras nos ahorra iterar el conjunto de figuras original
    // ventaja? -> mas claridad de codigo y reutilizacion de metodos de List<>
    public boolean deleteFigures(Collection<Figure> figures) {
        return list.removeAll(figures);
    }

    public Figure getFigureAt(Point point) {
        for (int i = list.size() - 1; i >= 0; i--) {
            Figure f = list.get(i);
            if (f.contains(point))
                return f;
        }

        return null;
    }

    public int getFiguresOnRectangle(Rectangle rectangle, Collection<Figure> result) {
        int count = 0;
        for (Figure f : list) {
            if (f.isContainedIn(rectangle))
            {
                result.add(f);
                count++;
            }
        }
        return count;
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

}
