package backend;

import backend.model.Figure;
import backend.model.FigureEnum;
import backend.model.Point;
import javafx.scene.control.ToggleButton;

public class FigureToggleButton extends ToggleButton{
    private FigureEnum figureType;

    public FigureToggleButton(FigureEnum figureType){
        super(figureType.toString());
        this.figureType = figureType;
    }

    public Figure getFigureBasedOnPoints(Point startPoint, Point endPoint){
        return figureType.getFigureBasedOnPoints(startPoint, endPoint);
    }
}
