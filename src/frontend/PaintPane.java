package frontend;

import backend.CanvasState;
import backend.model.GroupedFigure;
import backend.model.Point;
import frontend.gcmodel.GCFigure;
import frontend.gcmodel.GCGroupedFigure;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState<GCFigure> canvasState;

	// Canvas y relacionados, se le agrega mas altura para que utilice todo el canvas 
	Canvas canvas = new Canvas(800, 665);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = Color.BLACK;
	Color defaultFillColor = Color.YELLOW;

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
	ColorPicker fillColorPicker = new ColorPicker(defaultFillColor);

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

	// Seleccionar una figura
	Set<GCFigure> selectedFigures = new HashSet<>();

	// Panes
	StatusPane statusPane;

	// Barra de selector de efectos
	EffectsPane effectsPane;

	// Mostrar Capas
	    Label layerShownLabel = new Label("Mostrar Capas:");
    	CheckBox layer1 = new CheckBox("Layer 1");
    	CheckBox layer2 = new CheckBox("Layer 2");
    	CheckBox layer3 = new CheckBox("Layer 3");
		CheckBox[] layersCheckBoxes = {layer1, layer2, layer3}; 

	// Mostrar Tags
		ToggleGroup toggleGroup = new ToggleGroup();
		Label showLabel = new Label("Mostrar etiquetas:");
		RadioButton showAll = new RadioButton("Todas");
		RadioButton showOnly = new RadioButton("Sólo");
		TextField showOnlyField = new TextField();

	public PaintPane(CanvasState<GCFigure> canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		this.effectsPane = new EffectsPane();
		effectsPane.shadowCheckBox.selectedProperty().addListener(this::onShadowChange);
		effectsPane.gradCheckBox.selectedProperty().addListener(this::onGradChange);
		effectsPane.biselCheckBox.selectedProperty().addListener(this::onBiselChange);
		setTop(effectsPane);

		currentLayer.setValue("Layer 1");

		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton,groupButton, ungroupButton, rotateButton, flipHButton, flipVButton, scalePButton, scaleMButton, deleteButton};
		FigureToggleButton[] figuresArr = {rectangleButton, circleButton, squareButton, ellipseButton};
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		// ----------- VBOX DE BOTONES ----------- //
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
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

		// ----------- HBOX DE TAGS ----------- //
		HBox tagBox = new HBox(10);
		tagBox.getChildren().addAll(showLabel, showAll, showOnly, showOnlyField);
		tagBox.setPadding(new Insets(5));
		tagBox.setStyle("-fx-background-color: #999");
		setBottom(tagBox);
		tagBox.setAlignment(Pos.CENTER);

		showAll.setToggleGroup(toggleGroup);
		showAll.setSelected(true);
		showOnly.setToggleGroup(toggleGroup);

		// ----------- VBOX DE MOSTRAR ----------- //
		VBox shownBox = new VBox(layerBox, tagBox);
		setBottom(shownBox);
	
		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null /*|| (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY())*/) {
				return ;
			}		

			//Un rectangulo imaginario. Solo se acepta la seleccion multiple si es una sola capa
			if (selectionButton.isSelected()){
				boolean found = false;
				if (checkOneLayer("Seleccion Multiple")) {
					for (GCFigure figure : canvasState.figures(currentLayer.getValue())){
						if (figure.found(startPoint, endPoint)) {
							found = true;
							selectedFigures.add((GCFigure) figure);
							showEffectsOnFigure(figure);
						}
					}
					if (found) statusPane.updateStatus("Figuras seleccionadas mediante Seleccion Multiple");
					else statusPane.updateStatus("Ninguna figura encontrada");
				}
			}else{
				GCFigure newFigure = null;
				for(FigureToggleButton button : figuresArr){
					if (button.isSelected()) {
						newFigure = button.getFigureBasedOnPoints(startPoint, endPoint);
						newFigure.setFillColor(fillColorPicker.getValue());
						newFigure.setShadow(effectsPane.shadowCheckBox.isSelected());
						newFigure.setGrad(effectsPane.gradCheckBox.isSelected());
						newFigure.setBisel(effectsPane.biselCheckBox.isSelected());
						canvasState.addFigure(newFigure, currentLayer.getValue());
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
			for(GCFigure figure : canvasState.figures(getLayersShown())) {
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

		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;

				// setOnMouseClicked no verifica si es literalmente un click, por lo tanto se verifica si startPoint es el mismo que endPoint
				if (eventPoint.equals(startPoint)) {
					selectedFigures = new HashSet<>(); // Creo un nuevo HashSet para que no se seleccione
					StringBuilder label = new StringBuilder("Se seleccionó: ");
					for (GCFigure figure : canvasState.figures(currentLayer.getValue())) {
						if(figure.found(eventPoint)) {
							found = true;
							selectedFigures = new HashSet<>();
							selectedFigures.add((GCFigure) figure);
							showEffectsOnFigure(figure);
							label.append(figure.toString());
						}
					}
					if (found) {
						statusPane.updateStatus(label.toString());
					} else {
						statusPane.updateStatus("Ninguna figura encontrada");
					}
				}
				
				redrawCanvas();
			}
		});
		
		for (CheckBox layerCheckBox : layersCheckBoxes){
				layerCheckBox.setOnAction(event -> {
					redrawCanvas();
					statusPane.updateStatus(String.format("%s %s", layerCheckBox.getText(), layerCheckBox.isSelected() ? "Checked":"Unchecked"));
				});
			}

		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected() && selectedFigures!=null) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
				for (GCFigure figure : selectedFigures){
					figure.changePos(diffX, diffY);
					redrawCanvas();
				}
			}
		});

		deleteButton.setOnAction(event -> {
			if (selectedFigures != null) {
				canvasState.deleteFigure(selectedFigures, currentLayer.getValue());
				redrawCanvas();
			}
		});

		rotateButton.setOnAction(event -> {
			if (selectedFigures != null) {
				for (GCFigure figure : selectedFigures){
					figure.rotate();
				}
				redrawCanvas();
			}
		});

		scalePButton.setOnAction(event->{
			if (selectedFigures != null) {
				for (GCFigure figure : selectedFigures){
					figure.scale(1.25);
				}
				redrawCanvas();
			}
		});

		scaleMButton.setOnAction(event->{
			if (selectedFigures != null) {
				for (GCFigure figure : selectedFigures){
					figure.scale(0.75);
				}
				redrawCanvas();
			}
		});

		flipHButton.setOnAction(event ->{
			if (selectedFigures != null) {
				for (GCFigure figure : selectedFigures){
					figure.flipH();
				}
				redrawCanvas();
			}
		});

		flipVButton.setOnAction(event ->{
			if (selectedFigures != null) {
				for (GCFigure figure : selectedFigures){
					figure.flipV();
				}
				redrawCanvas();
			}
		});

		groupButton.setOnAction(event ->{
			if (selectedFigures != null) {
				if (checkOneLayer("Agrupar")) {
					String layerName = currentLayer.getValue();
					GCGroupedFigure groupedFigure = new GCGroupedFigure(selectedFigures);
					canvasState.deleteFigure(selectedFigures, layerName);
					canvasState.addFigure(groupedFigure, layerName);
				}
			}
		});

		ungroupButton.setOnAction(event ->{
			if (selectedFigures != null) {
				if (checkOneLayer("Desagrupar")) {
					String layerName = currentLayer.getValue();
					for (GCFigure figure : selectedFigures){
						// Solo tiene que desagrupar si es una figura agrupada
						if (figure instanceof GroupedFigure) {
							canvasState.deleteFigure(figure, layerName);
							GCGroupedFigure group = (GCGroupedFigure) figure;
							canvasState.addFigure(group.getFiguresCopy(), layerName);
						}
					}
				}
			}
			
		});

		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(GCFigure figure : canvasState.figures(getLayersShown())) {
				if (selectedFigures != null && selectedFigures.contains(figure)) {
					gc.setStroke(Color.RED);
				} else {
					gc.setStroke(lineColor);
				}
				//gc.setFill(figure.getFillColor());
				figure.createFigure(gc);
		}
	}

	private void showEffectsOnFigure(GCFigure figure){
		if (figure.getShadow()) {
			effectsPane.shadowCheckBox.setSelected(figure.getShadow());
		}else{
			effectsPane.shadowCheckBox.setIndeterminate(!figure.getShadow());
		}
		if (figure.getGrad()) {
			effectsPane.gradCheckBox.setSelected(figure.getGrad());
		}else{
			effectsPane.gradCheckBox.setIndeterminate(!figure.getGrad());
		}
		if (figure.getBisel()) {
			effectsPane.biselCheckBox.setSelected(figure.getBisel());
		}else{
			effectsPane.biselCheckBox.setIndeterminate(!figure.getBisel());
		}
		
	}

	private void onShadowChange(ObservableValue<? extends Boolean> observableValue, Boolean selector, Boolean newValue) {
		if (newValue == null)
			return;

		if (!selectedFigures.isEmpty()) {
			for (GCFigure figure : selectedFigures)
				figure.setShadow(newValue);
			redrawCanvas();
		}
	}

	private void onGradChange(ObservableValue<? extends Boolean> observableValue, Boolean selector, Boolean newValue) {
		if (newValue == null)
			return;

		if (!selectedFigures.isEmpty()) {
			for (GCFigure figure : selectedFigures)
				figure.setGrad(newValue);
			redrawCanvas();
		}
	}

	private void onBiselChange(ObservableValue<? extends Boolean> observableValue, Boolean selector, Boolean newValue) {
		if (newValue == null)
			return;

		if (!selectedFigures.isEmpty()) {
			for (GCFigure figure : selectedFigures)
				figure.setBisel((newValue));
			redrawCanvas();
		}
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

	public boolean checkOneLayer(String button){
		if (getLayersShown().size() != 1) {
			statusPane.updateStatus(String.format("%s esta deshabilitado si mas de una capa esta siendo mostrada", button));
			return false;
		}
		return true;
	}

}