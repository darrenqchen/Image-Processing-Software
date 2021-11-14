### Completion

The program is entirely complete, as far as we know. That being said, the instructions are extremely
hard to follow, so it might not be.

### Extra Credit

- We've implemented the mosaic transformation (read in USEME).
- We've implemented the downscale transformation (read in USEME).

# Instructions

You can run the file normally, and you'll be presented with the GUI view, hopefully this is
intuitive, but I'll provide a walk through later.

If you'd like to run a script, simply pass `-script <filePath>` as arguments. You will get a text
view of what the script contained, and the commands that were executed during the run of the script.

# DESIGN CHANGES (Summary):

## pt1 - pt2 (you can skip this part for this homework)

We changed the main model of our program from Image to Layer to support multiple layers. We also
added the MultiLayerImageProcessor class to wrap the functionality that comes with Layer and
ITransformation. This is mainly so that our program can be more coherent and user\-friendly. It's
now much easier to see what can be done with our program by reading javadoc.

The other major design change was moving our image export and image import functionality from the
model to the controller. We made this change because IO has nothing to do with the internal
representation of our images that's provided by the model.

## pt2 - pt3

We didn't heavily modify any existing code. We added a new transformation in our model. This was as
simple as adding a few lines to parse out the command and it's parameter. Then calling a new
function object that handles the heavy lifting.

Instead of heavy modification, we added a new controller and new view that work together to support
the GUI. These are `ControllerLIMEGUI` and `GUIView` respectively.

# Images (res/ folder) <br>

### Homework 1:

The Blur and Sharpen have been applied 25 times while the Greyscale and Sepia has only been applied
once in the res folder.

### Citations:

Meme: <br>
Kale, Sirin. “Life beyond the Meme: What Happens after You Go Viral.” BBC Three, BBC, 7 Mar.
2019, www.bbc.co.uk/bbcthree/article/e6511d6a-ea8c-4e27-aac3-728205903635.

Naruto: <br>
“Naruto Uzumaki.” Narutopedia, naruto.fandom.com/wiki/Naruto_Uzumaki.

We have permission to use these (and any images) under fair use (educational purpose). No one is
going to try to sell this or anything lol.

Sunset: <br>
Ben Lubas owns the photo and gives permission to use the photo.

Dog: <br>
Darren own the photo and gives permission to use the photo.

No Images photo: <br>
I (Ben) drew that one. I know you couldn't tell, it's so professional.

Eye photo for visibility: <br>
I (Ben) also drew that one. Again, I'm sure you couldn't tell.