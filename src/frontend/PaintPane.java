package frontend;

import backend.CanvasState;
import backend.model.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();

	// Constantes de dibujo
	private static final Color SELECTED_FIGURE_BORDER_COLOR = Color.RED;
	private static final Color DEFAULT_FIGURE_FILL_COLOR = Color.YELLOW;
	private static final Color DEFAULT_FIGURE_BORDER_COLOR = Color.BLACK;

	// Propiedades de figuras seleccionadas (inicializads para figuras nuevas)
	private Color borderColor = DEFAULT_FIGURE_BORDER_COLOR;
	private Color fillColor = DEFAULT_FIGURE_FILL_COLOR;

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
	ComboBox<String> layerCB = new ComboBox<String>(FXCollections.observableArrayList(layers)); //Test
	
	// Tags
	Label tagLabel = new Label("Etiquetas");
	TextArea tagArea = new TextArea();
	Button tagButton = new Button("Guardar");

	// Dibujar una figura
	Point startPoint;

	boolean isMovingFigures;

	// Seleccionar una figura
	Set<Figure> selectedFigures = new HashSet<>();

	// StatusBar
	StatusPane statusPane;

	// Barra de selector de efectos
	EffectsPane effectsPane;

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.effectsPane = new EffectsPane();
		this.setTop(effectsPane);
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton,groupButton, ungroupButton, rotateButton, flipHButton, flipVButton, scalePButton, scaleMButton, deleteButton};
		//FigureToggleButton[] figuresArr = {rectangleButton, circleButton, squareButton, ellipseButton};
		ToggleGroup tools = new ToggleGroup();
		tools.selectedToggleProperty().addListener(this::onSelectedButtonChanged);
		fillColorPicker.valueProperty().addListener(this::onFillColorChanged);
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.getChildren().add(fillColorPicker);

		buttonsBox.getChildren().add(layerLabel);
		buttonsBox.getChildren().add(layerCB);

		buttonsBox.getChildren().add(tagLabel);
		buttonsBox.getChildren().add(tagArea);
		buttonsBox.getChildren().add(tagButton);
		tagArea.setPrefHeight(10);

		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);

		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
			isMovingFigures = false;
		});

		// canvas.setOnMouseReleased(event -> {
		// 	Point endPoint = new Point(event.getX(), event.getY());
		// 	if(startPoint == null) {
		// 		return ;
		// 	}
		// 	Figure newFigure = null;
		// 	for(FigureToggleButton button : figuresArr){
		// 		if (button.isSelected()) {
		// 			newFigure = button.getFigureBasedOnPoints(startPoint, endPoint);
		// 			canvasState.addFigure(newFigure);
		// 			startPoint = null;
		// 			redrawCanvas();
		// 			return;
		// 		}
		// 	}
		// 	//Un rectangulo imaginario.
		// 	if (selectionButton.isSelected()){
		// 		selectedFigures = new HashSet<>();
		// 		for (Figure figure : canvasState.figures()){
		// 			if (figure.found(startPoint, endPoint)) {
		// 				selectedFigures.add(figure);
		// 			}
		// 		}
		// 	}
		// });

		canvas.setOnMouseReleased(event -> {

			Toggle selectedButton = tools.getSelectedToggle();

			boolean wasMovingFigures = isMovingFigures;
			isMovingFigures = false;

			if (selectedButton == null || wasMovingFigures){
				return;
			}

			Point endPoint = new Point(event.getX(), event.getY());

			// si el boton de Selection esta activo busco figuras
			// CHEQUEAR CON EQUIPO: que pasa con las figuras agrupadas
			if (selectedButton == selectionButton) {
				selectedFigures.clear();
				if (startPoint.distanceSquaredTo(endPoint) > 1) {
					Rectangle container = Rectangle.from(startPoint, endPoint);
					canvasState.getFiguresOnRectangle(container, selectedFigures);
					String status;
					if (selectedFigures.isEmpty()){
						status = "No se encontraron figuras en el area";
					}
					else if (selectedFigures.size() == 1){
						status = String.format("Se seleccionó: %s", selectedFigures.iterator().next());
					}
					else{
						status = String.format("Se seleccionaron %d figuras", selectedFigures.size());
					}
					statusPane.updateStatus(status);
				} 
				else {
					Figure selectedFigure = canvasState.getFigureAt(endPoint);
					if (selectedFigure != null) {
						selectedFigures.add(selectedFigure);
						statusPane.updateStatus(String.format("Se seleccionó: %s", selectedFigure));
					}
				}
				onSelectionChanged();
			}
			// si el boton de Selection NO esta activo -> dibujo figura
			else{
				((FigureToggleButton) selectedButton).getFigureBasedOnPoints(startPoint, endPoint);
			}
			
		});

		canvas.setOnMouseMoved(event -> {
			// si no estoy seleccionando figuras
			// --> muestro el cursor sobre un punto en el plano o sobre una figura
			if (selectedFigures.isEmpty()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				Figure selectedFigure = canvasState.getFigureAt(eventPoint);
				statusPane.updateStatus(selectedFigure == null ? eventPoint.toString() : selectedFigure.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Figure figure : canvasState.figures()) {
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
				//	selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});

		canvas.setOnMouseDragged(event -> {
			if (selectedFigures!=null && !selectedFigures.isEmpty()) {
				double diffX = event.getX() - startPoint.getX();
				double diffY = event.getY() - startPoint.getY();
				for (Figure figure : selectedFigures)
					figure.changePos(diffX, diffY);
				redrawCanvas();
				startPoint.changePos(diffX, diffY);
				isMovingFigures = true;
			}
		});

		deleteButton.setOnAction(event -> {
			// si hay figuras seleccionadas se van a borrar sino no habra cambios
			canvasState.deleteFigures(selectedFigures);
			selectedFigures.clear();
			onSelectionChanged();
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
				canvasState.groupFigures(selectedFigures);
				redrawCanvas();
			}
		});

		ungroupButton.setOnAction(event ->{
			if (selectedFigures != null) {
				canvasState.ungroupFigures(selectedFigures);
				redrawCanvas();
			}
		});

		setLeft(buttonsBox);
		setRight(canvas);
	}

	// cuando se activa/desactiva el boton de Seleccionar
	private void onSelectedButtonChanged(ObservableValue<? extends Toggle> observableValue, Toggle value, Toggle newValue) {
		if (value == selectionButton && newValue != selectionButton && !selectedFigures.isEmpty()) {
			selectedFigures.clear();
			onSelectionChanged();
		}
	}

	// cuando las figuras selecciondas sufren cambios
	private void onSelectionChanged() {
		// si no hay figuras seleccionadas
		// -> tomo el ultimo color en la paleta
		if (selectedFigures.isEmpty()) {
			fillColorPicker.setValue(fillColor);
		}
		else {
			Iterator<Figure> iter = selectedFigures.iterator();
			Figure f = iter.next();
			Color fc = f.getFillColor();
			// Me fijo si las figuras seleccionadas comparten color para mostrar en la paleta
			while (fc != null && iter.hasNext()) {
				f = iter.next();
				if (fc != null && !fc.equals(f.getFillColor())) fc = null;				
			}
			fillColorPicker.setValue(fc);			
		}
		redrawCanvas();
	}

	private void onBorderColorChanged(ObservableValue<? extends Color> observableValue, Color color, Color newValue) {
		if (newValue == null)
			return;

		if (selectedFigures.isEmpty()) {
			borderColor = newValue;
		} else {
			for (Figure figure : selectedFigures)
				figure.setBorderColor(newValue);
			redrawCanvas();
		}
	}

	private void onFillColorChanged(ObservableValue<? extends Color> observableValue, Color color, Color newValue) {
		if (newValue == null)
			return;

		if (selectedFigures.isEmpty()) {
			fillColor = newValue;
		} else {
			for (Figure figure : selectedFigures)
				figure.setFillColor(newValue);
			redrawCanvas();
		}
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : canvasState.figures()) {
			gc.setStroke(selectedFigures.contains(figure) ? SELECTED_FIGURE_BORDER_COLOR : figure.getBorderColor());
			gc.setFill(figure.getFillColor());
			figure.draw(gc);
		}
	}

}