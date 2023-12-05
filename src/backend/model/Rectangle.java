package backend.model;

public class Rectangle implements Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public Figure getFigureBasedOnPoints(Point startPoint, Point endPoint){
        return new Rectangle(startPoint, endPoint);
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public void changePos(double diffX, double diffY){
        this.getTopLeft().x += diffX;
		this.getBottomRight().x += diffX;
		this.getTopLeft().y += diffY;
		this.getBottomRight().y += diffY;
    }

}
