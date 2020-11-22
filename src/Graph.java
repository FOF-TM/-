import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

/**
 * 绘制的所有图形的顶层抽象类
 */
public abstract class Graph {

  protected int x1, y1, x2, y2;
  protected Color color;
  protected float width;

  public Graph(int x1, int y1, int x2, int y2, Color c, float width) {
    color = c;
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.width = width;
  }

  /**
   * 用于保证 y2>y1, x2>x1，从而方便绘图
   */
  public void normalize() {
    int tmp = 0;
    if (x2 < x1) {
      tmp = x2; x2 = x1; x1 = tmp;
    }
    if (y2 < y1) {
      tmp = y2; y2 = y1; y1 = tmp;
    }
  }

  public void draw(Graphics2D pen) {
    pen.setStroke(new BasicStroke(width));
    pen.setColor(color);
    innerDraw(pen);
    pen.setColor(StateManager.getColorBackup());
    pen.setStroke(StateManager.getStroke());
  }

  public abstract void innerDraw(Graphics2D pen);

}