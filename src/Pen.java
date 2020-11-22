import java.awt.Graphics2D;
import java.awt.Color;

public class Pen extends Graph {

  public Pen(int x1, int y1, int x2, int y2, Color c) {
    super(x1, y1, x2, y2, c, StateManager.getPenWidth());
  }

  @Override
  public void innerDraw(Graphics2D pen) {
    pen.drawLine(x1, y1, x2, y2);
  }
  
}
