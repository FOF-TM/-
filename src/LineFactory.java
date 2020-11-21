import java.awt.Color;

public class LineFactory implements GraphFactory {

  private LineFactory() {};
  private static LineFactory instance = new LineFactory();


  public static GraphFactory getInstance() {
    return instance;
  }

  @Override
  public Graph genGraph(int x1, int y1, int x2, int y2, Color c) {
    Controller.getInstance().updateX1Y1withX2Y2();
    return new Line(x1, y1, x2, y2, c);
  }
  
}
