package frontend.gcmodel;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.TriStateBoolean;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;

public abstract class GCFigure implements Figure{

    protected Paint fillColor;
    protected Figure model;
    protected Map<Effects, TriStateBoolean> effectMap = new EnumMap<>(Effects.class);

    public GCFigure(Figure model, Color fillColor, boolean shadow, boolean grad, boolean bevel) {
        this.model = model;
        setFillColor(fillColor);

        effectMap.put(Effects.SHADOW, TriStateBoolean.fromBoolean(shadow));
        effectMap.put(Effects.GRADIENT, TriStateBoolean.fromBoolean(grad));
        effectMap.put(Effects.BEVEL, TriStateBoolean.fromBoolean(bevel));
        
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

    @Override
    public boolean found(Point point){
        return model.found(point);
    }

    // nueva forma de utilizar "found" con respecto al rectangulo invisible
    @Override
    public boolean found(Rectangle rectangle){
        return model.found(rectangle);
    }

    public abstract void draw(GraphicsContext gc);

    public void setEffect(Effects effect, boolean value){
        effectMap.put(effect, TriStateBoolean.fromBoolean(value));
    }

    public TriStateBoolean getEffect(Effects effect){
        return effectMap.get(effect);
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
        return this == o || (o instanceof GCFigure figure
                && model.equals(figure));
    }

    @Override
    public int hashCode() {
        return Objects.hash(model.hashCode());
    }


}
