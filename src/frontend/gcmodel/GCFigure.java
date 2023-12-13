package frontend.gcmodel;
import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface GCFigure extends Figure{
    public abstract void createFigure(GraphicsContext gc);
    public abstract void setFillColor(Color color);
    public abstract Color getFillColor();
    public abstract void setShadow(boolean value);
    public abstract boolean getShadow();
    public abstract void setBisel(boolean value);
    public abstract boolean getBisel();
    public abstract void setGrad(boolean value);
    public abstract boolean getGrad();
}