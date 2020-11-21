import java.awt.Color;
import java.awt.Graphics2D;

public class Oval extends Graph {

  public Oval(int x1, int y1, int x2, int y2, Color c) {
    super(x1, y1, x2, y2, c, StateManager.getPenWidth());
  }

  @Override
  public void innerDraw(Graphics2D pen) {
    normalize();
    pen.drawOval(x1, y1, x2-x1, y2-y1);
  }
  
}
