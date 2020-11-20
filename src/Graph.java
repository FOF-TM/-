import java.awt.Graphics2D;

/**
 * 绘制的所有图形的顶层抽象类
 */
public abstract class Graph {

  protected int x1, y1, x2, y2;

  public Graph(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
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

  public abstract void draw(Graphics2D pen);
}