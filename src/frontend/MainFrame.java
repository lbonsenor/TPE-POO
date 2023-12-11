package frontend;

import backend.CanvasState;
import frontend.gcmodel.GCFigure;
import javafx.scene.layout.VBox;

public class MainFrame extends VBox {

    public MainFrame(CanvasState<GCFigure> canvasState) {
        getChildren().add(new AppMenuBar());
        StatusPane statusPane = new StatusPane();
        TagsPane tagsPane = new TagsPane();
        //LayersPane layersPane = new LayersPane();
        getChildren().add(new PaintPane(canvasState, statusPane, tagsPane));
        //getChildren().add(layersPane);
        getChildren().add(tagsPane);
        getChildren().add(statusPane);
        
    }

}
