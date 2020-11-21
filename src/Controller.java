import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 * 用于监听鼠标的动作，调用 Drawer 的相关方法完成绘制
 */
public class Controller extends MouseInputAdapter {
  
  private Controller() {};
  private static Controller instance = new Controller();

  private int x1, x2, y1, y2;

  public static Controller getInstance() {
    return instance;
  }

  public void updateX1Y1withX2Y2() {
    x1 = x2; y1 = y2;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    x1 = e.getX(); y1 = e.getY();
    x2 = e.getX(); y2 = e.getY();
    Drawer.addToUndoPoint(Drawer.draw(x1, y1, x2, y2, false));
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    x2 = e.getX(); y2 = e.getY();
    Drawer.draw(x1, y1, x2, y2, true);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    Drawer.draw(x1, y1, x2, y2, false);
  }
}