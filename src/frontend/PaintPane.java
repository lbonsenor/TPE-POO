package frontend;

import backend.CanvasState;
import backend.model.*;
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
import javafx.scene.control.TextArea;
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
	CanvasState canvasState;

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
	Set<Figure> selectedFigures = new HashSet<>();

	// Panes
	StatusPane statusPane;
	TagsPane tagsPane;

	// Colores de relleno de cada figura
	Map<Figure, Color> figureColorMap = new HashMap<>();

	// Mostrar Capas
	    Label layerShownLabel = new Label("Mostrar Capas:");
    	CheckBox layer1 = new CheckBox("Layer 1");
    	CheckBox layer2 = new CheckBox("Layer 2");
    	CheckBox layer3 = new CheckBox("Layer 3");
		CheckBox[] layersCheckBoxes = {layer1, layer2, layer3}; 

	public PaintPane(CanvasState canvasState, StatusPane statusPane, TagsPane tagsPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		//this.layersPane = layersPane;
		this.tagsPane = tagsPane;

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
        setBottom(layerBox);
        layerBox.setAlignment(Pos.CENTER);

		for (CheckBox checkBox : layersCheckBoxes){
			checkBox.setSelected(true);
		}

		// -------------------------------------- //
	
		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null /*|| (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY())*/) {
				return ;
			}
			
			Figure newFigure = null;
			for(FigureToggleButton button : figuresArr){
				if (button.isSelected()) {
					newFigure = button.getFigureBasedOnPoints(startPoint, endPoint);
					figureColorMap.put(newFigure, fillColorPicker.getValue());
					canvasState.addFigure(newFigure, currentLayer.getValue());
					startPoint = null;
					redrawCanvas();
					return;
				}
			}

			//Un rectangulo imaginario. Solo se acepta la seleccion multiple si es una sola capa
			if (selectionButton.isSelected()){
				boolean found = false;
				if (checkOneLayer("Seleccion Multiple")) {
					for (Figure figure : canvasState.figures(currentLayer.getValue())){
						if (figure.found(startPoint, endPoint)) {
							found = true;
							selectedFigures.add(figure);
						}
					}
					if (found) statusPane.updateStatus("Figuras seleccionadas mediante Seleccion Multiple");
					else statusPane.updateStatus("Ninguna figura encontrada");
				}
			}
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(Figure figure : canvasState.figures(getLayersShown())) {
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
					for (Figure figure : canvasState.figures(currentLayer.getValue())) {
						if(figure.found(eventPoint)) {
							found = true;
							selectedFigures = new HashSet<>();
							selectedFigures.add(figure);
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
				for (Figure figure : selectedFigures){
					figure.changePos(diffX, diffY);
					redrawCanvas();
				}
			}
		});

		deleteButton.setOnAction(event -> {
			if (selectedFigures != null) {
				for (Figure figure : selectedFigures){
					canvasState.deleteFigure(figure, currentLayer.getValue());
				}
				redrawCanvas();
			}
		});

		rotateButton.setOnAction(event -> {
			if (selectedFigures != null) {
				for (Figure figure : selectedFigures){
					figure.rotate();
				}
				redrawCanvas();
			}
		});

		scalePButton.setOnAction(event->{
			if (selectedFigures != null) {
				for (Figure figure : selectedFigures){
					figure.scale(1.25);
				}
				redrawCanvas();
			}
		});

		scaleMButton.setOnAction(event->{
			if (selectedFigures != null) {
				for (Figure figure : selectedFigures){
					figure.scale(0.75);
				}
				redrawCanvas();
			}
		});

		flipHButton.setOnAction(event ->{
			if (selectedFigures != null) {
				for (Figure figure : selectedFigures){
					figure.flipH();
				}
				redrawCanvas();
			}
		});

		flipVButton.setOnAction(event ->{
			if (selectedFigures != null) {
				for (Figure figure : selectedFigures){
					figure.flipV();
				}
				redrawCanvas();
			}
		});

		groupButton.setOnAction(event ->{
			if (selectedFigures != null) {
				if (checkOneLayer("Agrupar")) {
					canvasState.groupFigures(selectedFigures, currentLayer.getValue());
					redrawCanvas();
				}
			}
		});

		ungroupButton.setOnAction(event ->{
			if (selectedFigures != null) {
				if (checkOneLayer("Desagrupar")) {
					canvasState.ungroupFigures(selectedFigures, currentLayer.getValue());
					redrawCanvas();
				}
			}
			
		});

		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : canvasState.figures(getLayersShown())) {
				if (selectedFigures != null && selectedFigures.contains(figure)) {
					gc.setStroke(Color.RED);
				} else {
					gc.setStroke(lineColor);
				}
				gc.setFill(figureColorMap.get(figure));
				createFigure(gc, figure);
		}
		}

	// Como GraphicsContext es final, estas funciones tienen que crearse de esta manera
	// Si no fuera final, se debería crear una clase que extienda a GraphicsContext y que esten estas funciones ahi, sin la necesidad de createFigure(GraphicsContext gc, Figure figure)
	private void createFigure(GraphicsContext gc, Figure figure){
		if (figure instanceof Circle) {
			createFigure(gc, (Circle) figure);
		}
		else if (figure instanceof Rectangle) {
			createFigure(gc, (Rectangle) figure);
		}
		else if (figure instanceof Ellipse) {
			createFigure(gc, (Ellipse) figure);
		}
		else if (figure instanceof GroupedFigure){
			createFigure(gc, (GroupedFigure) figure);
		}
	}

	private void createFigure(GraphicsContext gc, Circle figureCircle){
		double diameter = figureCircle.getRadius() * 2;
	    gc.fillOval(figureCircle.getCenterPoint().getX() - figureCircle.getRadius(), 
                    figureCircle.getCenterPoint().getY() - figureCircle.getRadius(), 
                    diameter, diameter);
		gc.strokeOval(figureCircle.getCenterPoint().getX() - figureCircle.getRadius(), 
                    figureCircle.getCenterPoint().getY() - figureCircle.getRadius(), diameter, diameter);
	}

	private void createFigure(GraphicsContext gc, Ellipse figureEllipse){
		gc.strokeOval(figureEllipse.getCenterPoint().getX() - (figureEllipse.getsMayorAxis() / 2), 
			figureEllipse.getCenterPoint().getY() - (figureEllipse.getsMinorAxis() / 2), 
			figureEllipse.getsMayorAxis(), figureEllipse.getsMinorAxis());
		gc.fillOval(figureEllipse.getCenterPoint().getX() - (figureEllipse.getsMayorAxis() / 2), 
			figureEllipse.getCenterPoint().getY() - (figureEllipse.getsMinorAxis() / 2), 
			figureEllipse.getsMayorAxis(), figureEllipse.getsMinorAxis());
	}

	private void createFigure(GraphicsContext gc, Rectangle figureRectangle){
		gc.fillRect(figureRectangle.getTopLeft().getX(), figureRectangle.getTopLeft().getY(),
			Math.abs(figureRectangle.getTopLeft().getX() - figureRectangle.getBottomRight().getX()), 
			Math.abs(figureRectangle.getTopLeft().getY() - figureRectangle.getBottomRight().getY()));
		gc.strokeRect(figureRectangle.getTopLeft().getX(), figureRectangle.getTopLeft().getY(),
			Math.abs(figureRectangle.getTopLeft().getX() - figureRectangle.getBottomRight().getX()), 
			Math.abs(figureRectangle.getTopLeft().getY() - figureRectangle.getBottomRight().getY()));
	}

	private void createFigure(GraphicsContext gc, GroupedFigure figureGrouped){
		for (Figure figure : figureGrouped.getFiguresCopy()){
			createFigure(gc, figure);
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