import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends Graph {

  public Rectangle(int x1, int y1, int x2, int y2, Color c) {
    super(x1, y1, x2, y2, c);
  }

  @Override
  public void innerDraw(Graphics2D pen) {
    normalize();
    pen.drawRect(x1, y1, x2-x1, y2-y1);
  }
  
}
