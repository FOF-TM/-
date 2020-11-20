import java.awt.Graphics2D;

public class Line extends Graph {

  public Line(int x1, int y1, int x2, int y2) {
    super(x1, y1, x2, y2);
  }

  @Override
  public void draw(Graphics2D pen) {
    pen.drawLine(x1, y1, x2, y2);
  }
  
}
