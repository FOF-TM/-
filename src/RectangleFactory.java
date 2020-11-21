import java.awt.Color;

public class RectangleFactory implements GraphFactory {
  private RectangleFactory() {}
  
  private static GraphFactory instance = new RectangleFactory();

  public static GraphFactory getInstance() {
    return instance;
  }

  @Override
  public Graph genGraph(int x1, int y1, int x2, int y2, Color color, MouseState s) {
    if (s == MouseState.Dragged) Drawer.undo(true);
    return new Rectangle(x1, y1, x2, y2, color);
  }
  
}
