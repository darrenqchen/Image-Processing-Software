package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.IViewModelLIME;
import model.layer.ILayer;

/**
 * GUI view for LIME models. Exposes model actions and information in a GUI using swing. Broadcasts
 * events to the controller.
 */
public class GUIView extends JFrame implements ILayerGUIView {

  private final IViewModelLIME roModel;
  private final JPanel mainPanel;

  // bottomBorder
  private final JLabel messageDisplayer;

  // topBorder
  private final JButton loadButton;
  private final JButton loadAllButton;
  private final JButton saveAllButton;
  private final JButton saveCurrentButton;

  // leftBorder
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton greyscaleButton;
  private final JButton sepiaButton;
  private final JButton mosaicButton;
  private final JTextField mosaicSeeds;

  private final JButton downscaleButton;
  private final JTextField downscaleX;
  private final JTextField downscaleY;

  private final JTextField checkerSize;
  private final JTextField checkerColumns;
  private final JTextField checkerRows;
  private final JButton colorChooserButton1;
  private final JLabel colorChooserDisplay1;
  private final JButton colorChooserButton2;
  private final JLabel colorChooserDisplay2;
  private final JButton buildCheckerButton;
  // middle
  private final JLabel image;
  private final JPanel layerContainer;
  // rightBorder
  private final JButton x;
  private final JButton vis;
  private final JTextField layerName;
  private final JButton renameButton;
  private final List<JButton> layerSelectButtons;
  private Color color1 = Color.white;
  private Color color2 = Color.white;
  private ActionListener listener;

