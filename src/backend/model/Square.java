package backend.model;

public class Square extends Rectangle {

    public Square(Point topLeft, double size) {
        super(topLeft, new Point(topLeft.x + size, topLeft.y + size));
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }

    @Override
    public Figure getFigureBasedOnPoints(Point startPoint, Point endPoint){
        double size = Math.abs(endPoint.getX() - startPoint.getX());
		return new Square(startPoint, size);
    }

}
