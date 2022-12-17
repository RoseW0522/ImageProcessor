package view;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * Represents a panel containing all buttons for the picture editing program GUI.
 */
public class ButtonPanel extends JPanel {
  protected JButton saveButton;
  protected JButton loadButton;
  protected JButton exitButton;
  protected JButton greyButton;
  protected JButton sepiaButton;
  protected JButton sharpenButton;
  protected JButton blurButton;
  protected JButton redButton;
  protected JButton greenButton;
  protected JButton blueButton;
  protected JButton lumaButton;
  protected JButton intensityButton;
  protected JButton valueButton;
  protected JButton hFlipButton;
  protected JButton vFlipButton;
  protected JButton doubleFlipButton;
  protected JButton brightenButton;
  protected JButton dimButton;

  /**
   * Constructs the button panel, assigning all buttons their names and action commands.
   */
  public ButtonPanel() {
    this.setLayout(new FlowLayout());

    this.saveButton = new JButton("Save");
    this.saveButton.setActionCommand("Save this image");
    this.add(this.saveButton);

    this.loadButton = new JButton("Load");
    this.loadButton.setActionCommand("Load an image");
    this.add(this.loadButton);

    this.exitButton = new JButton("Exit");
    this.exitButton.setActionCommand("Exit the program");
    this.add(this.exitButton);

    this.greyButton = new JButton("Greyscale");
    this.greyButton.setActionCommand("Greyscale this image");
    this.add(this.greyButton);

    this.sepiaButton = new JButton("Sepia");
    this.sepiaButton.setActionCommand("Sepia this image");
    this.add(this.sepiaButton);

    this.blurButton = new JButton("Blur");
    this.blurButton.setActionCommand("Blur this image");
    this.add(this.blurButton);

    this.sharpenButton = new JButton("Sharpen");
    this.sharpenButton.setActionCommand("Sharpen this image");
    this.add(this.sharpenButton);

    this.redButton = new JButton("Red Component");
    this.redButton.setActionCommand("Show red component");
    this.add(this.redButton);

    this.blueButton = new JButton("Blue Component");
    this.blueButton.setActionCommand("Show blue component");
    this.add(this.blueButton);

    this.greenButton = new JButton("Green Component");
    this.greenButton.setActionCommand("Show green component");
    this.add(this.greenButton);

    this.lumaButton = new JButton("Luma Component");
    this.lumaButton.setActionCommand("Show luma component");
    this.add(this.lumaButton);

    this.intensityButton = new JButton("Intensity Component");
    this.intensityButton.setActionCommand("Show intensity component");
    this.add(this.intensityButton);

    this.valueButton = new JButton("Value Component");
    this.valueButton.setActionCommand("Show value component");
    this.add(this.valueButton);

    this.hFlipButton = new JButton("Horizontal Flip");
    this.hFlipButton.setActionCommand("Flip image horizontal");
    this.add(this.hFlipButton);

    this.vFlipButton = new JButton("Vertical Flip");
    this.vFlipButton.setActionCommand("Flip image vertical");
    this.add(this.vFlipButton);

    this.doubleFlipButton = new JButton("Hor. & Vert. Flip");
    this.doubleFlipButton.setActionCommand("Flip image horizontal and vertical");
    this.add(this.doubleFlipButton);

    this.brightenButton = new JButton("Brighten");
    this.brightenButton.setActionCommand("Brighten image");
    this.add(this.brightenButton);

    this.dimButton = new JButton("Darken");
    this.dimButton.setActionCommand("Darken image");
    this.add(this.dimButton);
  }
}
