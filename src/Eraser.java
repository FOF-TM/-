import java.awt.Graphics2D;
import java.awt.Color;

public class Eraser extends Graph {

  public Eraser(int x1, int y1, int x2, int y2, Color c) {
    super(x1, y1, x2, y2, Color.WHITE);
  }

  @Override
  public void innerDraw(Graphics2D pen) {
    pen.fillOval(x1-20, y1-20, 40, 40);
  }
  
}