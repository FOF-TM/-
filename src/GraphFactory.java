/**
 * 工厂方法模式
 * 每个 Graph 对应一个 GraphFactory
 */
public interface GraphFactory {
  /**
   * 生成工厂对应的 Graph 对象
   */
  public Graph genGraph(int x1, int y1, int x2, int y2);
}
