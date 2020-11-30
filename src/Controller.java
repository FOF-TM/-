import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 * 用于监听鼠标的动作，调用 Drawer 的相关方法完成绘制
 */
public class Controller extends MouseInputAdapter {
  
  // 用于实现单例模式
  private Controller() {};
  private static Controller instance = new Controller();

  // 用于实现单例模式
  public static Controller getInstance() {
    return instance;
  }

  // 用于记录上一次绘制的点，方便拖动绘图的实现
  private int x1, x2, y1, y2;

  public void updateX1Y1withX2Y2() {
    x1 = x2; y1 = y2;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    x1 = e.getX(); y1 = e.getY();
    x2 = e.getX(); y2 = e.getY();
    Drawer.addToUndoPoint(Drawer.draw(x1, y1, x2, y2, MouseState.Pressed));
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    x2 = e.getX(); y2 = e.getY();
    Drawer.draw(x1, y1, x2, y2, MouseState.Dragged);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    Drawer.draw(x1, y1, x2, y2, MouseState.Released);
    updateX1Y1withX2Y2();
  }
}