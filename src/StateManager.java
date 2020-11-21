import java.awt.*;
import javax.swing.JComponent;

/**
 * 状态管理类
 */
public class StateManager {

  private static Color colorBackup;
  private static JComponent drawingBoard;
  private static float width;
  
  /**
   * 状态管理器启动函数，传入画板实例
   */
  public static void init(JComponent board) {
    drawingBoard = board;
    drawingBoard.addMouseListener(Controller.getInstance());
    drawingBoard.addMouseMotionListener(Controller.getInstance());

    Drawer.setPen((Graphics2D) board.getGraphics());
    setPenColor(Color.BLACK);
    setTool("Pen");
  }

  /**
   * 获取最后一次选择的颜色
   */
  public static Color getColorBackup() {
    return colorBackup;
  }

  /**
   * 获取当前笔刷的宽度
   */
  public static float getPenWidth() {
    return width;
  }

  /**
   * 设置笔刷的宽度
   */
  public static void setPenWidth(float w) {
    width = w;
  }

  /**
   * 设置笔刷的颜色
   */
  public static void setPenColor(Color c) {
    colorBackup = c;
    Drawer.setPenColor(c);
  }

  /**
   * 设置使用的工具，参数可以为：
   *  Pen 铅笔
   *  Eraser 橡皮
   *  Line 直线
   *  Oval 圆
   *  Rectangle 矩形
   */
  public static void setTool(String toolName) {
    Drawer.setTool(toolName);
  }

  /**
   * 强制调用画板的 repaint 方法
   */
  public static void repaintDrawingBoard() {
    drawingBoard.repaint();
  }

  /**
   * 在画板上重新绘制所有的图形，配合画板的 repaint 使用
   */
  public static void repaintHistory(Graphics g) {
    for (Graph graph: Drawer.getHistory()) {
      graph.draw((Graphics2D) g);
    }
  }
}
