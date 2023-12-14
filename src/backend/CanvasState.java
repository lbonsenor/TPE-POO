package backend;

import backend.model.Figure;
import backend.model.Rectangle;
import frontend.paintFigures.PaintFigure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class CanvasState<F extends Figure> {

    //Como la naturaleza de un sistema de Capas, cada capa es un String
    private final SortedMap<String, List<F>> layers = new TreeMap<>();
    private final Map<F, Set<String>> tags = new HashMap<>();

    //lista provisoria para testing
    

    public void addFigure(F figure, String layer, Collection<String> tags) {
        if (!layers.containsKey(layer)) {
            layers.put(layer, new ArrayList<>());
        }
        layers.get(layer).add(figure);

        this.tags.put(figure, new HashSet<>(tags));
    }

    public void addFigure(Collection<F> figures, String layer, Collection<String> tags){
        if (!layers.containsKey(layer)) {
            layers.put(layer, new ArrayList<>());
        }
        layers.get(layer).addAll(figures);
        
        for (F figure : figures){
            this.tags.put(figure, new HashSet<>(tags));
        }
    }

    public void deleteFigure(Collection<F> figures, String layer){
        layers.getOrDefault(layer, new ArrayList<>()).removeAll(figures);
        
        for (F figure : figures){
            tags.remove(figure);
        }
    }

    // muestra las figuras de todos los layers indicados
    public Iterable<F> figures(Collection<String> layers) {
        List<F> toReturn = new ArrayList<>();
        for (String layer : layers){
            toReturn.addAll(figures(layer));
        }
        
        return toReturn;
    }

    // retorna las figuras de un layer especifico
    public Collection<F> figures(String layer) {
        return new ArrayList<>(layers.getOrDefault(layer, new ArrayList<>()));
    }
    
    // retorna figuras de todos los layers con el tag indicado
    public Collection<F> figures(Collection<String> layers, String tag){
        List<F> toReturn = new ArrayList<>();
        for (F figure : figures(layers)){
            if (this.tags.getOrDefault(figure, new HashSet<String>()).contains(tag)) {
                toReturn.add(figure);
            }
        }

        return toReturn;
    }

    public Iterable<String> getTags(F figure){
        return new ArrayList<>(tags.get(figure));
    }

    public void changeTags(F figure, Collection<String> tags){
        if (this.tags.containsKey(figure)) {
            this.tags.put(figure, new HashSet<>(tags));
        }
    }

    public void getFiguresOnRectangle(Rectangle rectangle, Collection<PaintFigure> params, Collection<PaintFigure> result) {

        for (PaintFigure paintFigure : params) {
            if (paintFigure.isContainedIn(rectangle)) {
                result.add(paintFigure);
            }
        }

    }

}