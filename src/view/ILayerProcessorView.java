package view;

/**
 * Spec for an ImageProcessorView, ensures that there is a way to render a message, and for
 * important program information to be seen.
 */
public interface ILayerProcessorView {

  /**
   * Renders the message to the view, or otherwise makes the message visible to the user.
   *
   * @param message the message that should be rendered/shown
   */
  void renderMessage(String message);

  /**
   * Renders information about the layers that are currently loaded. Included layer order info and
   * layer visibility information.
   */
  void renderLayerInfo();
}
