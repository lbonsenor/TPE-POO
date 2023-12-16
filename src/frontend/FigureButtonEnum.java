package frontend;

import backend.model.Circle;
import backend.model.Ellipse;
import backend.model.Point;
import backend.model.Rectangle;
import backend.model.Square;
import frontend.gcmodel.GCCircle;
import frontend.gcmodel.GCEllipse;
import frontend.gcmodel.GCFigure;
import frontend.gcmodel.GCRectangle;
import frontend.gcmodel.GCSquare;
import javafx.scene.paint.Color;

public enum FigureButtonEnum {
    RECTANGLE("Rectangulo"){
        @Override
        public GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint, Color fillColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected){
            return new GCRectangle(new Rectangle(Point.getTopLeft(startPoint, endPoint), Point.getBottomRight(startPoint,endPoint)), fillColor, shadowSelected, gradSelected, biselSelected);
        }
    },
    CIRCLE("Círculo"){
        @Override
        public GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint, Color fillColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected){
            double circleRadius = Math.sqrt(Math.pow(endPoint.getX()-startPoint.getX(),2)+Math.pow(endPoint.getY()-startPoint.getY(), 2));
			return new GCCircle(new Circle(startPoint, circleRadius), fillColor, shadowSelected, gradSelected, biselSelected);
        }
    },
    ELLIPSE("Elipse"){
        @Override
        public GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint, Color fillColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected){
            Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
            double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
            double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
            return new GCEllipse(new Ellipse(centerPoint, sMayorAxis, sMinorAxis), fillColor, shadowSelected, gradSelected, biselSelected);
        }
    },
    SQUARE("Cuadrado"){
        @Override
        public GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint, Color fillColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected){
            double size = Math.abs(endPoint.getX() - startPoint.getX());
			return new GCSquare(new Square(Point.getTopLeft(startPoint, endPoint), size), fillColor, shadowSelected, gradSelected, biselSelected);
        }
    };

    private String name;

    FigureButtonEnum(String name){
        this.name = name;
    }

    public abstract GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint, Color fillColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected);
    
    @Override
    public String toString(){
        return this.name;
    }
}
