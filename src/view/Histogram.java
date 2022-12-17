package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import picture.IPicture;
import picture.IPixel;
import picture.Picture;

/**
 * This class creates a histogram by the red, green, blue components in the Picture.
 */
public class Histogram extends JPanel {
  private final int width;
  private final Color red;
  private final Color green;
  private final Color blue;
  private IPicture picture;

  /**
   * Instantiate a Histogram with the give pixels and collect the red,
   * green, blue components into a HashMap.
   * Use the three HashMap to present the new Histogram.
   *
   * @param pixels The Picture for creating a histogram
   */
  public Histogram(List<List<IPixel>> pixels) {
    this.width = 1;
    this.red = new Color(255, 0, 0);
    this.green = new Color(0, 255, 0);
    this.blue = new Color(0, 0, 255);
    this.picture = new Picture(pixels, "filename");
    this.setPreferredSize(new Dimension(500, 500));
  }

  protected void updatePicture(List<List<IPixel>> pixels) {
    this.picture = new Picture(pixels, "filename");
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graph = (Graphics2D) g;
    graph.setColor(Color.WHITE);

    HashMap<Integer, Integer> redMap = this.picture.redMap();
    HashMap<Integer, Integer> greenMap = this.picture.greenMap();
    HashMap<Integer, Integer> blueMap = this.picture.blueMap();

    for (int key : redMap.keySet()) {
      Rectangle2D redBar = new Rectangle2D.Float((float) key * this.picture.getWidth() / 255,
              (float) ((float) this.picture.getHeight()
                      - Math.min(255, Math.pow(redMap.get(key), 2) * 0.01)), this.width,
              (float) Math.min(255, Math.pow(redMap.get(key), 2) * 0.01));

      Rectangle2D greenBar = new Rectangle2D.Float((float) key * this.picture.getWidth() / 255,
              (float) ((float) this.picture.getHeight()
                      - Math.min(255, Math.pow(greenMap.get(key), 2) * 0.01)), this.width,
              (float) Math.min(255, Math.pow(greenMap.get(key), 2) * 0.01));

      Rectangle2D blueBar = new Rectangle2D.Float((float) key * this.picture.getWidth() / 255,
              (float) ((float) this.picture.getHeight()
                      - Math.min(255, Math.pow(blueMap.get(key), 2) * 0.01)), this.width,
              (float) Math.min(255, Math.pow(blueMap.get(key), 2) * 0.01));

      graph.setPaint(red);
      graph.setPaint(green);
      graph.setPaint(blue);

      graph.draw(redBar);
      graph.fill(redBar);

      graph.draw(greenBar);
      graph.fill(greenBar);

      graph.draw(blueBar);
      graph.fill(blueBar);
    }
  }
}