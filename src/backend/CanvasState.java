package backend;

import backend.model.Figure;

import java.util.*;

public class CanvasState<F extends Figure> {

    //Como la naturaleza de un sistema de Capas, cada capa es un String
    private final SortedMap<String, List<F>> layers = new TreeMap<>();
    //private final SortedMap<F, List<String>> keys = new TreeMap<>();
    //private final SortedMap<String, List<F>>

    //Tags. Let's see how this goes...
    private final Map<F, Set<String>> tags = new HashMap<>();

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

    //Obtengo de entrada selectedFigures, y asocio la figura al tag.
    public void changeTag(Collection<F> figures,Collection<String> tag){
            for (F figure : figures) {
                //Siempre vacio los tags que tenia antes. Salvo que quiero mostrarlos nomas.
                tags.put(figure, new HashSet<>());
                tags.get(figure).addAll(tag);
            }
    }
    public String getTags(Collection<F> figures){
            StringBuilder accum = new StringBuilder();
            for (F figure: figures){ //Para silenciar el Warning, por eso lo escribi aca.
            tags.getOrDefault(figure,new HashSet<>()).forEach(string -> accum.append(string).append(" "));
            }
            return accum.toString();
    }

    public boolean hasOne(Collection<F> figures){
        return figures.size()==1;
    }

    public void deleteFigure(F figure, String layer) {
        layers.getOrDefault(layer, new ArrayList<>()).remove(figure);
    }

    public void deleteFigure(Collection<F> figures, String layer){
        layers.getOrDefault(layer, new ArrayList<>()).removeAll(figures);
    }

    public Iterable<F> figures(List<String> layers) {
        List<F> toReturn = new ArrayList<>();
        for (String layer : layers){
            toReturn.addAll(this.layers.getOrDefault(layer, new ArrayList<>()));
        }
        
        return toReturn;
    }

    public Iterable<F> figures(String layer) {
        return new ArrayList<>(layers.getOrDefault(layer, new ArrayList<>()));
    }
/*
    public Iterable<F> figures(String layer, Collection<String> tags){
        List<F> list = new ArrayList<>();
        return list;
    }
 */

}
