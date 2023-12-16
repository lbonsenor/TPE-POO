package frontend;

import java.util.Collection;
import java.util.HashSet;

import frontend.gcmodel.GCFigure;

public class GroupFigure extends HashSet<GCFigure>{

    public GroupFigure(Collection<GCFigure> figures){
        super(figures);
    }
}
