package backend.model;

public interface Figure {
    public abstract void changePos(double diffX, double diffY);
    public abstract Figure getFigureBasedOnPoints(Point startPoint, Point endPoint);
}
