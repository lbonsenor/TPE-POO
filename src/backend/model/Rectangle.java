package backend.model;

import javafx.scene.canvas.GraphicsContext;

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

    @Override
    public void redraw(GraphicsContext gc){
        gc.fillRect(this.getTopLeft().getX(), this.getTopLeft().getY(),
					Math.abs(this.getTopLeft().getX() - this.getBottomRight().getX()), 
                    Math.abs(this.getTopLeft().getY() - this.getBottomRight().getY()));
		gc.strokeRect(this.getTopLeft().getX(), this.getTopLeft().getY(),
					  Math.abs(this.getTopLeft().getX() - this.getBottomRight().getX()), 
                      Math.abs(this.getTopLeft().getY() - this.getBottomRight().getY()));
    }

    @Override
    public boolean found(Point eventPoint){
        return eventPoint.getX() > this.topLeft.getX() 
               && eventPoint.getX() < this.bottomRight.getX() 
               && eventPoint.getY() > this.topLeft.getY() 
               && eventPoint.getY() < this.bottomRight.getY();
    }

    @Override
    public void rotate(){
        double centerPoint[] = {(topLeft.getX()+bottomRight.getX())/2, (topLeft.getY()+bottomRight.getY())/2};
        double offset[] = {(bottomRight.getY()-topLeft.getY())/2, (bottomRight.getX()-topLeft.getX())/2};

        topLeft.x = centerPoint[0]-offset[0];
        topLeft.y = centerPoint[1]-offset[1];


        bottomRight.x = centerPoint[0]+offset[0];
        bottomRight.y = centerPoint[1]+offset[1];
    }

}
