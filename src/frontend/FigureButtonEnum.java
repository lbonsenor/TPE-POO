package frontend;

import backend.model.Point;
import frontend.gcmodel.GCCircle;
import frontend.gcmodel.GCEllipse;
import frontend.gcmodel.GCFigure;
import frontend.gcmodel.GCRectangle;
import frontend.gcmodel.GCSquare;

public enum FigureButtonEnum {
    RECTANGLE("Rectangulo"){
        @Override
        public GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint){
            return new GCRectangle(Point.getTopLeft(startPoint, endPoint), Point.getBottomRight(startPoint,endPoint));
        }
    },
    CIRCLE("Círculo"){
        @Override
        public GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint){
            double circleRadius = Math.sqrt(Math.pow(endPoint.getX()-startPoint.getX(),2)+Math.pow(endPoint.getY()-startPoint.getY(), 2));
			return new GCCircle(startPoint, circleRadius);
        }
    },
    ELLIPSE("Elipse"){
        @Override
        public GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint){
            Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
            double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
            double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
            return new GCEllipse(centerPoint, sMayorAxis, sMinorAxis);
        }
    },
    SQUARE("Cuadrado"){
        @Override
        public GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint){
            double size = Math.abs(endPoint.getX() - startPoint.getX());
			return new GCSquare(Point.getTopLeft(startPoint, endPoint), size);
        }
    };

    private String name;

    FigureButtonEnum(String name){
        this.name = name;
    }

    public abstract GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint);
    
    @Override
    public String toString(){
        return this.name;
    }
}
