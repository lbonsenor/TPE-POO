package frontend.gcmodel;

import backend.model.Square;
import javafx.scene.paint.Color;

public class GCSquare extends GCRectangle{
    public GCSquare(Square figure, Color fillColor, Color borderColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected) {
        super(figure, fillColor, borderColor, shadowSelected, gradSelected, biselSelected);
    }
}
