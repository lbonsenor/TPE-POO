package backend;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class CanvasState<F extends Figure> {

    //Como la naturaleza de un sistema de Capas, cada capa es un String
    private final SortedMap<String, Set<F>> layers = new TreeMap<>();
    private final Map<F, Set<String>> tags = new HashMap<>();

    public void addFigure(F figure, String layer, Collection<String> tags) {
        if (!layers.containsKey(layer)) {
            layers.put(layer, new HashSet<>());
        }
        layers.get(layer).add(figure);

        this.tags.put(figure, new HashSet<>(tags));
    }

    public void addFigure(Collection<F> figures, String layer, Collection<String> tags){
        if (!layers.containsKey(layer)) {
            layers.put(layer, new HashSet<>());
        }
        layers.get(layer).addAll(figures);
        
        for (F figure : figures){
            this.tags.put(figure, new HashSet<>(tags));
        }
    }

    public void addFigure(F figure, String layer) {
        addFigure(figure, layer, new HashSet<>());
    }

    public void deleteFigure(F figure, String layer) {
        layers.getOrDefault(layer, new HashSet<>()).remove(figure);
        tags.remove(figure);
    }

    public void deleteFigure(Collection<F> figures, String layer){
        layers.getOrDefault(layer, new HashSet<>()).removeAll(figures);
        
        for (F figure : figures){
            tags.remove(figure);
        }
    }

    public Iterable<F> figures(Collection<String> layers) {
        List<F> toReturn = new ArrayList<>();
        for (String layer : layers){
            toReturn.addAll(this.layers.getOrDefault(layer, new HashSet<>()));
        }
        
        return toReturn;
    }

    public Iterable<F> figures(String layer) {
        return new ArrayList<>(layers.getOrDefault(layer, new HashSet<>()));
    }

    public Iterable<F> figures(Collection<String> layers, String tag){
        List<F> toReturn = new ArrayList<>();
        for (F figure : figures(layers)){
            if (this.tags.getOrDefault(figure, new HashSet<String>()).contains(tag)) {
                toReturn.add(figure);
            }
        }

        return toReturn;
    }

    public Collection<String> getTags(F figure){
        return new ArrayList<>(tags.get(figure));
    }

    public void changeTags(F figure, Collection<String> tags){
        if (this.tags.containsKey(figure)) {
            this.tags.put(figure, new HashSet<>(tags));
        }
    }

}