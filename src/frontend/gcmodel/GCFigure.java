package frontend.gcmodel;
import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;

public interface GCFigure extends Figure{
    public abstract void createFigure(GraphicsContext gc);
    
}
