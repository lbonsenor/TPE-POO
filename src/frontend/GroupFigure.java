package frontend;

import java.util.Collection;
import java.util.HashSet;

import backend.model.Figure;
import frontend.paintFigures.PaintFigure;

public class GroupFigure<F extends Figure> extends HashSet<Figure>{

    public GroupFigure(Collection<? extends PaintFigure> figures){
        super(figures);
    }
}
