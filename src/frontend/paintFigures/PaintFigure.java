package frontend.paintFigures;

import java.util.Objects;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.GroupFigure;
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
    protected GroupFigure groupFigure;

    public PaintFigure(Figure model, Color fillColor, Color borderColor, boolean shadowSelected, boolean gradSelected, boolean biselSelected) {
        this.model = model;
        setFillColor(fillColor);
        setBorderColor(borderColor);
        setShadow(shadowSelected);
        setGrad(gradSelected);
        setBisel(biselSelected);
    }

    protected Paint gradColor(){
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
            CycleMethod.NO_CYCLE,
            new Stop(0, (Color) fillColor),
            new Stop(1, ((Color) fillColor).invert() ));
        return linearGradient;
    }

    protected abstract void shadowDraw(GraphicsContext gc);
    protected abstract void biselDraw(GraphicsContext gc);

    //metodo experimental
    public void showFiguresInGroup(){
        for (PaintFigure paintFigure : groupFigure) {
            System.out.println(paintFigure);
        }
    }

    public void setGroupFigure(GroupFigure groupFigure){
        this.groupFigure = groupFigure;
    }

    public GroupFigure getGroupFigure(){
        return this.groupFigure;
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

    @Override
    public void rotate() {
        model.rotate();
    }

    @Override
    public void scale(double multiplier) {
        model.scale(multiplier);
    }

    @Override
    public void flipH() {
        model.flipH();
    }

    @Override
    public void flipV() {
        model.flipV();
    }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof PaintFigure figure
                && this.model.equals(figure));
    }

    @Override
    public int hashCode() {
        return Objects.hash(model.hashCode());
    }


}
