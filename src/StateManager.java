import java.awt.*;
import javax.swing.JComponent;

/**
 * 状态管理类
 */
public class StateManager {

  private static Color colorBackup;
  private static JComponent drawingBoard;

  private static float width;
  private static BasicStroke stroke = new BasicStroke(1);

  private static String fontName;
  private static int fontSize;
  private static Font font = new Font("微软雅黑", Font.PLAIN, 20);

  private static Image background = null;

  private static String text = "Ghy - Lym";

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
   * 获取当前记录的文本框中的内容，方便文字绘制的实现
   */
  public static String getText() {
    return text;
  }

  /**
   * 返回当前 width 对应的 BasicStroke 对象
   */
  public static BasicStroke getStroke() {
    return stroke;
  }

  /**
   * 返回当前的 Font 对象
   */
  public static Font getFont() {
    return font;
  }

  /**
   * 设置字体
   */
  public static void setFontName(String fn) {
    if (! fn.equals(fontName)) {
      fontName = fn;
      font = new Font(fontName, Font.PLAIN, fontSize);
    }
  }

  /**
   * 设置文本大小
   */
  public static void setFontSize(int s) {
    if (s != fontSize) {
      fontSize = s;
      font = new Font(fontName, Font.PLAIN, fontSize);
    }
  }

  /**
   * 用于记录当前文本框中的内容
   */
  public static void setText(String t) {
    text = t;
  }

  /**
   * 设置笔刷的宽度
   */
  public static void setPenWidth(float w) {
    if (width != w) {
      width = w;
      stroke = new BasicStroke(width);
    }
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

  public static void setBackground(Image img) {
    background = img;
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
    g.drawImage(background, 0, 0, drawingBoard.getWidth(), drawingBoard.getHeight(), null);
    for (Graph graph: Drawer.getHistory()) {
      graph.draw((Graphics2D) g);
    }
  }

  /**
   * 清空 Drawer 的 history 和 undoPoints，方便新建画板
   */
  public static void reset() {
    Drawer.getHistory().clear();
    Drawer.getUndoPoints().clear();
  }
}
