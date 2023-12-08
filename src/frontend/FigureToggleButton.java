package frontend;

import backend.model.Figure;
import backend.model.Point;
import javafx.scene.control.ToggleButton;

public class FigureToggleButton extends ToggleButton{
    private FigureButtonEnum figureType;

    public FigureToggleButton(FigureButtonEnum figureType){
        super(figureType.toString());
        this.figureType = figureType;
    }

    public Figure getFigureBasedOnPoints(Point startPoint, Point endPoint){
        return figureType.getFigureBasedOnPoints(startPoint, endPoint);
    }
}
