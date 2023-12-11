package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends Figure {

    protected final Point centerPoint;
    protected double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        super(new Point[]{centerPoint});
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
    public void draw(GraphicsContext gc){
        gc.strokeOval(this.getCenterPoint().getX() - (this.getsMayorAxis() / 2), 
                      this.getCenterPoint().getY() - (this.getsMinorAxis() / 2), 
                      this.getsMayorAxis(), this.getsMinorAxis());
		gc.fillOval(this.getCenterPoint().getX() - (this.getsMayorAxis() / 2), 
                    this.getCenterPoint().getY() - (this.getsMinorAxis() / 2), 
                    this.getsMayorAxis(), this.getsMinorAxis());
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
    public void shadow(GraphicsContext gc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shadow'");
    }

    @Override
    public void bisel(GraphicsContext gc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bisel'");
    }

    @Override
    public void gradient(GraphicsContext gc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gradient'");
    }

}
