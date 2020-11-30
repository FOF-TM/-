import java.awt.*;
import javax.swing.*;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 绘图类
 */
public class Drawer {

  // 画笔实例
  private static Graphics2D pen = null;
  // 当前的工具工厂
  private static GraphFactory toolFactory = null;
  // 当前所有的绘制历史
  private static final Deque<Graph> history = new LinkedList<>();
  // 当前所有的撤销点记录
  private static final Deque<Graph> undoPoints = new LinkedList<>();
  // 字符串与工具工厂的映射记录，方便工具的切换
  private static final HashMap<String, GraphFactory> toolMap = new HashMap<>();

  /**
   * 建立字符串与 GraphFactory 的映射
   */
  static {
    toolMap.put("Pen", PenFactory.getInstance());
    toolMap.put("Eraser", EraserFactory.getInstance());
    toolMap.put("Rectangle", RectangleFactory.getInstance());
    toolMap.put("Oval", OvalFactory.getInstance());
    toolMap.put("Line", LineFactory.getInstance());
    toolMap.put("Text", TextFactory.getInstance());
  }

  /**
   * 设置画笔实例
   */
  public static void setPen(Graphics2D g) {
    pen = g;
  }

  /**
   * 设置画笔颜色
   */
  public static void setPenColor(Color color) {
    pen.setColor(color);
  }

  /**
   * 设置绘图工具
   */
  public static void setTool(String toolName) {
    toolFactory = toolMap.get(toolName);
  }

  /**
   * 将一个图形加入到撤销点中
   */
  public static void addToUndoPoint(Graph g) {
    undoPoints.add(g);
  }

  /**
   * 将一个图形加入到历史记录中
   */
  public static void addToHistory(Graph g) {
    history.add(g);
  }

  /**
   * 在 (x1, x2) 与 (x2, y2) 形成的范围内根据鼠标状态 s 来调用 GraphFactory 画一个图形
   */
  public static Graph draw(int x1, int y1, int x2, int y2, MouseState s) {
    Graph tmp = 
      toolFactory.genGraph(x1, y1, x2, y2, pen.getColor(), s);

    history.add(tmp);
    tmp.draw(pen);
    return tmp;
  }

  /**
   * 删除最后的撤销点到最后的历史记录间所有的图形并重绘画板
   * 如果 saveHead 为 true 则不删除这个撤销点，否则撤销点也会被删除
   */
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

  /**
   * 获取  history 对象
   */
  public static Deque<Graph> getHistory() {
    return history;
  }

  /**
   * 获取 undoPoints 对象
   */
  public static Deque<Graph> getUndoPoints() {
    return undoPoints;
  }

}