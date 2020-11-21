import java.awt.Color;

public class PenFactory implements GraphFactory {

  private PenFactory() {};
  private static PenFactory instance = new PenFactory();


  public static GraphFactory getInstance() {
    return instance;
  }

  @Override
  public Graph genGraph(int x1, int y1, int x2, int y2, Color c, MouseState s) {
    Controller.getInstance().updateX1Y1withX2Y2();
    return new Pen(x1, y1, x2, y2, c);
  }
  
}
