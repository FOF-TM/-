import java.awt.*;
import javax.swing.JComponent;

/**
 * 状态管理类
 */
public class StateManager {

  // 用于记录最后一次选择的颜色
  private static Color colorBackup;
  private static JComponent drawingBoard;
  
  public static void init(JComponent board) {
    drawingBoard = board;
    drawingBoard.addMouseListener(Controller.getInstance());
    drawingBoard.addMouseMotionListener(Controller.getInstance());

    Drawer.setPen((Graphics2D) board.getGraphics());
    setPenColor(Color.BLACK);
    setTool("Line");
  }

  public static Color getColorBackup() {
    return colorBackup;
  }

  public static void setPenColor(Color c) {
    colorBackup = c;
    Drawer.setPenColor(c);
  }

  public static void setTool(String toolName) {
    Drawer.setTool(toolName);
  }

  public static void repaintDrawingBoard() {
    drawingBoard.repaint();
  }

  public static void repaintHistory(Graphics g) {
    for (Graph graph: Drawer.getHistory()) {
      graph.draw((Graphics2D) g);
    }
  }
}
