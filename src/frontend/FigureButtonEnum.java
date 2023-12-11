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
            return new GCRectangle(getTopLeft(startPoint, endPoint), getBottomRight(startPoint,endPoint));
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
            Point centerPoint = new Point(Math.abs(endPoint.x + startPoint.x) / 2, (Math.abs((endPoint.y + startPoint.y)) / 2));
            double sMayorAxis = Math.abs(endPoint.x - startPoint.x);
            double sMinorAxis = Math.abs(endPoint.y - startPoint.y);
            return new GCEllipse(centerPoint, sMayorAxis, sMinorAxis);
        }
    },
    SQUARE("Cuadrado"){
        @Override
        public GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint){
            double size = Math.abs(endPoint.getX() - startPoint.getX());
			return new GCSquare(getTopLeft(startPoint, endPoint), size);
        }
    };

    private String name;

    FigureButtonEnum(String name){
        this.name = name;
    }

    public abstract GCFigure getFigureBasedOnPoints(Point startPoint, Point endPoint);

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
