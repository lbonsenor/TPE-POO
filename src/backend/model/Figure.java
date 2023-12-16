package backend.model;

public interface Figure {
    public abstract void changePos(double diffX, double diffY);

    public abstract boolean found(Point eventPoint);
    public abstract boolean found(Rectangle rectangle);

    public abstract void rotate();
    public abstract void scale(double multiplier);
    public abstract void flipH();
    public abstract void flipV();
}
