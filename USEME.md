# Instructions

Program will Execute commands one line at a time.

In the interactive window, quit with "q".

- "apply <filterName>(one of: blur, sharpen, greyscale, sepia)": applies the filter to the selected
  layer
- "create <imageFilePath> <?name> ": creates a layer underneath existing layers. <name>
  defaults to <imageFilePath>. Created layers are visible. Created layers must be the same size as
  existing layers, if there are any.
- "createMulti <folderPath>": loads all the layers in the multi layer image folder. This folder must
  have been created by the saveAll command (see bellow). If there are already layers loaded, this
  will load all layers underneath existing layers. These layers keep their relative order, names,
  and visibility status.
- "hide <layername>": hide the layer.
- "remove <layername>": remove the layer.
- "save <fileName>": saves the selected layer as an image, include the file extension in the name.
  Officially supports: PNG, JPG, PPM. Unofficially supports: GIF, BMP, WBMP.
- "saveAll <folderName>": saves all the layers into a folder called <folderName> with an info.txt
  file containing layer info. Each layer gets saved as its own PNG file.
- "select <layerName>": sets the named layer as the topmost and makes it visible
- "show <layername>": show the layer.

NOTES:

- (GUI only) Must Fill out text fields for corresponding actions before hitting the buttons.
- Must include the file extensions when saving individual layers.
- Must provide the name of a file that does not yet exist when saving a multi-layered image. This is
  a safety feature, we don't overwrite existing folders.

## Example

```
create res/DontTouch/Sunset.png sunset
create res/DontTouch/Skater.png skater
select skater
remove sunset
hide skater
show skater
apply sepia
save res/oldTimeySkater.png
create res/DontTouch/Sunset.png sunset
hide sunset
saveAll res/twoLayers
createMulti res/twoLayers
```

## GUI Walk Through

The first thing you'll want to do is to load up an image. This is done with the Load New Layer
button. Click it, select an image file (either PPM, PNG, or JPG). If you'd instead like to load
saved layers, you click the Load A Folder of Layers button and choose the folder that this program
created with the Save All Layer Button (NOTE: it must have been exported by the program beforehand).

Now that you've loaded a layer in, you should see a button somewhere along the bottom right under
the layers tab. This is where you can hide the layer, select the layer, remove it, or rename it (
Note: Make sure all the layers are named differently for an ideal experience).

X remove, the eye hides, and the rename button will rename the selected layer to the name in the
adjacent text field.

On the left is the image transformations tab. Click a button to trigger a transformation on the
selected layer. Each button triggers a random transformation so have fun... no. The buttons are
named that way for a reason, not going to go over "how to blur" "how to sharpen", just read.

If you want to mosaic an image, make sure to specify the granularity of the mosaicification in the
text box below.

If you want to downscale an image, make sure to specify the x and y scalars in the fields below the
button. Scalars should be doubles in (0, 1]

Creating a checker image can be done by filling out the three text boxes and picking your colors
with the two buttons below. Then clicking the build checkerboard button.

If, at any point, you mess up, the message box at the bottom with tell you and you can promptly fix
your mistake.

Now you've made your ~~disgusting~~ incredible artwork, you want to save it forever. Select the
layer and save it with the Save current layer button. Enter the name, including the file type (PPM,
PNG, or JPG).

The Save All button can be used if you'd like to save all the layers in a way that the program can
load back up again. This will even save hidden layers. 