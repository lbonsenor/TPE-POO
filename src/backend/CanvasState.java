package backend;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.GroupFigure;
import frontend.paintFigures.PaintFigure;
import javafx.scene.paint.Paint;

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
    private final Set<PaintFigure> list = new HashSet<>();

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

    //metodos provisorios para testing
    public void addFigure(PaintFigure figure) {
        list.add(figure);
    }

    public boolean deleteFigures(Collection<PaintFigure> figures) {
        return list.removeAll(figures);
    }

    public void clear() {
        list.clear();
    }

    /**
     * Gets the Figure with the lowest depth that contains the given point, or null if none was found.
     */
    public PaintFigure getFigureAt(Point point) {
        for (PaintFigure paintFigure : list) {
            if (paintFigure.contains(point)) {
                return paintFigure;
            }
        }

        return null;
    }

    /**
     * Adds all the figures that are fully contained within a given rectangle to the specified collection.
     * Returns the amount of figures that were added to the collection.
     */
    public int getFiguresOnRectangle(Rectangle rectangle, Collection<PaintFigure> result) {
        int count = 0;
        for (PaintFigure f : list) {
            if (f.isContainedIn(rectangle))
            {
                result.add(f);
                count++;
            }
        }
        return count;
    }

    /**
     * Returns an iterator through the Figures in this CanvasState in descending depth order (from bottom to top).
     */
    public Iterator<PaintFigure> iterator() {
        return list.iterator();
    }

    public Iterable<PaintFigure> figures() {
        return new ArrayList<>(list);
    }


    //VERSION EXPERIMENTAL GROUPFIGURES
    public void groupFigures(Collection<PaintFigure> selected){
        GroupFigure newGroup = new GroupFigure(selected);
        for (PaintFigure paintFigure : selected) {
            if (paintFigure.getGroupFigure() == null) {
                paintFigure.setGroupFigure(newGroup);
            }
        }
    }

    public void ungroupFigures(Collection<PaintFigure> selected){
        for (PaintFigure paintFigure : selected) {
            if (paintFigure.getGroupFigure() != null) {
                paintFigure.getGroupFigure().clear();
                paintFigure.setGroupFigure(null);
            }
        }
    }

}