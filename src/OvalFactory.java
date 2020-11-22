import java.awt.Color;

public class OvalFactory implements GraphFactory {

  private OvalFactory() {}
  private static OvalFactory instance = new OvalFactory();

  public static GraphFactory getInstance() {
    return instance;
  }

  @Override
  public Graph genGraph(int x1, int y1, int x2, int y2, Color color, MouseState s) {
    if (s == MouseState.Dragged) Drawer.undo(true);
    return new Oval(x1, y1, x2, y2, color);
  }

}
