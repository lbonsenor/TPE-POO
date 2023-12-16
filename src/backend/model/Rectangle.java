package backend.model;

import java.util.Objects;
public class Rectangle implements Figure {

    protected final Point topLeft, bottomRight;

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
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public void changePos(double diffX, double diffY){
        getTopLeft().changePos(diffX, diffY);
        getBottomRight().changePos(diffX, diffY);
    }

    // unicamente genera rectangulo invisible
    // -> generado, "no dibujado"
    public static Rectangle from(Point pointA, Point pointB) {
        return new Rectangle(
            new Point(pointA.getX()<pointB.getX() ? pointA.getX():pointB.getX(), pointA.getY()<pointB.getY() ? pointA.getY():pointB.getY()),
            new Point(pointA.getX()>pointB.getX() ? pointA.getX():pointB.getX(), pointA.getY()>pointB.getY() ? pointA.getY():pointB.getY())
        );
    }

    @Override
    public boolean contains(Point point) {
        return topLeft.getX() <= point.getX() && point.getX() <= bottomRight.getX() &&
                topLeft.getY() <= point.getY() && point.getY() <= bottomRight.getY();
    }

    public boolean contains(Rectangle other) {
        return topLeft.getX() <= other.topLeft.getX() && other.bottomRight.getX() <= bottomRight.getX()
                && topLeft.getY() <= other.topLeft.getY() && other.bottomRight.getY() <= bottomRight.getY();
    }

    @Override
    public boolean isContainedIn(Rectangle rectangle) {
        return rectangle.contains(this);
    }

    @Override
    public void rotate(){
        double centerPoint[] = getCenterPoints();
        double offset[] = {(bottomRight.getY()-topLeft.getY())/2, (bottomRight.getX()-topLeft.getX())/2};

        topLeft.setNewValues(centerPoint[0]-offset[0], centerPoint[1]-offset[1]);
        bottomRight.setNewValues(centerPoint[0]+offset[0], centerPoint[1]+offset[1]);
    }

    @Override
    public void scale(double multiplier){
        double centerPoint[] = getCenterPoints();
        double offset[] = {(bottomRight.getX()-topLeft.getX())*Math.sqrt(multiplier)/2,(bottomRight.getY()-topLeft.getY())*Math.sqrt(multiplier)/2};

        topLeft.setNewValues(centerPoint[0]-offset[0], centerPoint[1]-offset[1]);
        bottomRight.setNewValues(centerPoint[0]+offset[0], centerPoint[1]+offset[1]);
    }

    @Override
    public void flipH(){
        changePos(bottomRight.getX()-topLeft.getX(), 0);
    }

    @Override
    public void flipV(){
        changePos(0, bottomRight.getY()-topLeft.getY());
    }

    private double[] getCenterPoints(){
        double toReturn[] = {(topLeft.getX()+bottomRight.getX())/2, (topLeft.getY()+bottomRight.getY())/2};
        return toReturn;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof Rectangle rectangle
                && getBottomRight().equals(rectangle.getBottomRight())
                && getTopLeft().equals(rectangle.getTopLeft()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBottomRight(), getTopLeft());
    }
   
}