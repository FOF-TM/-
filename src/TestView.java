import javax.swing.*;
import java.awt.*;

/**
 * 用于测试后端的临时窗口
 */
public class TestView {
  public static void main(String[] args) {
    JFrame window = new JFrame("Test Window");

    JPanel drawingBoard = new JPanel() {
      /**
       * 画板实例必须重写的方法，方便撤销及拖动绘图
       * 同时解决改变窗口大小时的自动重绘导致的图像消失
       */
      @Override
      public void paint(Graphics g) {
        super.paint(g);
        StateManager.repaintHistory(g);
      }
    };
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
     *  qwer 用于切换颜色
     *  ` 撤销
     *  1 铅笔
     *  2 橡皮
     *  3 矩形
     *  4 圆形
     *  5 直线
     *  [ 笔刷宽度减一
     *  ] 笔刷宽度加一
     */
    window.addKeyListener(new TestKeyListener());
  }
}