package frontend;

import backend.model.Circle;
import backend.model.Ellipse;
import backend.model.Point;
import backend.model.Rectangle;
import backend.model.Square;
import frontend.paintFigures.PaintEllipse;
import frontend.paintFigures.PaintFigure;
import frontend.paintFigures.PaintRectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public enum FigureButtonEnum {
    RECTANGLE("Rectangulo"){
        @Override
        public PaintFigure getFigureBasedOnPoints(Point startPoint, Point endPoint,  GraphicsContext gc, Color fillColor, Color borderColor){
            return new PaintRectangle(new Rectangle(getTopLeft(startPoint, endPoint), getBottomRight(startPoint,endPoint)), gc, fillColor, borderColor);
        }
    },
    CIRCLE("Círculo"){
        @Override
        public PaintFigure getFigureBasedOnPoints(Point startPoint, Point endPoint,  GraphicsContext gc, Color fillColor, Color borderColor){
            double circleRadius = Math.sqrt(Math.pow(endPoint.getX()-startPoint.getX(),2)+Math.pow(endPoint.getY()-startPoint.getY(), 2));
			return new PaintEllipse(new Circle(startPoint, circleRadius), gc, fillColor, borderColor);
        }
    },
    ELLIPSE("Elipse"){
        @Override
        public PaintFigure getFigureBasedOnPoints(Point startPoint, Point endPoint, GraphicsContext gc, Color fillColor, Color borderColor){
            Point centerPoint = new Point(Math.abs(endPoint.x + startPoint.x) / 2, (Math.abs((endPoint.y + startPoint.y)) / 2));
            double sMayorAxis = Math.abs(endPoint.x - startPoint.x);
            double sMinorAxis = Math.abs(endPoint.y - startPoint.y);
            return new PaintEllipse(new Ellipse(centerPoint, sMayorAxis, sMinorAxis), gc, fillColor, borderColor);
        }
    },
    SQUARE("Cuadrado"){
        @Override
        public PaintFigure getFigureBasedOnPoints(Point startPoint, Point endPoint, GraphicsContext gc, Color fillColor, Color borderColor){
            double size = Math.abs(endPoint.getX() - startPoint.getX());
			return new PaintRectangle(new Square(getTopLeft(startPoint, endPoint), size), gc, fillColor, borderColor);
        }
    };

    private String name;

    FigureButtonEnum(String name){
        this.name = name;
    }

    public abstract PaintFigure getFigureBasedOnPoints(Point startPoint, Point endPoint, GraphicsContext gc, Color fillColor, Color borderColor);

    private static Point getTopLeft(Point p1, Point p2){
        return new Point(p1.getX()<p2.getX() ? p1.getX():p2.getX(), p1.getY()<p2.getY() ? p1.getY():p2.getY());
    }

    private static Point getBottomRight(Point p1, Point p2){
        return new Point(p1.getX()>p2.getX() ? p1.getX():p2.getX(), p1.getY()>p2.getY() ? p1.getY():p2.getY());
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
