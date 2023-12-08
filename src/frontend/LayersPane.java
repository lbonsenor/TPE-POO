package frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class LayersPane extends BorderPane{
    Label layerLabel = new Label("Mostrar Capas:");
    CheckBox layer1 = new CheckBox("Layer 1");
    CheckBox layer2 = new CheckBox("Layer 2");
    CheckBox layer3 = new CheckBox("Layer 3");

    public LayersPane(){
        HBox layerBox = new HBox(10);
        layerBox.getChildren().addAll(layerLabel, layer1,layer2,layer3);
        layerBox.setPadding(new Insets(5));
        layerBox.setStyle("-fx-background-color: #999");
        setBottom(layerBox);
        layerBox.setAlignment(Pos.CENTER);
    }
}
