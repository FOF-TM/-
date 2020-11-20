import java.awt.Color;
import java.awt.event.*;

/**
 * 用于测试后端的键盘事件监听器
 */
public class TestKeyListener implements KeyListener {

  @Override
  public void keyTyped(KeyEvent e) {
    switch (e.getKeyChar()) {
      case 'q': StateManager.setPenColor(Color.BLACK); break;
      case 'w': StateManager.setPenColor(Color.BLUE); break;
      case 'e': StateManager.setPenColor(Color.RED); break;
      case 'r': StateManager.setPenColor(Color.GREEN); break;
      case '1': StateManager.setTool("Line"); break;
      case '2': StateManager.setTool("Eraser"); break;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e) {}
  
}
