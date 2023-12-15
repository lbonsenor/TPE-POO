package frontend;

import backend.model.Point;
import frontend.paintFigures.PaintFigure;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;

public class FigureToggleButton extends ToggleButton{
    private FigureButtonEnum figureType;

    public FigureToggleButton(FigureButtonEnum figureType){
        super(figureType.toString());
        this.figureType = figureType;
    }

    public PaintFigure getFigureBasedOnPoints(Point startPoint, Point endPoint, Color fillColor, Color borderColor){
        return figureType.getFigureBasedOnPoints(startPoint, endPoint, fillColor, borderColor);
    }
}
