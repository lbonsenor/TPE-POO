package backend;

import backend.model.Figure;
import backend.model.GroupedFigure;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class CanvasState {

    //Como la naturaleza de un sistema de Capas, cada capa es un String
    private final SortedMap<String, List<Figure>> layers = new TreeMap<>();
    //private final List<Figure> list = new ArrayList<>();

    public void addFigure(Figure figure, String layer) {
        if (!layers.containsKey(layer)) {
            layers.put(layer, new ArrayList<>());
        }
        layers.get(layer).add(figure);
    }

    public void groupFigures(Set<Figure> figures, String layer){
        // Si es solo una figura, no tiene sentido agruparla
        // OBS: Puedo agrupar dos figuras agrupadas, pues 
        if (figures.size() > 1) {
            addFigure(new GroupedFigure(figures), layer);
            layers.get(layer).removeAll(figures); 
        }
    }

    public void ungroupFigures(Set<Figure> figures, String layer){
        // Puedo seleccionar más de una figura agrupada y desagrupar ambas, eso sí, si esa figura agrupada contenía una figura agrupada dentro, esta no se desagrupa
        for (Figure figure : figures){
            if (figure instanceof GroupedFigure) {
                // list.remove(figure);
                // GroupedFigure group = (GroupedFigure) figure;
                // list.addAll(group.getFiguresCopy());

                layers.get(layer).remove(figure);
                GroupedFigure group = (GroupedFigure) figure;
                layers.get(layer).addAll(group.getFiguresCopy());
            }
        }
    }

    public void deleteFigure(Figure figure, String layer) {
        layers.getOrDefault(layer, new ArrayList<>()).remove(figure);
    }

    public Iterable<Figure> figures(List<String> layers) {
        List<Figure> toReturn = new ArrayList<>();
        for (String layer : layers){
            toReturn.addAll(this.layers.getOrDefault(layer, new ArrayList<>()));
        }
        
        return toReturn;
    }

    public Iterable<Figure> figures(String layer) {
        return new ArrayList<>(layers.getOrDefault(layer, new ArrayList<>()));
    }

}
