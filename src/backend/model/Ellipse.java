package backend.model;

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
        this.getCenterPoint().x += diffX;
		this.getCenterPoint().y += diffY;
    }

    @Override
    public boolean found(Point eventPoint){
        return ((Math.pow(eventPoint.getX() - this.centerPoint.getX(), 2) / Math.pow(this.sMayorAxis, 2)) +
				(Math.pow(eventPoint.getY() - this.centerPoint.getY(), 2) / Math.pow(this.sMinorAxis, 2))) <= 0.30;
    }

    @Override
    public boolean found(Point startPoint, Point endPoint){
        return startPoint.getX() < centerPoint.getX()-sMayorAxis/2
               && startPoint.getY() < centerPoint.getY()-sMinorAxis/2
               && endPoint.getX() > centerPoint.getX()+sMayorAxis/2
               && endPoint.getY() > centerPoint.getY()+sMinorAxis/2;
    }

    @Override
    public void rotate(){
        double temp = this.sMayorAxis;
        this.sMayorAxis = this.sMinorAxis;
        this.sMinorAxis = temp;
    }

    @Override
    public void scale(double multiplier){
        // A = pi*(sMayorAxis/2)*(sMinorAxis/2)
        // mult*A = pi*(a(sMayorAxis/2))*(a(sMinorAxis/2))
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

    protected Point getCenterPoint() {
        return centerPoint;
    }

    protected double getsMayorAxis() {
        return sMayorAxis;
    }

    protected double getsMinorAxis() {
        return sMinorAxis;
    }

}
