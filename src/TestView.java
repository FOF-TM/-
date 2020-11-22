import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.io.File;
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
    JScrollBar scroller = new JScrollBar(); // 滑动条，设置工具的大小
    scroller.setOrientation(JScrollBar.HORIZONTAL);
    scroller.setValue(0);
    scroller.setVisibleAmount(20);
		// minmum值设为10
		scroller.setMinimum(10);
		// maximan值设为60,因为minmum值设为10，可滚动的区域大小为60-20-10=30
		// 个刻度，滚动范围在10~40中。
		scroller.setMaximum(60);
		// 当鼠标在滚动轴列上按一下时，滚动轴一次所跳的区块大小为5个刻度
		scroller.setBlockIncrement(5);

    Action pen = new AbstractAction("", new ImageIcon("./image/pen.png")) {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    };
    Action word = new AbstractAction("", new ImageIcon("./image/word.png")) {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    };
    Action eraser = new AbstractAction("", new ImageIcon("./image/eraser.png")) {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    };
    Action shape1 = new AbstractAction("矩形") {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    };
    Action shape2 = new AbstractAction("圆形") {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    };
    Action shape3 = new AbstractAction("三角形") {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    };
    Action color1 = new AbstractAction("颜色") {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    };
    Action color2 = new AbstractAction("颜色") {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    };
    Action color3 = new AbstractAction("颜色") {
      @Override
      public void actionPerformed(ActionEvent e) {
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
    tool.setLayout(new GridLayout(1,3,2,2)); //设置工具栏
    shape.setLayout(new GridLayout(3, 4, 2, 2)); // 设置形状栏
    color.setLayout(new GridLayout(2, 4, 0, 0)); // 设置颜色栏
    tool.add(penBtn);
    tool.add(wordBtn);
    tool.add(eraserBtn);
    JPanel toolAndScroller = new JPanel(); 
    toolAndScroller.setLayout(new GridLayout(3, 1, 10, 10));
    toolAndScroller.add(tool);
    toolAndScroller.add(scroller);
    for(int i = 0; i < 12; i++) {
      shape.add(new JButton("shape" +(i+1) + ""));
    }
    JButton btn1 = new JButton();
    JButton btn2 = new JButton();
    JButton btn3 = new JButton();
    JButton btn4 = new JButton();
    btn1.setBackground(Color.BLACK);
    btn2.setBackground(Color.GRAY);
    btn3.setBackground(Color.RED);
    btn4.setBackground(Color.lightGray);
    color.add(btn1);
    color.add(btn2);
    color.add(btn3);
    color.add(btn4);
    btn1 = new JButton();
    btn2 = new JButton();
    btn3 = new JButton();
    btn4 = new JButton(new AbstractAction("颜色选择"){
      @Override
      public void actionPerformed(ActionEvent e) {
        Color result = JColorChooser.showDialog(window, "颜色选择器", Color.WHITE);
        StateManager.setPenColor(result);
      }
    });
    btn1.setBackground(Color.BLUE);
    btn2.setBackground(Color.YELLOW);
    btn3.setBackground(Color.GREEN);
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
    Graphics graph = image.getGraphics();
    graph = drawingBoard.getGraphics();
    graph.drawImage(image, 0, 0, null);
    saveItem.addActionListener(e -> {
      //弹出对话框，另存为
      FileDialog sDialog = new FileDialog(window,"保存图片",FileDialog.SAVE);
      sDialog.setVisible(true);
      String dir = sDialog.getDirectory();
      String file = sDialog.getFile();

      try {
          ImageIO.write((Image)drawingBoard.getGraphics(),"JPEG",new File(dir,file));
      } catch (IOException e1) {
          e1.printStackTrace();
      }
  });
    // 画板的背景需要设置为白色，否则橡皮看起来很怪
    drawingBoard.setBackground(Color.WHITE);
    // 用画板实例来注册状态管理器
    StateManager.init(drawingBoard, graph);
    /**
     * 由于测试窗口中没有按钮，因此使用键盘来分发功能进行测试
     * 按键功能如下：
     *  qwer 用于切换颜色
     *  ` 撤销
     *  1 铅笔
     *  2 橡皮
     */
    window.addKeyListener(new TestKeyListener());
  }
}