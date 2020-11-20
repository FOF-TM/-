import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 状态管理类
 */
public class StateManager {

  // 用于记录最后一次选择的颜色
  private static Color colorBackup;
  
  public static void init(Graphics2D pen) {
    Drawer.setPen(pen);
    setPenColor(Color.BLACK);
    setTool("Line");
  }

  public static void setPenColor(Color c) {
    colorBackup = c;
    Drawer.setPenColor(c);
  }

  public static void setTool(String toolName) {
    switch (toolName) {
      case "Line":
        Drawer.setPenColor(colorBackup);
        break;
      case "Eraser":
        Drawer.setPenColor(Color.WHITE);
        break;
    }
    Drawer.setTool(toolName);
  }
}