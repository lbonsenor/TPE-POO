package frontend;

import java.util.ArrayList;
import java.util.Collection;

import frontend.paintFigures.PaintFigure;

public class GroupFigure extends ArrayList<PaintFigure>{

    public GroupFigure(Collection<? extends PaintFigure> figures){
        super(figures);
    }
}
