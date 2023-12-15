package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.paintFigures.PaintFigure;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
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
import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState<PaintFigure> canvasState;

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
	Button groupButton = new Button("Agrupar");
	Button ungroupButton = new Button("Desagrupar");
	Button rotateButton = new Button("Girar D");
	Button flipHButton = new Button("Voltear H");
	Button flipVButton = new Button("Voltear V");
	Button scalePButton = new Button("Escalar +");
	Button scaleMButton = new Button("Escalar -");
	Button deleteButton = new Button("Borrar");

	// Selector de color de relleno
	ColorPicker fillColorPicker = new ColorPicker(DEFAULT_FIGURE_FILL_COLOR);

	// Boton de Capa Izquierdo
	Label layerLabel = new Label("Capa");
	String layers[] = {"Layer 1", "Layer 2", "Layer 3"};
	ComboBox<String> layerCB = new ComboBox<String>(FXCollections.observableArrayList(layers)); //Test
	
	// Tags
	//Label tagLabel = new Label("Etiquetas");
	//TextArea tagArea = new TextArea();
	//Button tagButton = new Button("Guardar");

	// Dibujar una figura
	Point startPoint;

	boolean isMovingFigures;

	// Seleccionar una figura
	Set<PaintFigure> selectedFigures = new HashSet<>();

	// StatusBar
	StatusPane statusPane;

	// Barra de selector de efectos
	EffectsPane effectsPane;

	public PaintPane(CanvasState<PaintFigure> canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		this.effectsPane = new EffectsPane();
		ToggleButton[] figuresButtons = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton,};
		ButtonBase[] functionalitiesButtons = { groupButton, ungroupButton, rotateButton, flipHButton, flipVButton, scalePButton, scaleMButton, deleteButton};
		ToggleGroup tools = new ToggleGroup();
		this.setTop(effectsPane);
		
		fillColorPicker.valueProperty().addListener(this::onFillColorChanged);
		for (ToggleButton tool : figuresButtons) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		for (ButtonBase tool : functionalitiesButtons) {
			tool.setMinWidth(90);
			tool.setCursor(Cursor.HAND);
		}
		
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(figuresButtons);
		buttonsBox.getChildren().addAll(functionalitiesButtons);
		buttonsBox.getChildren().add(fillColorPicker);

		buttonsBox.getChildren().add(layerLabel);
		buttonsBox.getChildren().add(layerCB);

		//buttonsBox.getChildren().add(tagLabel);
		//buttonsBox.getChildren().add(tagArea);
		//buttonsBox.getChildren().add(tagButton);
		//tagArea.setPrefHeight(10);

		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);

		tools.selectedToggleProperty().addListener(this::onSelectedButtonChanged);

		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
			isMovingFigures = false;
		});

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
				String status = "";
				if (startPoint.distanceSquaredTo(endPoint) == 0) {
					PaintFigure figure = canvasState.getFigureAt(endPoint);
					if (figure != null) {
						selectedFigures.add(figure);
						status = String.format("Se seleccionó %s", figure);
					}
				}else {
					Rectangle container = Rectangle.from(startPoint, endPoint);
					canvasState.getFiguresOnRectangle(container, selectedFigures);
					if (selectedFigures.size() == 1){
						status = String.format("Se seleccionó: %s", selectedFigures.iterator().next());
					} else{
						status = String.format("Se seleccionaron %d figuras", selectedFigures.size());
					}
				}
				if (selectedFigures.isEmpty()) {
					status = "No se seleccionó ninguna figura";
				}
				statusPane.updateStatus(status);
				onSelectionChanged();
			}
			// si el boton de Selection NO esta activo -> dibujo figura
			else{
				PaintFigure figure = ((FigureToggleButton) selectedButton).getFigureBasedOnPoints(startPoint, endPoint, fillColor, borderColor);
				canvasState.addFigure(figure);
				redrawCanvas();
			}
			
		});

		canvas.setOnMouseMoved(event -> {
			// si no estoy seleccionando figuras
			// --> muestro el cursor sobre un punto en el plano o sobre una figura
			if (selectedFigures.isEmpty()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				PaintFigure selectedFigure = canvasState.getFigureAt(eventPoint);
				statusPane.updateStatus(selectedFigure == null ? eventPoint.toString() : selectedFigure.toString());
			}
		});

		canvas.setOnMouseDragged(event -> {
			if (selectedFigures!=null && !selectedFigures.isEmpty()) {
				double diffX = event.getX() - startPoint.getX();
				double diffY = event.getY() - startPoint.getY();
				for (PaintFigure figure : selectedFigures)
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
				for (PaintFigure figure : selectedFigures){
					figure.rotate();
				}
				redrawCanvas();
			}
		});

		scalePButton.setOnAction(event->{
			if (selectedFigures != null) {
				for (PaintFigure figure : selectedFigures){
					figure.scale(1.25);
				}
				redrawCanvas();
			}
		});

		scaleMButton.setOnAction(event->{
			if (selectedFigures != null) {
				for (PaintFigure figure : selectedFigures){
					figure.scale(0.75);
				}
				redrawCanvas();
			}
		});

		flipHButton.setOnAction(event ->{
			if (selectedFigures != null) {
				for (PaintFigure figure : selectedFigures){
					figure.flipH();
				}
				redrawCanvas();
			}
		});

		flipVButton.setOnAction(event ->{
			if (selectedFigures != null) {
				for (PaintFigure figure : selectedFigures){
					figure.flipV();
				}
				redrawCanvas();
			}
		});

		// groupButton.setOnAction(event ->{
		// 	if (selectedFigures != null) {
		// 		figures.groupFigures(selectedFigures);
		// 		redrawCanvas();
		// 	}
		// });

		// ungroupButton.setOnAction(event ->{
		// 	if (selectedFigures != null) {
		// 		figures.ungroupFigures(selectedFigures);
		// 		redrawCanvas();
		// 	}
		// });

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
			Iterator<PaintFigure> iter = selectedFigures.iterator();
			PaintFigure f = iter.next();
			Color fc = (Color) f.getFillColor();
			// Me fijo si las figuras seleccionadas comparten color para mostrar en la paleta
			while (fc != null && iter.hasNext()) {
				f = iter.next();
				if (fc != null && !fc.equals(f.getFillColor())) fc = null;				
			}
			fillColorPicker.setValue(fc);			
		}
		redrawCanvas();
	}

	private void onFillColorChanged(ObservableValue<? extends Color> observableValue, Color color, Color newValue) {
		if (newValue == null)
			return;

		if (selectedFigures.isEmpty()) {
			fillColor = newValue;
		} else {
			for (PaintFigure figure : selectedFigures)
				figure.setFillColor(newValue);
			redrawCanvas();
		}
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(PaintFigure figure : canvasState.figures()) {
			gc.setStroke(selectedFigures.contains(figure) ? SELECTED_FIGURE_BORDER_COLOR : figure.getBorderColor());
			gc.setFill(figure.getFillColor());
			figure.draw(gc);
		}
	}

}