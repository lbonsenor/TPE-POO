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
        getTopLeft().x += diffX;
		getBottomRight().x += diffX;
		getTopLeft().y += diffY;
		getBottomRight().y += diffY;
    }

    @Override
    public boolean found(Point eventPoint){
        return eventPoint.getX() > topLeft.getX() 
               && eventPoint.getX() < bottomRight.getX() 
               && eventPoint.getY() > topLeft.getY() 
               && eventPoint.getY() < bottomRight.getY();
    }

    @Override
    public boolean found(Point startPoint, Point endPoint){
        return startPoint.getX() < topLeft.getX()
               && startPoint.getY() < topLeft.getY()
               && endPoint.getX() > bottomRight.getX()
               && endPoint.getY() > bottomRight.getY();
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
}
