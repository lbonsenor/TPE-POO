package frontend;

import java.util.Collection;
import java.util.HashSet;

import frontend.paintFigures.PaintFigure;

public class GroupFigure extends HashSet<PaintFigure>{

    public GroupFigure(Collection<? extends PaintFigure> figures){
        super(figures);
    }
}
