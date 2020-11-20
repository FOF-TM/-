import java.awt.*;
import javax.swing.*;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 绘图类
 */
public class Drawer {

  private static Graphics2D pen = null;
  private static GraphFactory toolFactory = null;
  private static final Deque<Graph> history = new LinkedList<>();
  private static final HashMap<String, GraphFactory> toolMap = new HashMap<>();

  /**
   * 建立字符串与 GraphFactory 的映射
   */
  static {
    toolMap.put("Line", LineFactory.getInstance());
    toolMap.put("Eraser", EraserFactory.getInstance());
  }

  public static void setPen(Graphics2D g) {
    pen = g;
  }

  public static void setPenColor(Color color) {
    pen.setColor(color);
  }

  public static void setTool(String toolName) {
    toolFactory = toolMap.get(toolName);
  }

  public static void draw(int x1, int y1, int x2, int y2) {
    if (toolFactory == null) return;
    Graph tmp = toolFactory.genGraph(x1, y1, x2, y2);
    history.add(tmp);
    tmp.draw(pen);
  }

  public static void repaint() {
    for (Graph graph: history) {
      graph.draw(pen);
    }
  }

}