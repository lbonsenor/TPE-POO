package backend;

import backend.model.Figure;
import backend.model.GroupedFigure;

import java.util.ArrayList;
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

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

}
