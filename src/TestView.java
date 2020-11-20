import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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

    // 背景需要设置为白色，否则橡皮看起来很怪
    window.setBackground(Color.WHITE);
    window.setSize(640, 360);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setVisible(true);

    // 下面的三行是前后端相互绑定所必须的语句
    StateManager.init((Graphics2D) window.getGraphics());
    window.addMouseListener(Controller.getInstance());
    window.addMouseMotionListener(Controller.getInstance());

    /**
     * 由于测试窗口中没有按钮，因此使用键盘来分发功能进行测试
     * 按键功能如下：
     *  qwer 用于切换颜色
     *  1 铅笔
     *  2 橡皮
     */
    window.addKeyListener(new TestKeyListener());
  }
}