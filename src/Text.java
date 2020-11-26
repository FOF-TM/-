import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;

public class Text extends Graph {

  private Font font = null;
  private String text = null;

  public Text(int x1, int y1, int x2, int y2, Color c) {
    super(x1, y1, x2, y2, c, StateManager.getPenWidth());
    font = StateManager.getFont();
    text = StateManager.getText();
  }

  @Override
  public void innerDraw(Graphics2D pen) {
    Font fontBackup = StateManager.getFont();
    pen.setFont(font);
    System.out.println("123");
    pen.drawString(text, x2, y2);
    pen.setFont(fontBackup);
  }

}
