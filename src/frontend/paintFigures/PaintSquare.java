package frontend.paintFigures;

import backend.model.Square;
import javafx.scene.paint.Color;

public class PaintSquare extends PaintRectangle{
    public PaintSquare(Square figure, Color fillColor, Color borderColor) {
        super(figure, fillColor, borderColor);
    }
}
