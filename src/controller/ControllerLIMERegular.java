package controller;

import controller.command.ApplyCommand;
import controller.command.CreateCommand;
import controller.command.CreateMultiCommand;
import controller.command.HideCommand;
import controller.command.ICommand;
import controller.command.RemoveCommand;
import controller.command.SaveAllCommand;
import controller.command.SaveCommand;
import controller.command.SelectCommand;
import controller.command.ShowCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.IModelLIME;
import view.ILayerProcessorView;
import view.LayerProcessorView;

/**
 * Creates a Controller for the ImageProcessor that is just used to either interactive or
 * file-based.
 */
public class ControllerLIMERegular implements IControllerLIME {

  private final Readable in;
  private final ILayerProcessorView view;
  private final Map<String, ICommand> commands;
  private Boolean shouldQuit;

  /**
   * Creates an {@code ImageProcessorControllerImpl} that takes in the view and model part of the
   * ImageProcessor.
   *
   * @param view  view part of the ImageProcessor.
   * @param model model part of the ImageProcessor.
   * @throws IllegalArgumentException if the parameters are null.
   */
  public ControllerLIMERegular(Readable in, IModelLIME model,
      ILayerProcessorView view) throws IllegalArgumentException {
    if (in == null || view == null || model == null) {
      throw new IllegalArgumentException("Parameters cannot be null.");
    }
    this.in = in;
    this.view = view;
    this.shouldQuit = Boolean.FALSE;
    this.commands = new HashMap<String, ICommand>();
    this.commands.put("apply", new ApplyCommand(model));
    this.commands.put("create", new CreateCommand(model));
    this.commands.put("createmulti", new CreateMultiCommand(model));
    this.commands.put("hide", new HideCommand(model));
    this.commands.put("q", (line) -> this.shouldQuit = Boolean.TRUE);
    this.commands.put("remove", new RemoveCommand(model));
    this.commands.put("saveall", new SaveAllCommand(model));
    this.commands.put("save", new SaveCommand(model));
    this.commands.put("select", new SelectCommand(model));
    this.commands.put("show", new ShowCommand(model));
  }

  /**
   * Creates an {@code ImageProcessorControllerImpl} that takes in the view and model part of the
   * ImageProcessor.
   *
   * @param model model part of the ImageProcessor.
   * @throws IllegalArgumentException if the parameters are null.
   */
  public ControllerLIMERegular(Readable in,
      IModelLIME model) throws IllegalArgumentException {
    this(in, model, new LayerProcessorView(model, System.out));
  }


  @Override
  public void run() {
    Scanner scanner = new Scanner(in);
    while (scanner.hasNextLine()) {
      String nextLine = scanner.nextLine();
      this.view.renderMessage("received: " + nextLine);

      String[] next = nextLine.split("\\s");
      List<String> line = new ArrayList<>(Arrays.asList(next));
      String commandWord = line.remove(0).toLowerCase();
      ICommand command = this.commands.getOrDefault(commandWord, null);
      if (command == null) {
        this.view.renderMessage("Invalid Command.");
      } else {
        try {
          command.perform(line);
          this.view.renderLayerInfo();
        } catch (IllegalArgumentException e) {
          this.view.renderMessage(e.getMessage());
        }
      }
      if (this.shouldQuit) {
        this.view.renderMessage("Quitting...");
        return;
      }
    }
  }
}
