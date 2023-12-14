package frontend.paintFigures;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;

public abstract class PaintFigure implements Figure{

    protected Paint fillColor, borderColor;
    protected Figure model;
    protected boolean shadow, bisel, grad;

    public PaintFigure(Figure model, Color fillColor, Color borderColor) {
        this.model = model;
        setFillColor(fillColor);
        setBorderColor(borderColor);
    }

    public void gradColor(){
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
            CycleMethod.NO_CYCLE,
            new Stop(0, (Color) fillColor),
            new Stop(1, ((Color) fillColor).invert() ));
        setFillColor(linearGradient);
    }

    public void setBorderColor(Paint borderColor) {
        this.borderColor = borderColor;
    }

    public Paint getBorderColor() {
        return borderColor;
    }

    public void setFillColor(Paint fillColor) {
        this.fillColor = fillColor;
    }

    public Paint getFillColor() {
        return fillColor;
    }

    @Override
    public String toString(){
        return model.toString();
    }

    public boolean contains(Point point){
        return model.contains(point);
    }

    // nueva forma de utilizar "found" con respecto al rectangulo invisible
    public boolean isContainedIn(Rectangle rectangle){
        return model.isContainedIn(rectangle);
    }

    public abstract void draw(GraphicsContext gc);

    public void setShadow(boolean value){
        this.shadow = value;
    }

    public boolean getShadow(){
        return this.shadow;
    }

    public void setBisel(boolean value){
        this.bisel = value;
    }

    public boolean getBisel(){
        return this.bisel;
    }

    public void setGrad(boolean value){
        this.grad = value;
    }

    public boolean getGrad(){
        return this.grad;
    }

    @Override
    public void changePos(double diffX, double diffY) {
        model.changePos(diffX, diffY);
    }

    public abstract void rotate();
    public abstract void scale(double multiplier);
    public abstract void flipH();
    public abstract void flipV();
    
}
