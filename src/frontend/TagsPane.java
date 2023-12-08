package frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TagsPane extends BorderPane{

    ToggleGroup toggleGroup = new ToggleGroup();
    Label showLabel = new Label("Mostrar etiquetas:");
    RadioButton showAll = new RadioButton("Todas");
    RadioButton showOnly = new RadioButton("Sólo");
    TextField showOnlyField = new TextField();

    public TagsPane(){
        HBox tagBox = new HBox(10);
        tagBox.getChildren().addAll(showLabel, showAll, showOnly, showOnlyField);
        tagBox.setPadding(new Insets(5));
        tagBox.setStyle("-fx-background-color: #999");
        setBottom(tagBox);
        tagBox.setAlignment(Pos.CENTER);

        showAll.setToggleGroup(toggleGroup);
        showAll.setSelected(true);
        showOnly.setToggleGroup(toggleGroup);
    }
}
