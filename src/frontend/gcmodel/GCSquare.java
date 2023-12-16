package frontend.gcmodel;

import backend.model.Square;
import javafx.scene.paint.Color;

public class GCSquare extends GCRectangle{
    public GCSquare(Square figure, Color fillColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected) {
        super(figure, fillColor, shadowSelected, gradSelected, biselSelected);
    }
}
