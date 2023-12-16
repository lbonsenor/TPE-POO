package frontend.gcmodel;

import java.util.Collection;
import java.util.HashSet;

public class GroupFigure extends HashSet<GCFigure>{

    public GroupFigure(Collection<GCFigure> figures){
        super(figures);
    }
}
