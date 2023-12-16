package frontend;

import backend.CanvasState;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.gcmodel.Effects;
import frontend.gcmodel.GCFigure;
import frontend.gcmodel.GCGroupedFigure;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState<GCFigure> canvasState;

	// Canvas y relacionados, se le agrega mas altura para que utilice todo el canvas 
	Canvas canvas = new Canvas(800, 665);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	
	// Constantes de dibujo
	private static final Color SELECTED_FIGURE_BORDER_COLOR = Color.RED;
	private static final Color DEFAULT_FIGURE_FILL_COLOR = Color.YELLOW;
	private static final Color DEFAULT_FIGURE_BORDER_COLOR = Color.BLACK;

	// Botones Barra Izquierda
	ToggleButton selectionButton = new ToggleButton("Seleccionar");
	FigureToggleButton rectangleButton = new FigureToggleButton(FigureButtonEnum.RECTANGLE);
	FigureToggleButton circleButton = new FigureToggleButton(FigureButtonEnum.CIRCLE);
	FigureToggleButton squareButton = new FigureToggleButton(FigureButtonEnum.SQUARE);
	FigureToggleButton ellipseButton = new FigureToggleButton(FigureButtonEnum.ELLIPSE);
	ToggleButton groupButton = new ToggleButton("Agrupar");
	ToggleButton ungroupButton = new ToggleButton("Desagrupar");
	ToggleButton rotateButton = new ToggleButton("Girar D");
	ToggleButton flipHButton = new ToggleButton("Voltear H");
	ToggleButton flipVButton = new ToggleButton("Voltear V");
	ToggleButton scalePButton = new ToggleButton("Escalar +");
	ToggleButton scaleMButton = new ToggleButton("Escalar -");
	ToggleButton deleteButton = new ToggleButton("Borrar");

	// Selector de color de relleno
	ColorPicker fillColorPicker = new ColorPicker(DEFAULT_FIGURE_FILL_COLOR);

	// Boton de Capa Izquierdo
	Label layerLabel = new Label("Capa");
	String layers[] = {"Layer 1", "Layer 2", "Layer 3"};
	ComboBox<String> currentLayer = new ComboBox<String>(FXCollections.observableArrayList(layers)); //Test
	
	// Tags
	Label tagLabel = new Label("Etiquetas");
	TextArea tagArea = new TextArea();
	Button tagButton = new Button("Guardar");

	// Dibujar una figura
	Point startPoint;
	Point dragPoint;

	// Seleccionar una figura
	Set<GCFigure> selectedFigures = new HashSet<>();

	// Panes
	StatusPane statusPane;

	// Mostrar Capas
	    Label layerShownLabel = new Label("Mostrar Capas:");
    	CheckBox layer1 = new CheckBox("Layer 1");
    	CheckBox layer2 = new CheckBox("Layer 2");
    	CheckBox layer3 = new CheckBox("Layer 3");
		CheckBox[] layersCheckBoxes = {layer1, layer2, layer3}; 

	// Mostrar Tags
		ToggleGroup tagsToggle = new ToggleGroup();
		Label showLabel = new Label("Mostrar etiquetas:");
		RadioButton showAll = new RadioButton("Todas");
		RadioButton showOnly = new RadioButton("Sólo");
		TextField showOnlyField = new TextField();

	// Barra de Selector de Efectos
		EffectsPane effectsPane = new EffectsPane();
		CheckBox shadowCheckBox = effectsPane.getShadowCheckBox();
		CheckBox gradCheckBox = effectsPane.getGradCheckBox();
		CheckBox bevelCheckBox = effectsPane.getBevelCheckBox();

	public PaintPane(CanvasState<GCFigure> canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;

		setTop(effectsPane);
		currentLayer.setValue("Layer 1");

		FigureToggleButton[] figuresButtons = {rectangleButton, circleButton, squareButton, ellipseButton};
        ToggleButton[] functionalitiesButtons = { groupButton, ungroupButton, rotateButton, flipHButton, flipVButton, scalePButton, scaleMButton, deleteButton};
		
		ToggleGroup tools = new ToggleGroup();
		selectionButton.setToggleGroup(tools);
		for (ToggleButton tool : figuresButtons) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}

		for (ToggleButton tool : functionalitiesButtons) {
			tool.setMinWidth(90);
			tool.setCursor(Cursor.HAND);
		}
		modifiersEnabled(false);

		// ----------- VBOX DE BOTONES ----------- //
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().add(selectionButton);
			selectionButton.setMinWidth(90);
			selectionButton.setCursor(Cursor.HAND);
		buttonsBox.getChildren().addAll(figuresButtons);
		buttonsBox.getChildren().addAll(functionalitiesButtons);
		buttonsBox.getChildren().add(fillColorPicker);

		buttonsBox.getChildren().addAll(layerLabel, currentLayer);

		buttonsBox.getChildren().addAll(tagLabel, tagArea, tagButton);
		tagArea.setPrefHeight(10);

		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);

		// ----------- HBOX DE LAYERS ----------- //
        HBox layerBox = new HBox(10);

        layerBox.getChildren().add(layerShownLabel);
        layerBox.getChildren().addAll(layersCheckBoxes);

        layerBox.setPadding(new Insets(5));
        layerBox.setStyle("-fx-background-color: #999");
        layerBox.setAlignment(Pos.CENTER);

		for (CheckBox checkBox : layersCheckBoxes){
			checkBox.setSelected(true);
		}
		checkOneLayer();

		// ----------- HBOX DE TAGS ----------- //
		HBox tagBox = new HBox(10);
		tagBox.getChildren().addAll(showLabel, showAll, showOnly, showOnlyField);
		tagBox.setPadding(new Insets(5));
		tagBox.setStyle("-fx-background-color: #999");
		setBottom(tagBox);
		tagBox.setAlignment(Pos.CENTER);

		showAll.setToggleGroup(tagsToggle);
		showAll.setSelected(true);
		showOnly.setToggleGroup(tagsToggle);
		tagsEnabled(false);

		// ----------- VBOX DE MOSTRAR ----------- //
		VBox shownBox = new VBox(layerBox, tagBox);
		setBottom(shownBox);
	
		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
			dragPoint = new Point(startPoint.getX(), startPoint.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());

			// si el boton de Selection esta activo busco figuras		
			if (selectionButton.isSelected()) {
				String status = "";
				boolean found = false;
				if (startPoint.equals(endPoint)) {
					for( GCFigure figure : getFigures() ){
						if (figure.found(endPoint)) {
							found = true;
							selectedFigures.clear();
							selectedFigures.add(figure);
							status = String.format("Se seleccionó %s", figure);
					}

					if (!found) {
						selectedFigures.clear();
					}
				}
				}else if (checkOneLayer() && selectedFigures.isEmpty()){
					Rectangle imaginaryRect = new Rectangle(Point.getTopLeft(startPoint, endPoint), Point.getBottomRight(startPoint, endPoint));
					for(GCFigure figure: getFigures()){
						if (figure.found(imaginaryRect)) {
							selectedFigures.add(figure);
						}
					}	
				}
				switch (selectedFigures.size()) {
					case 0:
						status = "No se seleccionó ninguna figura";
						break;

					case 1:
						tagsEnabled(true);
						status = String.format("Se seleccionó: %s", selectedFigures.iterator().next());
						break;
				
					default:
						status = String.format("Se seleccionaron %d figuras", selectedFigures.size());
						modifiersEnabled(true);
						break;
				}
				statusPane.updateStatus(status);
				onSelectionChanged();
			}
			// si el boton de Selection NO esta activo -> dibujo figura
			else{
				for (FigureToggleButton button : figuresButtons){
					if (button.isSelected()) {
						selectedFigures.clear();
						GCFigure newFigure = button.getFigureBasedOnPoints(startPoint, endPoint, fillColorPicker.getValue(), shadowCheckBox.isSelected(), gradCheckBox.isSelected(), bevelCheckBox.isSelected());
						canvasState.addFigure(newFigure, currentLayer.getValue(), new ArrayList<>());
						startPoint = null;
						redrawCanvas();
						return;
					}
				}
			}
			
});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(GCFigure figure : getFigures()) {
				if(figure.found(eventPoint)) {
					found = true;
					label.append(figure.toString());
				}
			}
			if(found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});
		
		for (CheckBox layerCheckBox : layersCheckBoxes){
				layerCheckBox.setOnAction(event -> {
					redrawCanvas();
					checkOneLayer();
					statusPane.updateStatus(String.format("%s %s", layerCheckBox.getText(), layerCheckBox.isSelected() ? "Checked":"Unchecked"));
				});
			}

		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected() && !selectedFigures.isEmpty()) {
				
				Point eventPoint = new Point(event.getX(), event.getY());

				double diffX = (eventPoint.getX() - dragPoint.getX());
				double diffY = (eventPoint.getY() - dragPoint.getY());
				for (GCFigure figure : selectedFigures){
					figure.changePos(diffX, diffY);
				}
				redrawCanvas();
				dragPoint.changePos(diffX, diffY);
			}
		});

		deleteButton.setOnAction(event -> {
			canvasState.deleteFigure(selectedFigures, currentLayer.getValue());
			selectedFigures.clear();
			enableButtons(false);
			redrawCanvas();
		});

		rotateButton.setOnAction(event -> {
			for (GCFigure figure : selectedFigures){
				figure.rotate();
			}
			redrawCanvas();
			
		});

		scalePButton.setOnAction(event->{
			for (GCFigure figure : selectedFigures){
				figure.scale(1.25);
			}
			redrawCanvas();
			
		});

		scaleMButton.setOnAction(event->{
			for (GCFigure figure : selectedFigures){
				figure.scale(0.75);
			}
			redrawCanvas();
			
		});

		flipHButton.setOnAction(event ->{
			for (GCFigure figure : selectedFigures){
				figure.flipH();
			}
			redrawCanvas();
			
		});

		flipVButton.setOnAction(event ->{
			for (GCFigure figure : selectedFigures){
				figure.flipV();
			}
			redrawCanvas();
			
		});

		groupButton.setOnAction(event ->{
			if (selectedFigures.size() > 1) {
				Set<String> tags = new HashSet<>();
				for(GCFigure figure : selectedFigures){
					tags.addAll(canvasState.getTags(figure));
				}

				String layerName = currentLayer.getValue();
				GCGroupedFigure groupedFigure = new GCGroupedFigure(selectedFigures);
				canvasState.deleteFigure(selectedFigures, layerName);
				canvasState.addFigure(groupedFigure, layerName, tags);
				
				selectedFigures.clear();
				selectedFigures.add(groupedFigure);
				redrawCanvas();
			}
		});

		ungroupButton.setOnAction(event ->{
			if (!selectedFigures.isEmpty()) {
				String layerName = currentLayer.getValue();
				for (GCFigure figure : selectedFigures){
					// Solo tiene que desagrupar si es una figura agrupada
					if (figure instanceof GCGroupedFigure) {
						GCGroupedFigure group = (GCGroupedFigure) figure;
						canvasState.addFigure(group.getFiguresCopy(), layerName, canvasState.getTags(figure));
						canvasState.deleteFigure(figure, layerName);
					}
				}
				selectedFigures.clear();
				enableButtons(false);
				redrawCanvas();
			}
			
		});

		tagButton.setOnAction(event ->{
			if (selectedFigures.size() == 1) {
				for (GCFigure figure : selectedFigures){
					canvasState.changeTags(figure, getTags());	
				}
			} 
		});

		showOnly.setOnAction(event -> {
			selectedFigures.clear();
			redrawCanvas();
		});

		showOnlyField.setOnAction(event -> {
			if (showOnly.isSelected()) {
				redrawCanvas();
			}
		});

		showAll.setOnAction(event -> {
			redrawCanvas();
		});

		shadowCheckBox.setOnAction(event ->{
			for (GCFigure figure : selectedFigures){
				figure.setEffect(Effects.SHADOW, shadowCheckBox.isSelected());
			}
			redrawCanvas();
		});

		bevelCheckBox.setOnAction(event ->{
			for (GCFigure figure : selectedFigures){
				figure.setEffect(Effects.BEVEL, bevelCheckBox.isSelected());
			}
			redrawCanvas();
		});

		gradCheckBox.setOnAction(event ->{
			for (GCFigure figure : selectedFigures){
				figure.setEffect(Effects.GRADIENT, gradCheckBox.isSelected());
			}
			redrawCanvas();
		});

		setLeft(buttonsBox);
		setRight(canvas);
	}

	private List<String> getLayersShown(){
		List<String> toReturn = new ArrayList<>();
		for (CheckBox layerCheckBox : layersCheckBoxes){
			if (layerCheckBox.isSelected()) {
				toReturn.add(layerCheckBox.getText());
			}
		}
		return toReturn;
	}

	private boolean checkOneLayer(){
		if (getLayersShown().size() != 1) {
			groupButton.setDisable(true);
			ungroupButton.setDisable(true);
			return false;
		}
		groupButton.setDisable(false);
		ungroupButton.setDisable(false);
		return true;
	}

	private Set<String> getTags(){
		return new HashSet<>(Arrays.stream(tagArea.getText().split(" |\n")).toList());
	}

	private void modifiersEnabled(boolean enabled){
		ToggleButton[] modifierArr = {rotateButton, flipHButton, flipVButton, scalePButton, scaleMButton, deleteButton};

		for (ToggleButton modifier : modifierArr){
			modifier.setDisable(!enabled);
		}
	}

	private void tagsEnabled(boolean enabled){
		if (enabled == false) {
			tagArea.setText("");
		}
		tagArea.setDisable(!enabled);
		tagButton.setDisable(!enabled);
	}

	private void enableButtons(boolean enabled){
		modifiersEnabled(enabled);
		tagsEnabled(enabled);
	}

	private Iterable<GCFigure> getFigures(){
		String currentTag = showOnlyField.getText().split(" ")[0];
		return showOnly.isSelected() ? canvasState.figures(getLayersShown(), currentTag):canvasState.figures(getLayersShown());
	}

	// cuando las figuras selecciondas sufren cambios
	private void onSelectionChanged() {
		switch (selectedFigures.size()) {
			case 0:
				enableButtons(false);
				break;
		
			case 1:
				GCFigure figure = selectedFigures.iterator().next();
				
				StringBuilder tags = new StringBuilder();
				for (String tag : canvasState.getTags(figure)){
					tags.append(tag).append("\n");
				}

				tagArea.setText(tags.toString());

				setSelectedIndeterminate(shadowCheckBox, figure.getEffect(Effects.SHADOW));
				setSelectedIndeterminate(bevelCheckBox, figure.getEffect(Effects.BEVEL));
				setSelectedIndeterminate(gradCheckBox, figure.getEffect(Effects.GRADIENT));
				
				tagsEnabled(true);
				modifiersEnabled(true);
				break;

			default:
				GCGroupedFigure group = new GCGroupedFigure(selectedFigures);

				setSelectedIndeterminate(shadowCheckBox, group.getEffect(Effects.SHADOW));
				setSelectedIndeterminate(bevelCheckBox, group.getEffect(Effects.BEVEL));
				setSelectedIndeterminate(gradCheckBox, group.getEffect(Effects.GRADIENT));

				tagsEnabled(false);
				modifiersEnabled(true);
				break;
		}
		
		redrawCanvas();
	}

	private void setSelectedIndeterminate(CheckBox checkBox, TriStateBoolean tsboolean){
		switch (tsboolean) {
			case TRUE:
				checkBox.setSelected(true);
				break;
		
			case FALSE:
				checkBox.setSelected(false);
				break;

			case UNDEFINED:
				checkBox.setIndeterminate(true);
				break;
		}
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(GCFigure figure : getFigures()) {
			gc.setFill(figure.getFillColor());
			gc.setStroke(selectedFigures.contains(figure) ? SELECTED_FIGURE_BORDER_COLOR : DEFAULT_FIGURE_BORDER_COLOR);
			figure.draw(gc);
			
		}
	}

}