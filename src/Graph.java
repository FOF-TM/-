import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

/**
 * 绘制的所有图形的顶层抽象类
 */
public abstract class Graph {

  // 当前图形所在的范围
  protected int x1, y1, x2, y2;
  // 当前图形的颜色
  protected Color color;
  // 当前图形的线宽（文字图形实际并没有用到这个字段）
  protected float width;

  /**
   * Constructor，用于初始化内部属性值
   */
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

  /**
   * 通用绘图方法，根据内部的字段属性来画一个图形，并恢复现场
   */
  public void draw(Graphics2D pen) {
    pen.setStroke(new BasicStroke(width));
    pen.setColor(color);
    innerDraw(pen);
    pen.setColor(StateManager.getColorBackup());
    pen.setStroke(StateManager.getStroke());
  }

  /**
   * 具体工具类需要实现的方法
   */
  public abstract void innerDraw(Graphics2D pen);

}