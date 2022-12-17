package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Represents the Panel displaying the Picture in the program GUI.
 */
public class PicturePanel extends JPanel {

  private Image image;

  /**
   * Default constructor for a PicturePanel, does not set an image.
   * Image should be set with changeImage method.
   */
  public PicturePanel() {
    this.setPreferredSize(new Dimension(100, 100));
  }

  /**
   * Constructor for a PicturePanel object, the panel for the picture editor program GUI.
   * Displays the given image on the panel, with a preferred size of the image's dimensions.
   */
  public PicturePanel(Image image) {
    super();
    if (image == null) {
      throw new IllegalArgumentException("Given image cannot be null.");
    }

    this.image = image;
    this.setPreferredSize(new Dimension(this.image.getWidth(null),
            this.image.getHeight(null)));
  }

  /**
   * Changes the picture displayed by the panel to the given image.
   * Sets the panel's preferred size to the image's dimensions.
   *
   * @param image Image to be displayed on the panel
   * @throws IllegalArgumentException if the given image is null
   */
  protected void changeImage(BufferedImage image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    this.image = image;
    this.setPreferredSize(new Dimension(this.image.getWidth(null),
            this.image.getHeight(null)));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(this.image, 0, 0, null);
  }
}
