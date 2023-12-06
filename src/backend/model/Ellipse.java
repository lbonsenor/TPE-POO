package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse implements Figure {

    protected final Point centerPoint;
    protected double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        this.centerPoint = centerPoint;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    @Override
    public Figure getFigureBasedOnPoints(Point startPoint, Point endPoint){
        Point centerPoint = new Point(Math.abs(endPoint.x + startPoint.x) / 2, (Math.abs((endPoint.y + startPoint.y)) / 2));
		double sMayorAxis = Math.abs(endPoint.x - startPoint.x);
		double sMinorAxis = Math.abs(endPoint.y - startPoint.y);
		return new Ellipse(centerPoint, sMayorAxis, sMinorAxis);
    }

    @Override
    public void changePos(double diffX, double diffY){
        this.getCenterPoint().x += diffX;
		this.getCenterPoint().y += diffY;
    }

    @Override
    public void redraw(GraphicsContext gc){
        gc.strokeOval(this.getCenterPoint().getX() - (this.getsMayorAxis() / 2), 
                      this.getCenterPoint().getY() - (this.getsMinorAxis() / 2), 
                      this.getsMayorAxis(), this.getsMinorAxis());
		gc.fillOval(this.getCenterPoint().getX() - (this.getsMayorAxis() / 2), 
                    this.getCenterPoint().getY() - (this.getsMinorAxis() / 2), 
                    this.getsMayorAxis(), this.getsMinorAxis());
    }

    @Override
    public boolean found(Point eventPoint){
        return ((Math.pow(eventPoint.getX() - this.centerPoint.getX(), 2) / Math.pow(this.sMayorAxis, 2)) +
				(Math.pow(eventPoint.getY() - this.centerPoint.getY(), 2) / Math.pow(this.sMinorAxis, 2))) <= 0.30;
    }

    @Override
    public void rotate(){
        double temp = this.sMayorAxis;
        this.sMayorAxis = this.sMinorAxis;
        this.sMinorAxis = temp;
    }

    @Override
    public void scale(double multiplier){
        // A = pi*sMayorAxis*sMinorAxis
        // mult*A = pi*(a*sMayorAxis)*(a*sMinorAxis)
        // mult*A = a²*A
        // sqrt(mult) = a
        this.sMayorAxis *= Math.sqrt(multiplier);
        this.sMinorAxis *= Math.sqrt(multiplier);
    }

    @Override
    public void flipH(){
        changePos(sMayorAxis,0);
    }

    @Override
    public void flipV(){
        changePos(0, sMinorAxis);
    }
    
    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, DMayor: %.2f, DMenor: %.2f]", centerPoint, sMayorAxis, sMinorAxis);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getsMayorAxis() {
        return sMayorAxis;
    }

    public double getsMinorAxis() {
        return sMinorAxis;
    }

}
