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
  private static final Deque<Graph> undoPoints = new LinkedList<>();
  private static final HashMap<String, GraphFactory> toolMap = new HashMap<>();

  /**
   * 建立字符串与 GraphFactory 的映射
   */
  static {
    toolMap.put("Pen", PenFactory.getInstance());
    toolMap.put("Eraser", EraserFactory.getInstance());
    toolMap.put("Rectangle", RectangleFactory.getInstance());
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

  public static void addToUndoPoint(Graph g) {
    undoPoints.add(g);
  }

  public static void addToHistory(Graph g) {
    history.add(g);
  }

  public static Graph draw(int x1, int y1, int x2, int y2, boolean isDragged) {
    Graph tmp = 
      toolFactory.genGraph(x1, y1, x2, y2, pen.getColor(), isDragged);

    history.add(tmp);
    tmp.draw(pen);
    return tmp;
  }

  public static void undo(boolean saveHead) {
    Graph head, tail;
    if ((head = undoPoints.pollLast()) == null) return;

    while ((tail = history.peekLast()) != null) {
      history.pollLast();
      if (head == tail) break;
    }
    StateManager.repaintDrawingBoard();
    if (saveHead) {
      history.add(head); undoPoints.add(head);
    }
  }

  public static Deque<Graph> getHistory() {
    return history;
  }

}