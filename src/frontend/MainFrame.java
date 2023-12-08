package frontend;

import backend.CanvasState;
import javafx.scene.layout.VBox;

public class MainFrame extends VBox {

    public MainFrame(CanvasState canvasState) {
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
