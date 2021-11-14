package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.layer.ILayer;
import model.layer.Layer;
import model.transformation.DownscaleTransformation;
import model.transformation.ITransformation;
import model.transformation.MosaicTransformation;
import model.transformation.GreyscaleColorChange;
import model.transformation.SepiaColorChange;
import model.transformation.BlurFilter;
import model.transformation.SharpenFilter;

/**
 * Represents the Image Processor that is able to deal with multiple Images at once. Contains
 * methods for layer operations, and image modifications.
 */
public class MultiLayerLIME implements IModelLIME {

  private final List<ILayer> layers;
  Map<String, ITransformation> transformations;

  /**
   * Creates a {@code MultiLayerImageProcessor} given a list of layers to the model.
   *
   * @param layers the list of layers already in the model.
   * @throws IllegalArgumentException if the list of layers is null.
   */
  public MultiLayerLIME(List<ILayer> layers) throws IllegalArgumentException {
    if (layers == null) {
      throw new IllegalArgumentException("Images list cannot be null");
    }
    this.layers = layers;
    this.transformations = new HashMap<>();
    transformations.put("blur", new BlurFilter());
    transformations.put("sharpen", new SharpenFilter());
    transformations.put("greyscale", new GreyscaleColorChange());
    transformations.put("sepia", new SepiaColorChange());
  }

  public MultiLayerLIME() throws IllegalArgumentException {
    this(new ArrayList<>());
  }

  /**
   * Finds a layer by name.
   *
   * @param layerName the layer name
   * @return the layer that has the name given
   * @throws IllegalArgumentException if given null or a layer name that doesn't exist.
   */
  protected ILayer getLayerByName(String layerName) throws IllegalArgumentException {
    if (layerName == null) {
      throw new IllegalArgumentException("layername cannot be null");
    }
    for (ILayer l : this.layers) {
      if (l.getName().equals(layerName)) {
        return l;
      }
    }
    throw new IllegalArgumentException("There is no layer named " + layerName);
  }

  @Override
  public void transformCurrentLayer(String command)
      throws IllegalArgumentException, IllegalStateException {
    if (command == null) {
      throw new IllegalArgumentException("transformation cannot be null.");
    } else if (layers.size() <= 0) {
      throw new IllegalStateException("There has to be at least one image.");
    }
    ITransformation transformation = null;
    String[] args = command.split(" ");
    if (args.length == 1) {
      transformation = this.transformations.getOrDefault(command, null);
    } else if (args.length > 1) {
      if (args[0].equals("mosaic")) {
        transformation = new MosaicTransformation(Integer.parseInt(args[1]));
      }
    }

    if (transformation == null) {
      throw new IllegalArgumentException("There is no transformation by that type");
    } else {
      // if you change current, it changes the reference
      ILayer current = this.getCurrentLayer();
      int cIndex = this.layers.indexOf(current);
      ILayer transformedLayer = current.applyTransformation(transformation);
      transformedLayer.changeName(current.getName());
      this.layers.set(cIndex, transformedLayer);
    }
  }

  @Override
  public void downScale(double scaleX, double scaleY) throws IllegalArgumentException {
    if (scaleX > 1 || scaleY > 1 || scaleX <= 0 || scaleY <= 0) {
      throw new IllegalArgumentException("scalars must be (0, 1]");
    }
    List<ILayer> scaledLayers = new ArrayList<>();
    ITransformation transformation = new DownscaleTransformation(scaleX, scaleY);
    for (ILayer l : this.layers) {
      ILayer scaledLayer = transformation.apply(l.getPixels());
      scaledLayer.changeName(l.getName());
      scaledLayers.add(scaledLayer);
    }
    this.layers.clear();
    this.layers.addAll(scaledLayers);
  }

  @Override
  public void addLayer(ILayer newLayer) throws IllegalArgumentException {
    if (newLayer == null) {
      throw new IllegalArgumentException("newLayer cannot be null");
    }
    if (this.getLayerSize() != 0) {
      ILayer firstlayer = this.layers.get(0);
      if (newLayer.getPixels().length != firstlayer.getPixels().length
          || newLayer.getPixels()[0].length != firstlayer.getPixels()[0].length) {
        throw new IllegalArgumentException("Cannot add a layer that is the wrong dimensions.");
      }
    }
    this.layers.add(newLayer);
  }

  @Override
  public void removeLayer(String layerName) throws IllegalArgumentException {
    if (layerName == null) {
      throw new IllegalArgumentException("layerName cannot be null");
    }

    // The error from getLayerByName will propagate, that's intended.
    this.layers.remove(this.getLayerByName(layerName));

  }

  @Override
  public ILayer getCurrentLayer() throws IllegalStateException {
    for (ILayer l : this.layers) {
      if (l.isVisible()) {
        return l;
      }
    }
    throw new IllegalStateException("There are no visible layers");
  }

  @Override
  public List<ILayer> getAllLayers() {
    List<ILayer> copy = new ArrayList<>();
    for (ILayer l : this.layers) {
      copy.add(new Layer(l));
    }
    return copy;
  }

  @Override
  public int getLayerSize() {
    return this.layers.size();
  }

  @Override
  public void selectLayer(String layerName) throws IllegalArgumentException {
    if (layerName == null) {
      throw new IllegalArgumentException("layer name cannot be null");
    }
    ILayer l = getLayerByName(layerName);
    this.layers.remove(l);
    this.layers.add(0, l);
    l.show();
  }

  @Override
  public void showLayer(String layerName) throws IllegalArgumentException {
    if (layerName == null) {
      throw new IllegalArgumentException("String cannot be null.");
    }
    this.getLayerByName(layerName).show();
  }

  @Override
  public void hideLayer(String layerName) throws IllegalArgumentException {
    if (layerName == null) {
      throw new IllegalArgumentException("String cannot be null.");
    }
    this.getLayerByName(layerName).hide();
  }


}
