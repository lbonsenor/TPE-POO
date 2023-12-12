package frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class EffectsPane extends BorderPane{

    public CheckBox shadowCheckBox = new CheckBox("Sombra");
	public CheckBox gradCheckBox = new CheckBox("Gradiente");
	public CheckBox biselCheckBox = new CheckBox("Biselado");

	public EffectsPane() {
		setStyle("-fx-background-color: #999");

		HBox fxSelectorsBox = new HBox(10);

		CheckBox[] checkers = { shadowCheckBox, gradCheckBox, biselCheckBox };

		for (CheckBox box : checkers) {
			box.setMinWidth(70);
			box.setCursor(Cursor.HAND);
		}

		Label checkboxLabel = new Label("Efectos:");

		fxSelectorsBox.setAlignment(Pos.CENTER);
		fxSelectorsBox.setPadding(new Insets(5));
		fxSelectorsBox.getChildren().add(checkboxLabel);
		fxSelectorsBox.getChildren().addAll(checkers);
		
		setCenter(fxSelectorsBox);
		
	}
    
}
