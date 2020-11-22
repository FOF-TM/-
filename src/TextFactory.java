import java.awt.Color;
import java.awt.Graphics2D;

public class TextFactory implements GraphFactory {

  private Graph blankGraph = new Graph(0, 0, 0, 0, Color.WHITE, 0) {
    @Override
    public void innerDraw(Graphics2D pen) {}
  };

  private TextFactory() {}
  private static TextFactory instance = new TextFactory();

  public static GraphFactory getInstance() {
    return instance;
  }

  @Override
  public Graph genGraph(int x1, int y1, int x2, int y2, Color color, MouseState s) {
    if (s == MouseState.Pressed) {
      return blankGraph;
    }
    Drawer.undo(true);
    return new Text(x1, y1, x2, y2, color);
  }
  
}
