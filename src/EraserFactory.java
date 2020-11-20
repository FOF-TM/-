import java.awt.Color;

public class EraserFactory implements GraphFactory {

  private EraserFactory() {};
  private static EraserFactory instance = new EraserFactory();


  public static GraphFactory getInstance() {
    return instance;
  }

  @Override
  public Graph genGraph(int x1, int y1, int x2, int y2, Color c) {
    return new Eraser(x1, y1, x2, y2, c);
  }
  
}
