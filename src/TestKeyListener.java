import java.awt.Color;
import java.awt.event.*;

/**
 * 用于测试后端的键盘事件监听器
 */
public class TestKeyListener implements KeyListener {

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("按键（"+e.getKeyChar()+"） 被按下");
    switch (e.getKeyChar()) {
      case '`': Drawer.undo(false); break;
      case 'q': StateManager.setPenColor(Color.BLACK); break;
      case 'w': StateManager.setPenColor(Color.BLUE); break;
      case 'e': StateManager.setPenColor(Color.RED); break;
      case 'r': StateManager.setPenColor(Color.GREEN); break;
      case '1': StateManager.setTool("Pen"); break;
      case '2': StateManager.setTool("Eraser"); break;
      case '3': StateManager.setTool("Rectangle"); break;
      case '4': StateManager.setTool("Oval"); break;
      case '5': StateManager.setTool("Line"); break;
      case '6': StateManager.setTool("Text"); break;
      case '[': StateManager.setPenWidth(Math.max(StateManager.getPenWidth() - 1, 0)); break;
      case ']': StateManager.setPenWidth(Math.min(StateManager.getPenWidth() + 1, 10)); break;
      case '\\': StateManager.setFontName(""); StateManager.setFontSize(50); break;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e) {}
  
}
