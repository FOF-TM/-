import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 * 用于测试后端的临时窗口
 */
public class TestView {
  public static void main(String[] args) {
    JFrame window = new JFrame("Test Window") {
      private static final long serialVersionUID = 1L;
      /**
       * 用于防止改变窗口大小时绘制的内容消失
       */
      @Override
      public void paint(Graphics g) {
        Drawer.repaint();
      }
    };

    JPanel drawingBoard = new JPanel();
    window.add(drawingBoard);
    window.setSize(640, 360);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setVisible(true);

    // 画板的背景需要设置为白色，否则橡皮看起来很怪
    drawingBoard.setBackground(Color.WHITE);
    // 用画板实例来注册状态管理器
    StateManager.init(drawingBoard);

    /**
     * 由于测试窗口中没有按钮，因此使用键盘来分发功能进行测试
     * 按键功能如下：
     *  空格 清空画板内容（假）
     *  qwer 用于切换颜色
     *  1 铅笔
     *  2 橡皮
     */
    window.addKeyListener(new TestKeyListener());
  }
}