package backend.model;

import java.util.Objects;

import frontend.gcmodel.GCFigure;

public class Ellipse implements Figure {

    protected final Point centerPoint;
    protected double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {        
        this.centerPoint = centerPoint;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    @Override
    public void changePos(double diffX, double diffY){
        centerPoint.changePos(diffX, diffY);
    }
   
    @Override
    public boolean contains(Point point) {
        return ((Math.pow(point.getX() - this.centerPoint.getX(), 2) / Math.pow(this.sMayorAxis, 2)) +
				(Math.pow(point.getY() - this.centerPoint.getY(), 2) / Math.pow(this.sMinorAxis, 2))) <= 0.30;
    }

    @Override
    public boolean isContainedIn(Rectangle rectangle) {
        Point tl = rectangle.getTopLeft();
        Point br = rectangle.getBottomRight();
        return tl.getX() < centerPoint.getX()-sMayorAxis/2
               && tl.getY() < centerPoint.getY()-sMinorAxis/2
               && br.getX() > centerPoint.getX()+sMayorAxis/2
               && br.getY() > centerPoint.getY()+sMinorAxis/2;
    }

    @Override
    public void rotate(){
        double temp = this.sMayorAxis;
        this.sMayorAxis = this.sMinorAxis;
        this.sMinorAxis = temp;
    }

    @Override
    public void scale(double multiplier){
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

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof Ellipse ellipse
                && this.getCenterPoint().equals(ellipse.getCenterPoint())
                && this.getsMayorAxis() == ellipse.getsMayorAxis()
                && this.getsMinorAxis() == ellipse.getsMinorAxis());
    }

    @Override
    public int hashCode() {
        return Objects.hash( getCenterPoint(), getsMayorAxis(), getsMinorAxis() );
    }

}