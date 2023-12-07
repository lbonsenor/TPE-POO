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

    @Override
    public boolean found(Point eventPoint){
        return eventPoint.getX() > this.topLeft.getX() 
               && eventPoint.getX() < this.bottomRight.getX() 
               && eventPoint.getY() > this.topLeft.getY() 
               && eventPoint.getY() < this.bottomRight.getY();
    }

    @Override
    public boolean found(Point startPoint, Point endPoint){
        return startPoint.getX() < this.topLeft.getX()
               && startPoint.getY() < this.topLeft.getY()
               && endPoint.getX() > this.bottomRight.getX()
               && endPoint.getY() > this.bottomRight.getY();
    }

    @Override
    public void rotate(){
        double centerPoint[] = getCenterPoints();
        double offset[] = {(bottomRight.getY()-topLeft.getY())/2, (bottomRight.getX()-topLeft.getX())/2};

        topLeft.x = centerPoint[0]-offset[0];
        topLeft.y = centerPoint[1]-offset[1];


        bottomRight.x = centerPoint[0]+offset[0];
        bottomRight.y = centerPoint[1]+offset[1];
    }

    @Override
    public void scale(double multiplier){
        double centerPoint[] = getCenterPoints();
        double offset[] = {(bottomRight.getX()-topLeft.getX())*Math.sqrt(multiplier)/2,(bottomRight.getY()-topLeft.getY())*Math.sqrt(multiplier)/2};

        topLeft.x = centerPoint[0]-offset[0];
        topLeft.y = centerPoint[1]-offset[1];

        bottomRight.x = centerPoint[0]+offset[0];
        bottomRight.y = centerPoint[1]+offset[1];
    }

    @Override
    public void flipH(){
        this.changePos(bottomRight.getX()-topLeft.getX(), 0);
    }

    @Override
    public void flipV(){
        this.changePos(0, bottomRight.getY()-topLeft.getY());
    }

    private double[] getCenterPoints(){
        double toReturn[] = {(topLeft.getX()+bottomRight.getX())/2, (topLeft.getY()+bottomRight.getY())/2};
        return toReturn;
    }
}
