package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import controller.ColorTransform;
import controller.Features;
import controller.Filter;
import controller.Flip;
import controller.Greyscale;
import utils.ImageUtil;

/**
 * This class represents a GUI view for the photo editing program.
 * Implemented using Java Swing.
 */
public class PictureSwingView extends JFrame implements IPictureView {

  /**
   * Represents the desired file action for the getFilePath method.
   * Dictates whether a file open window or file save window should be displayed for the user.
   */
  public enum FileAction {
    Save, Load
  }


  private ButtonPanel buttonPanel;
  private PicturePanel picturePanel;
  private Histogram histogram;


  /**
   * Instantiate the model with the given model and the appendable is System.out.
   */
  public PictureSwingView() throws IllegalArgumentException {
    super();
    this.setTitle("Picture Editor");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JScrollPane mainScrollPane;
    JScrollPane pictureScrollPane;
    JPanel mainPanel;

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainScrollPane = new JScrollPane(mainPanel);
    this.add(mainScrollPane);

    this.picturePanel = new PicturePanel();
    pictureScrollPane = new JScrollPane(this.picturePanel);
    pictureScrollPane.setPreferredSize(new Dimension(500, 500));
    mainPanel.add(pictureScrollPane);

    //create and add the button panel
    this.buttonPanel = new ButtonPanel();
    this.buttonPanel.setLayout(new GridLayout(5, 3));
    mainPanel.add(this.buttonPanel);

    this.histogram = new Histogram(new ArrayList<>());
    //this.histogram.setPreferredSize(new Dimension(500, 500));
    mainPanel.add(this.histogram);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message,
            "Message", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void addFeatures(Features features) {
    this.buttonPanel.exitButton.addActionListener(evt -> features.exitProgram());
    this.buttonPanel.loadButton.addActionListener(ect -> features.loadPicture());
    this.buttonPanel.greyButton.addActionListener(ect ->
            features.colorTransform(ColorTransform.TransformType.Greyscale));
    this.buttonPanel.sepiaButton.addActionListener(ect ->
            features.colorTransform(ColorTransform.TransformType.Sepia));
    this.buttonPanel.blurButton.addActionListener(ect ->
            features.filter(Filter.FilterType.Blur));
    this.buttonPanel.sharpenButton.addActionListener(ect ->
            features.filter(Filter.FilterType.Sharpen));
    this.buttonPanel.redButton.addActionListener(ect ->
            features.viewComponent(Greyscale.Greytype.Red));
    this.buttonPanel.greenButton.addActionListener(ect ->
            features.viewComponent(Greyscale.Greytype.Green));
    this.buttonPanel.blueButton.addActionListener(ect ->
            features.viewComponent(Greyscale.Greytype.Blue));
    this.buttonPanel.lumaButton.addActionListener(ect ->
            features.viewComponent(Greyscale.Greytype.Luma));
    this.buttonPanel.intensityButton.addActionListener(ect ->
            features.viewComponent(Greyscale.Greytype.Intensity));
    this.buttonPanel.valueButton.addActionListener(ect ->
            features.viewComponent(Greyscale.Greytype.Value));
    this.buttonPanel.saveButton.addActionListener(ect -> features.savePicture());
    this.buttonPanel.hFlipButton.addActionListener(ect ->
            features.flipPicture(Flip.Fliptype.Horizontal));
    this.buttonPanel.vFlipButton.addActionListener(ect ->
            features.flipPicture(Flip.Fliptype.Vertical));
    this.buttonPanel.doubleFlipButton.addActionListener(ect ->
            features.flipPicture(Flip.Fliptype.Both));
    this.buttonPanel.brightenButton.addActionListener(ect ->
            features.brighten(25));
    this.buttonPanel.dimButton.addActionListener(ect ->
            features.brighten(-25));
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void switchPicture(BufferedImage picture, String picName) throws IllegalArgumentException {
    if (picture == null || picName == null) {
      throw new IllegalArgumentException("Given BufferedImage cannot be null.");
    }
    this.picturePanel.changeImage(picture);
    this.histogram.updatePicture(new ImageUtil()
            .bufferedImageToPicture(picture, picName).getCopy());
    this.refresh();
  }

  @Override
  public File getFilePath(FileAction fileAction) {
    final JFileChooser fChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("PPM, JPG, "
            + "PNG, & BMP Images", "jpg", "jpeg", "png", "ppm");
    fChooser.setFileFilter(filter);
    int toReturn;
    if (fileAction == FileAction.Load) {
      toReturn = fChooser.showOpenDialog(this);
    } else if (fileAction == FileAction.Save) {
      toReturn = fChooser.showSaveDialog(this);
    } else {
      toReturn = JFileChooser.ERROR_OPTION;
    }
    if (toReturn == JFileChooser.APPROVE_OPTION) {
      return fChooser.getSelectedFile();
    } else if (toReturn == JFileChooser.ERROR_OPTION) {
      throw new IllegalStateException("Failed to load given image.");
    } else {
      return null;
    }
  }
}
