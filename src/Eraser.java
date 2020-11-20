import java.awt.Graphics2D;

public class Eraser extends Graph {

  public Eraser(int x1, int y1, int x2, int y2) {
    super(x1, y1, x2, y2);
  }

  @Override
  public void draw(Graphics2D pen) {;
    pen.fillOval(x1-20, y1-20, 40, 40);
  }
  
}