  /**
   * Constructs the view, loads all of the components of the view, certifiably spaghetti.
   *
   * @param roModel the model that contains the layer information.
   */
  public GUIView(IViewModelLIME roModel) {
    super();

    this.roModel = roModel;

    setTitle("Image Processor (Photoshop got neutered?)");
    setSize(1200, 800);

    mainPanel = new JPanel();
    mainPanel.setBackground(Color.GRAY);
    mainPanel.setLayout(new BorderLayout(5, 5));
    add(mainPanel);

    // bottomBorder
    // Message field for error alerts.
    JPanel bottomBorder = new JPanel();
    mainPanel.add(bottomBorder, BorderLayout.SOUTH);
    bottomBorder.setBorder(BorderFactory.createTitledBorder("Message Box:"));
    messageDisplayer = new JLabel("Where the message will be displayed if anything happens.");
    JScrollPane messageScroller = new JScrollPane(messageDisplayer);
    bottomBorder.add(messageScroller);

    // topBorder
    // These three trigger file dialogues.
    JPanel topBorder = new JPanel(new FlowLayout()); // creating and removing layers
    mainPanel.add(topBorder, BorderLayout.NORTH);
    loadButton = new JButton("Load New Layer");
    loadAllButton = new JButton("Load a Folder of Layers");
    saveCurrentButton = new JButton("Save Current Layer");
    saveAllButton = new JButton("Save All");

    topBorder.add(loadButton);
    topBorder.add(loadAllButton);
    topBorder.add(saveCurrentButton);
    topBorder.add(saveAllButton);

    // leftBorder
    // Layer transformations and checkerboard builder
    JPanel leftBorder = new JPanel();
    mainPanel.add(leftBorder, BorderLayout.WEST);
    leftBorder.setLayout(new BoxLayout(leftBorder, BoxLayout.Y_AXIS));

    JPanel transformationPanel = new JPanel();
    leftBorder.add(transformationPanel);
    transformationPanel.setLayout(new BoxLayout(transformationPanel, BoxLayout.Y_AXIS));
    transformationPanel.setBorder(BorderFactory.createTitledBorder("Image Transformations"));
    transformationPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    blurButton = new JButton("Blur Image");
    sharpenButton = new JButton("Sharpen Image");
    greyscaleButton = new JButton("Greyscale Image");
    sepiaButton = new JButton("Sepia Image");
    mosaicButton = new JButton("Mosaic Image");
    mosaicSeeds = new JTextField("Enter the number of seeds you want.");

    downscaleButton = new JButton("Downscale Images");
    downscaleX = new JTextField("1");
    downscaleX.setBorder(BorderFactory.createTitledBorder("X Scalar"));
    downscaleY = new JTextField("1");
    downscaleY.setBorder(BorderFactory.createTitledBorder("Y Scalar"));

    transformationPanel.add(blurButton);
    transformationPanel.add(sharpenButton);
    transformationPanel.add(greyscaleButton);
    transformationPanel.add(sepiaButton);
    transformationPanel.add(downscaleButton);
    transformationPanel.add(downscaleX);
    transformationPanel.add(downscaleY);
    transformationPanel.add(mosaicButton);
    transformationPanel.add(mosaicSeeds);

    JPanel checkerboardPanel = new JPanel();
    leftBorder.add(checkerboardPanel);
    checkerboardPanel.setLayout(new BoxLayout(checkerboardPanel, BoxLayout.Y_AXIS));
    checkerboardPanel.setBorder(BorderFactory.createTitledBorder("Checkerboard"));
    checkerboardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

    checkerSize = new JTextField();
    checkerSize.setBorder(BorderFactory.createTitledBorder("Enter the checker size"));
    checkerSize.setPreferredSize(new Dimension(150, 30));

    checkerColumns = new JTextField();
    checkerColumns.setBorder(BorderFactory.createTitledBorder("Enter the column size"));
    checkerColumns.setPreferredSize(new Dimension(150, 30));

    checkerRows = new JTextField();
    checkerRows.setBorder(BorderFactory.createTitledBorder("Enter the row size"));
    checkerRows.setPreferredSize(new Dimension(150, 30));

    checkerboardPanel.add(checkerSize);
    checkerboardPanel.add(checkerColumns);
    checkerboardPanel.add(checkerRows);

    JPanel colorChooserPanel1 = new JPanel();
    checkerboardPanel.add(colorChooserPanel1);
    colorChooserPanel1.setLayout(new FlowLayout());
    colorChooserButton1 = new JButton("Choose the first color");
    colorChooserPanel1.add(colorChooserButton1);
    colorChooserDisplay1 = new JLabel("      ");
    colorChooserPanel1.add(colorChooserDisplay1);
    colorChooserDisplay1.setOpaque(true); //so that background color shows up
    colorChooserDisplay1.setBackground(Color.WHITE);

    JPanel colorChooserPanel2 = new JPanel();
    checkerboardPanel.add(colorChooserPanel2);
    colorChooserPanel2.setLayout(new FlowLayout());
    colorChooserButton2 = new JButton("Choose the second color");
    colorChooserPanel2.add(colorChooserButton2);
    colorChooserDisplay2 = new JLabel("      ");
    colorChooserPanel2.add(colorChooserDisplay2);
    colorChooserDisplay2.setOpaque(true); //so that background color shows up
    colorChooserDisplay2.setBackground(Color.WHITE);

    buildCheckerButton = new JButton("Build checkerboard");
    checkerboardPanel.add(buildCheckerButton);

    // middle
    // Displays the current main image.

    JPanel middle;
    middle = new JPanel();
    mainPanel.add(middle, BorderLayout.CENTER);
    middle.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    middle.setLayout(new GridLayout(1, 1, 10, 10));
    image = new JLabel();
    JScrollPane imageScroll = new JScrollPane(image);
    middle.add(imageScroll);
    middle.setSize(new Dimension(620, 630));

    this.setupImage();

    // Right panel
    // Layer information and operations.
    JPanel rightBorder;
    rightBorder = new JPanel();
    JScrollPane layersScroll = new JScrollPane(rightBorder);
    mainPanel.add(layersScroll, BorderLayout.EAST);
    rightBorder.setLayout(new BoxLayout(rightBorder, BoxLayout.Y_AXIS));
    rightBorder.setBorder(BorderFactory.createTitledBorder("Layers:"));

    JPanel layerOptions = new JPanel(new GridLayout(2, 2));
    rightBorder.add(layerOptions);
    layerOptions.setBorder(BorderFactory.createTitledBorder("Layer Options:"));
    layerOptions.setSize(new Dimension(200, 200));

    x = new JButton("X");
    layerOptions.add(x);
    x.setPreferredSize(new Dimension(30, 30));

    vis = new JButton();
    layerOptions.add(vis);
    vis.setIcon(new ImageIcon("res/DontTouch/eye.png"));
    vis.setPreferredSize(new Dimension(30, 30));

    layerName = new JTextField("Change name here");
    layerOptions.add(layerName);
    layerName.setSize(new Dimension(200, 75));

    renameButton = new JButton("Rename");
    layerOptions.add(renameButton);

    this.layerSelectButtons = new ArrayList<>();
    layerContainer = new JPanel();
    rightBorder.add(layerContainer);
    layerContainer.setLayout(new BoxLayout(layerContainer, BoxLayout.Y_AXIS));
    this.setupLayers();
  }

  /**
   * Builds and displays the main image if there is one. Otherwise displays the noImages image.
   */
  protected void setupImage() {
    try {
      ILayer topLayer = this.roModel.getCurrentLayer();
      //should be in one of the action performed
      BufferedImage bi = topLayer.toBufferedImage();
      image.setIcon(new ImageIcon(bi));
    } catch (IllegalStateException e) {
      image.setIcon(new ImageIcon("res/DontTouch/noImages.png"));
    }
  }

  /**
   * Builds and displays the layers selector on the right side of the GUI.
   */
  protected void setupLayers() {
    layerContainer.removeAll();
    for (ILayer l : this.roModel.getAllLayers()) {
      JButton layer = new JButton(l.getName());

      layer.setPreferredSize(new Dimension(175, 70));
      layer.setToolTipText(l.getName());
      layerContainer.add(layer);

      layer.addActionListener(this.listener);
      layer.setActionCommand("select " + l.getName());

      layerSelectButtons.add(layer);
    }
  }

  @Override
  public void colorChooser1() {
    color1 = JColorChooser
        .showDialog(GUIView.this, "Choose a color", colorChooserDisplay1.getBackground());
    colorChooserDisplay1.setBackground(color1);
  }

