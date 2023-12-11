package backend;

import backend.model.Figure;
import backend.model.GroupedFigure;
import backend.model.Point;
import backend.model.Rectangle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CanvasState implements Iterable<Figure>{

    private List<Figure> figuresList = new ArrayList<>();

    public void addFigure(Figure figure) {
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
    
    public boolean deleteFigure(Figure figure) {
        return figuresList.remove(figure);
    }

    // public Figure getFigureAt(Point point) {
    //     for (Figure figure : figuresList) {
    //         if (figure.contains(point)){
    //             return figure;
    //         }
    //     }

    //     return null;
    // }

    // public void getFiguresOnRectangle(Rectangle rectangle, Collection<Figure> result) {
    //     for (Figure f : figuresList) {
    //         if (f.isContainedIn(rectangle))
    //         {
    //             result.add(f);
    //         }
    //     }
    // }

    @Override
    public Iterator<Figure> iterator() {
        return figuresList.iterator();
    }

}
