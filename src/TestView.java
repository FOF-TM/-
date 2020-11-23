import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.io.File;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

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
    Container p = window.getContentPane();
    BoxLayout boxLayout = new BoxLayout(p, BoxLayout.Y_AXIS);
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("文件");
    JMenuItem newItem = new JMenuItem("新建");
    JMenuItem openItem = new JMenuItem("打开");
    JMenuItem saveItem = new JMenuItem("保存");
    JMenuItem exitItem = new JMenuItem("退出");
    JToolBar function = new JToolBar();
    JPanel tool = new JPanel();
    JPanel shape = new JPanel();
    JPanel color = new JPanel();
    JScrollBar scroller = new JScrollBar(JScrollBar.HORIZONTAL,0,10,0,20); // 滑动条，设置工具的大小
    scroller.setUnitIncrement(5);
    scroller.setBlockIncrement(10);
    scroller.addAdjustmentListener(new AdjustmentListener() {
      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        StateManager.setPenWidth(e.getValue());
        System.out.println(e.getValue());
      }
    }); 

    Action pen = new AbstractAction("", new ImageIcon("./image/pen.png")) {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setTool("Pen");
      }
    };
    Action word = new AbstractAction("", new ImageIcon("./image/word.png")) {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setTool("Text");
      }
    };
    Action eraser = new AbstractAction("", new ImageIcon("./image/eraser.png")) {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setTool("Eraser");
      }
    };
    Action previous = new AbstractAction("撤销") {
      @Override
      public void actionPerformed(ActionEvent e) {
        Drawer.undo(false);
      }
    };
    Action shape1 = new AbstractAction("直线") {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setTool("Line");
      }
    };
    Action shape2 = new AbstractAction("圆形") {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setTool("Oval");
      }
    };
    Action shape3 = new AbstractAction("矩形") {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setTool("Rectangle");
      }
    };
    Action color1 = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setPenColor(Color.BLACK);
      }
    };
    Action color2 = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setPenColor(Color.RED);
      }
    };
    Action color3 = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setPenColor(Color.ORANGE);
      }
    };
    Action color4 = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setPenColor(Color.YELLOW);
      }
    };
    Action color5 = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setPenColor(Color.GREEN);
      }
    };
    Action color6 = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setPenColor(Color.BLUE);
      }
    };
    Action color7 = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StateManager.setPenColor(Color.PINK);
      }
    };
    
    fileMenu.add(newItem);
    fileMenu.add(openItem);
    fileMenu.add(saveItem);
    fileMenu.add(exitItem);
    menuBar.add(fileMenu);
    window.setJMenuBar(menuBar);
    JButton penBtn = new JButton(pen);
    JButton wordBtn = new JButton(word);
    JButton eraserBtn = new JButton(eraser);
    JButton preBtn = new JButton(previous);
    tool.setLayout(new GridLayout(1,4,2,2)); //设置工具栏
    shape.setLayout(new GridLayout(1, 3, 2, 2)); // 设置形状栏
    color.setLayout(new GridLayout(2, 4, 0, 0)); // 设置颜色栏
    tool.add(penBtn);
    tool.add(wordBtn);
    tool.add(eraserBtn);
    tool.add(preBtn);
    JPanel toolAndScroller = new JPanel(); 
    toolAndScroller.setLayout(new GridLayout(2, 1, 10, 10));
    toolAndScroller.add(tool);
    toolAndScroller.add(scroller);
    shape.add(new JButton(shape1));
    shape.add(new JButton(shape2));
    shape.add(new JButton(shape3));
    JButton btn1 = new JButton(color1);
    JButton btn2 = new JButton(color2);
    JButton btn3 = new JButton(color3);
    JButton btn4 = new JButton(color4);
    btn1.setBackground(Color.BLACK);
    btn1.setOpaque(true); //foreground设置透明
    btn1.setBorderPainted(false); //最后显示红色
    btn2.setBackground(Color.RED);
    btn2.setOpaque(true); //foreground设置透明
    btn2.setBorderPainted(false); //最后显示红色
    btn3.setBackground(Color.ORANGE);
    btn3.setOpaque(true); //foreground设置透明
    btn3.setBorderPainted(false); //最后显示红色
    btn4.setBackground(Color.YELLOW);
    btn4.setOpaque(true); //foreground设置透明
    btn4.setBorderPainted(false); //最后显示红色
    color.add(btn1);
    color.add(btn2);
    color.add(btn3);
    color.add(btn4);
    btn1 = new JButton(color5);
    btn2 = new JButton(color6);
    btn3 = new JButton(color7);
    btn4 = new JButton(new AbstractAction("颜色选择"){
      @Override
      public void actionPerformed(ActionEvent e) {
        Color result = JColorChooser.showDialog(window, "颜色选择器", Color.WHITE);
        StateManager.setPenColor(result);
      }
    });
    btn1.setBackground(Color.GREEN);
    btn1.setOpaque(true); //foreground设置透明
    btn1.setBorderPainted(false); //最后显示红色
    btn2.setBackground(Color.BLUE);
    btn2.setOpaque(true); //foreground设置透明
    btn2.setBorderPainted(false); //最后显示红色
    btn3.setBackground(Color.PINK);
    btn3.setOpaque(true); //foreground设置透明
    btn3.setBorderPainted(false); //最后显示红色
    color.add(btn1);
    color.add(btn2);
    color.add(btn3);
    color.add(btn4);
    // JButton penBtn = new JButton(pen);
    // JButton colorButton = new JButton(color);
    function.add(toolAndScroller);
    function.addSeparator();
    function.add(shape);
    function.addSeparator();
    function.add(color);
    window.add(function, BorderLayout.NORTH);
    drawingBoard.setPreferredSize(new DimensionUIResource(600, 500));
    window.add(drawingBoard);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.pack();
    window.setVisible(true);
    BufferedImage image = new BufferedImage(drawingBoard.getWidth(), drawingBoard.getHeight(), BufferedImage.TYPE_INT_BGR);
    saveItem.addActionListener(e -> {
      //弹出对话框，另存为
      FileDialog sDialog = new FileDialog(window,"保存图片",FileDialog.SAVE);
      sDialog.setVisible(true);
      String dir = sDialog.getDirectory();
      String file = sDialog.getFile();

      try {
          ImageIO.write(image,"JPEG",new File(dir,file));
      } catch (IOException e1) {
          e1.printStackTrace();
      }
  });
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
     *  6 文字
     *  [ 笔刷宽度减一
     *  ] 笔刷宽度加一
     */
    window.addKeyListener(new TestKeyListener());
  }
}