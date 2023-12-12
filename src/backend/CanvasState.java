package backend;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class CanvasState<F extends Figure> {

    //Como la naturaleza de un sistema de Capas, cada capa es un String
    private final SortedMap<String, List<F>> layers = new TreeMap<>();

    public void addFigure(F figure, String layer) {
        if (!layers.containsKey(layer)) {
            layers.put(layer, new ArrayList<>());
        }
        layers.get(layer).add(figure);
    }

    public void addFigure(Collection<F> figures, String layer){
        if (!layers.containsKey(layer)) {
            layers.put(layer, new ArrayList<>());
        }
        layers.get(layer).addAll(figures);
    }

    public void deleteFigure(F figure, String layer) {
        layers.getOrDefault(layer, new ArrayList<>()).remove(figure);
    }

    public void deleteFigure(Collection<F> figures, String layer){
        layers.getOrDefault(layer, new ArrayList<>()).removeAll(figures);
    }

    public Iterable<F> figures(Collection<String> layers) {
        List<F> toReturn = new ArrayList<>();
        for (String layer : layers){
            toReturn.addAll(this.layers.getOrDefault(layer, new ArrayList<>()));
        }
        
        return toReturn;
    }

    public Iterable<F> figures(String layer) {
        return new ArrayList<>(layers.getOrDefault(layer, new ArrayList<>()));
    }

    public Iterable<F> figures(Collection<String> layers, Collection<String> tags){
        List<F> list = new ArrayList<>();
        return list;
    }

}