  @Override
  public void colorChooser2() {
    color2 = JColorChooser
        .showDialog(GUIView.this, "Choose a color", colorChooserDisplay2.getBackground());
    colorChooserDisplay2.setBackground(color2);
  }

  @Override
  public void setActionListeners(ActionListener e) throws IllegalArgumentException {
    if (e == null) {
      throw new IllegalArgumentException("Action listener cannot be null");
    }
    this.listener = e;

    loadButton.addActionListener(e);
    loadAllButton.addActionListener(e);
    saveAllButton.addActionListener(e);
    saveCurrentButton.addActionListener(e);

    blurButton.addActionListener(e);
    sharpenButton.addActionListener(e);
    greyscaleButton.addActionListener(e);
    sepiaButton.addActionListener(e);
    mosaicButton.addActionListener(e);
    downscaleButton.addActionListener(e);

    colorChooserButton1.addActionListener(e);
    colorChooserButton2.addActionListener(e);
    buildCheckerButton.addActionListener(e);

    x.addActionListener(e);
    vis.addActionListener(e);
    renameButton.addActionListener(e);

    for (JButton b : layerSelectButtons) {
      b.addActionListener(e);
    }

  }

  @Override
  public void setActionCommands() throws IllegalArgumentException {
    loadButton.setActionCommand("loadButton");
    loadAllButton.setActionCommand("loadAllButton");
    saveAllButton.setActionCommand("saveAllButton");
    saveCurrentButton.setActionCommand("saveCurrentLayerButton");

    blurButton.setActionCommand("blurButton");
    sharpenButton.setActionCommand("sharpenButton");
    greyscaleButton.setActionCommand("greyscaleButton");
    sepiaButton.setActionCommand("sepiaButton");
    mosaicButton.setActionCommand("mosaicButton");
    downscaleButton.setActionCommand("downscaleButton");

    colorChooserButton1.setActionCommand("colorChooser1");
    colorChooserButton2.setActionCommand("colorChooser2");
    buildCheckerButton.setActionCommand("buildCheckerPattern");

    x.setActionCommand("remove");
    vis.setActionCommand("toggleVis");
    renameButton.setActionCommand("rename");

    for (JButton b : layerSelectButtons) {
      b.setActionCommand("select " + b.getToolTipText());
    }
  }

  @Override
  public void setDefaultCloseOperation() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public Color getCheckerColor1() {
    return color1;
  }

  @Override
  public Color getCheckerColor2() {
    return color2;
  }

  @Override
  public double getDownscaleX() throws IllegalStateException {
    try {
      return Double.parseDouble(this.downscaleX.getText());
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Must enter a double in the downscaleX field");
    }
  }

  @Override
  public double getDownscaleY() throws IllegalStateException {
    try {
      return Double.parseDouble(this.downscaleY.getText());
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Must enter a double in the downscaleY field");
    }
  }

  @Override
  public String getNewLayerName() {
    return layerName.getText();
  }

  @Override
  public int getCheckerSize() throws IllegalStateException {
    try {
      return Integer.parseInt(checkerSize.getText());
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Checker size was not a number.");
    }
  }

  @Override
  public int getCheckerRows() throws IllegalStateException {
    try {
      return Integer.parseInt(checkerRows.getText());
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Checker rows was not a number.");
    }
  }

  @Override
  public int getCheckerColumns() throws IllegalStateException {
    try {
      return Integer.parseInt(checkerColumns.getText());
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Checker columns was not a number.");
    }
  }

  @Override
  public File getFileImage() throws IllegalStateException {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, PNG, PPM, & GIF Images", "jpg", "png", "ppm", "gif");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(GUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      return fchooser.getSelectedFile();
    }
    throw new IllegalStateException("Can't get file.");
  }

  @Override
  public File getFolderImage() throws IllegalStateException {
    final JFileChooser fchooser = new JFileChooser(".");
    fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int retvalue = fchooser.showOpenDialog(GUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      return fchooser.getSelectedFile();
    }
    throw new IllegalStateException("Can't get file.");
  }

  @Override
  public File getSaveFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(GUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      return fchooser.getSelectedFile();
    }
    throw new IllegalStateException("Cannot get that path.");
  }

  @Override
  public File getSaveFolder() {
    final JFileChooser fchooser = new JFileChooser(".");
    fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int retvalue = fchooser.showSaveDialog(GUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      return fchooser.getSelectedFile();
    }
    throw new IllegalStateException("Cannot get that path.");
  }

  @Override
  public int getSeeds() throws IllegalStateException {
    try {
      return Integer.parseInt(mosaicSeeds.getText());
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Seeds was not a number.");
    }
  }

  @Override
  public void setVisibility(boolean visibility) {
    this.setVisible(visibility);
  }

  @Override
  public void renderMessage(String message) {
    this.messageDisplayer.setText(message);
  }

  @Override
  public void rerender() {
    this.setupImage();
    this.setupLayers();
    this.mainPanel.invalidate();
    this.mainPanel.validate();
    this.mainPanel.repaint();
  }
}
