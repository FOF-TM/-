import java.awt.Color;
import java.awt.Graphics2D;

public class Text extends Graph {

  public Text(int x1, int y1, int x2, int y2, Color c) {
    super(x1, y1, x2, y2, c, StateManager.getPenWidth());
  }

  @Override
  public void innerDraw(Graphics2D pen) {
    pen.drawString(StateManager.getText(), x2, y2);
  }

}
