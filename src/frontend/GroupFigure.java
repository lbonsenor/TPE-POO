package frontend;

import java.util.Collection;
import java.util.HashSet;

import backend.model.Figure;
import frontend.gcmodel.GCFigure;

public class GroupFigure<F extends Figure> extends HashSet<GCFigure>{

    public GroupFigure(Collection<? extends GCFigure> figures){
        super(figures);
    }
}
