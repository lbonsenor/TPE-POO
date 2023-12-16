package frontend;

import backend.model.Point;
import frontend.gcmodel.GCFigure;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;

public class FigureToggleButton extends ToggleButton{
    private FigureButtonEnum figureType;

    public FigureToggleButton(FigureButtonEnum figureType){
        super(figureType.toString());
        this.figureType = figureType;
    }

    public GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint, Color fillColor, Color borderColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected){
        return figureType.getFigureBasedOnPoints(startPoint, endPoint, fillColor, borderColor, shadowSelected, gradSelected, biselSelected);
    }
}
