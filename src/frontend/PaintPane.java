package frontend;

import backend.CanvasState;
import backend.FigureToggleButton;
import backend.model.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
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
	ComboBox<String> layerCB = new ComboBox<String>(FXCollections.observableArrayList(layers)); //Test
	

	// Dibujar una figura
	Point startPoint;

	// Seleccionar una figura
	//Figure selectedFigure;
	ArrayList<Figure> selectedFigures = new ArrayList<>();

	// StatusBar
	StatusPane statusPane;

	// Colores de relleno de cada figura
	Map<Figure, Color> figureColorMap = new HashMap<>();

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton,groupButton, ungroupButton, rotateButton, flipHButton, flipVButton, scalePButton, scaleMButton, deleteButton};
		FigureToggleButton[] figuresArr = {rectangleButton, circleButton, squareButton, ellipseButton};
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.getChildren().add(fillColorPicker);

		buttonsBox.getChildren().addAll(layerLabel);
		buttonsBox.getChildren().addAll(layerCB);

		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);

		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null || (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY())) {
				return ;
			}
			
			Figure newFigure = null;
			for(FigureToggleButton button : figuresArr){
				if (button.isSelected()) {
					newFigure = button.getFigureBasedOnPoints(startPoint, endPoint);
					figureColorMap.put(newFigure, fillColorPicker.getValue());
					canvasState.addFigure(newFigure);
					startPoint = null;
					redrawCanvas();
					return;
				}
			}

			//Un rectangulo imaginario.
			if (selectionButton.isSelected()){
				Rectangle ImaginaryRec = new Rectangle(startPoint,endPoint);
				for (Figure figure : canvasState.figures()){
					//Qvq cada punto "clave" de figure, figureBelongs ImaginaryRec
					//Por ahora, lo hago de la manera bruta

					//Probablemente no sea de esta manera la comparacion,
					//por ahora lo dejo asi de prueba. Tengo q ver si es necesario comparar area qcy.

					if (figure instanceof Ellipse ellipse){
					if(figureBelongs(ImaginaryRec,ellipse.getCenterPoint())){
						selectedFigures.add(figure);
						}
					}
					else if (figure instanceof Rectangle rectangle){
						if (figureBelongs(ImaginaryRec,rectangle.getBottomRight())
						&& figureBelongs(ImaginaryRec,rectangle.getTopLeft())){
							selectedFigures.add(figure);
						}
						}



				}

			}
			
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(Figure figure : canvasState.figures()) {
				if(figureBelongs(figure, eventPoint)) {
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
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Figure figure : canvasState.figures()) {
					if(figureBelongs(figure, eventPoint)) {
						found = true;
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
					canvasState.deleteFigure(figure);
				//	selectedFigures.remove(figure); //creo q no se podia mientras iteraba.
				}
			//	selectedFigure = null;
				redrawCanvas();
			}
		});

		rotateButton.setOnAction(event -> {
			if (selectedFigure != null) {
				selectedFigure.rotate();
				redrawCanvas();
			}
		});

		scalePButton.setOnAction(event->{
			if (selectedFigure != null) {
				selectedFigure.scale(1.25);
				redrawCanvas();
			}
		});

		scaleMButton.setOnAction(event->{
			if (selectedFigure != null) {
				selectedFigure.scale(0.75);
				redrawCanvas();
			}
		});

		flipHButton.setOnAction(event ->{
			if (selectedFigure != null) {
				selectedFigure.flipH();
				redrawCanvas();
			}
		});

		flipVButton.setOnAction(event ->{
			if (selectedFigure != null) {
				selectedFigure.flipV();
				redrawCanvas();
			}
		});

		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : canvasState.figures()) {
				if (selectedFigures != null && selectedFigures.contains(figure)) {
					gc.setStroke(Color.RED);
				} else {
					gc.setStroke(lineColor);
				}
				gc.setFill(figureColorMap.get(figure));
				figure.redraw(gc);
		}
		}

	boolean figureBelongs(Figure figure, Point eventPoint) {
		return figure.found(eventPoint);
	}

}