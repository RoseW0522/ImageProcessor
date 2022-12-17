package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.IPictureModel;
import view.IPictureView;

/**
 * Implementation of the controller for the IPicture interface.
 * Handles the user input and calls the Picture Command objects to edit Pictures.
 * Pictures are stored in this model.
 */
public class PictureControllerImpl implements PictureController {

  private final IPictureModel model;
  private final Readable rd;

  /**
   * Constructs a controller for the given IPictureModel with the given view
   * as output and the given Readable as input to read user input.
   *
   * @param model IPictureModel that stores the Pictures being edited
   * @param view  view for this program
   * @param rd    Readable that takes input from the user
   * @throws IllegalArgumentException if any arguments are null
   */
  public PictureControllerImpl(IPictureModel model, IPictureView view, Readable rd)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model must not be null.");
    }
    if (view == null) {
      throw new IllegalArgumentException("View must not be null.");
    }
    if (rd == null) {
      throw new IllegalArgumentException("Readable must not be null.");
    }

    this.model = model;
    this.rd = rd;
  }

  /**
   * Controller object for the text version of the editor program.
   * (Does not include a view).
   *
   * @param model IPictureModel that stores the Pictures being edited
   * @param rd    Readable that takes input from the user
   * @throws IllegalArgumentException if any arguments are null
   */
  public PictureControllerImpl(IPictureModel model, Readable rd)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model must not be null.");
    }
    if (rd == null) {
      throw new IllegalArgumentException("Readable must not be null.");
    }

    this.model = model;
    this.rd = rd;
  }

  @Override
  public void editPhoto() throws IllegalStateException, IOException {
    Scanner scan = new Scanner(rd);

    Map<String, Function<Scanner, PictureCommand>> knownCommands;
    knownCommands = new HashMap<>();
    knownCommands.put("red-component", s -> new Greyscale(Greyscale.Greytype.Red,
            scan.next(), scan.next()));
    knownCommands.put("green-component", s -> new Greyscale(Greyscale.Greytype.Green,
            scan.next(), scan.next()));
    knownCommands.put("blue-component", s -> new Greyscale(Greyscale.Greytype.Blue,
            scan.next(), scan.next()));
    knownCommands.put("value-component", s -> new Greyscale(Greyscale.Greytype.Value,
            scan.next(), scan.next()));
    knownCommands.put("luma-component", s -> new Greyscale(Greyscale.Greytype.Luma,
            scan.next(), scan.next()));
    knownCommands.put("intensity-component", s -> new Greyscale(Greyscale.Greytype.Intensity,
            scan.next(), scan.next()));
    knownCommands.put("horizontal-flip", s -> new Flip(Flip.Fliptype.Horizontal,
            scan.next(), scan.next()));
    knownCommands.put("vertical-flip", s -> new Flip(Flip.Fliptype.Vertical,
            scan.next(), scan.next()));
    knownCommands.put("double-flip", s -> new Flip(Flip.Fliptype.Both,
            scan.next(), scan.next()));
    knownCommands.put("load", s -> new Load(scan.next(), scan.next()));
    knownCommands.put("save", s -> new Save(scan.next(), scan.next()));
    knownCommands.put("brighten", s -> new Brighten(scan.nextInt(), scan.next(), scan.next()));
    knownCommands.put("blur", s -> new Filter(Filter.FilterType.Blur, scan.next(), scan.next()));
    knownCommands.put("sharpen", s -> new Filter(Filter.FilterType.Sharpen,
            scan.next(), scan.next()));
    knownCommands.put("greyscale", s -> new ColorTransform(ColorTransform.TransformType.Greyscale,
            scan.next(), scan.next()));
    knownCommands.put("sepia", s -> new ColorTransform(ColorTransform.TransformType.Sepia,
            scan.next(), scan.next()));

    while (scan.hasNext()) {
      String input = scan.next();

      if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
        //exit this program if q or quit is input
        return;
      }
      Function<Scanner, PictureCommand> command = knownCommands.getOrDefault(input, null);
      if (command == null) {
        throw new IllegalArgumentException("Must input a valid command.");
      } else {
        PictureCommand pc = command.apply(scan);
        pc.execute(this.model);
      }
    }
  }
}
