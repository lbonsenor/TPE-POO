package frontend.gcmodel;

import java.util.Set;

import backend.model.GroupedFigure;
import javafx.scene.canvas.GraphicsContext;

public class GCGroupedFigure extends GroupedFigure<GCFigure> implements GCFigure{

    public GCGroupedFigure(Set<GCFigure> figures) {
        super(figures);
    }

    @Override
    public void createFigure(GraphicsContext gc) {
        for (GCFigure figure : this.getFiguresCopy()){
			figure.createFigure(gc);
		}
    }

}