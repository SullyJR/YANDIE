# Andie Overview

Andie is a non destructive (open source) image editor, which allows you to add images from your PC and lets you style them as you please. andie is developed using Java programming and libraries which includes JUnit testing, icons and text files. The UI is based off a standard image editor. ChatGPT was used within the Java programming for translations in languageList.txt and to write some JavaDocs.

# Features

* File - Open, save and exports images. Can also exit program.
* Edit - Can undo and redo changes to the image  
* Image - Resize, rotate and flip the image
* Select - Crop, colour and blur a certain area
* View - Zoom in and out on the image
* Filter - Adds various types of filters and blurs to the image
* Colour - Changes the colour of the image
* Macro - Allows user to record, save and load macros on to image
* Settings - Changes the language of the interface
* UI - Sleek and efficient user interface

## File Menu
The file menu has various functionality for opening, saving, exporting and closing images. It also has a simple exit button to close the program.

![image info](ReadmeImages/fileMenu.png)

## Edit Menu
The edit menu is used to undo and redo actions to the current image.

![image info](ReadmeImages/edit.png)

## Image Menu
The image menu has the ability to resize the image, rotate the image, and flip the image both horizontally and vertically.

![image info](ReadmeImages/image.png)

## View Menu
The view menu is simply used to zoom in and out of the image.

![image info](ReadmeImages/view.png)

## Filter Menu
The filter menu has a wide variety of functionality as this is where all our image processing filters are kept. In this menu you have 7 different filters that can be applied. These are:
- Mean filter - Applies a strong blur
- Soft blur - applies a simple blur to the image
- Sharpen filter - sharpens the image
- Gaussian blur - applies a blur using gaussian equation
- Box blur - applies a box blur to the image
- Median filter - Another blur commonly used to reduce noise in an image
- Emboss filter - Edge detection filter that applies an emboss effect to the image which makes it look like it is popping out of the screen

4 of these filters (Mean, Gaussian, Box, and Median) have the ability to choose the radius of the filter which alters the strength to which the filter is applied.

![image info](ReadmeImages/filter.png)
![image info](ReadmeImages/radiusFilter.png)


## Colour Menu
The colour menu is used to adjust the brightness and contrast of the image, this is done with a combo slider which allows you to adjust both the brightness and contrast at the same time. The colour menu also has the ability to change the colour of the image to greyscale

![image info](./ReadmeImages/colour.png)

![image info](ReadmeImages/colourAdjust.png)

## Settings Menu
The settings menu allows the user to change the programs language from a dropdown menu. The user can select from either English, French, or Malay.

![image info](ReadmeImages/settings.png)

## UI/Toolbar
The UI is based off of a standard image editor so that it is simple and easy to use. There is also an included toolbar that has quick access to some of the features noted above for efficient image processing

![image info](ReadmeImages/leftToolbar.png)
![image info](ReadmeImages/rightToolbar.png)


# Folder Structure

The workspace contains two folders, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

The Source Code File (src) contains two folders, where:

- `cosc202\andie` : the folder where the Java files are located, including the icons files and language text files
- `test` : the folder where the unit testing is located 

The workspace also contains two files, where:

- `.gitignore` : the file that tells git which files to ignore
- `README.md` : the markdown text file which describes the GitHub project

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.
> If you want to access the folder structure, open `https://altitude.otago.ac.nz/cosc202-yandie/andie`

# Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

# Current Project Team Members

## Current Project Development Team

* sulca807 - Callum Sullivan <sulca807@student.otago.ac.nz> (he/him)
* bugsa135 - Sam Bugden <bugsa135@student.otago.ac.nz> (he/him)
* johan372 - Andrew John Lieng <johan372@student.otago.ac.nz> (he/him)
* macse341 - Sen Macmaster <macse341@student.otago.ac.nz> (he/him)

## Emotional Support Team

* lamal240 - Alex Lambert-Janes <lamal240@student.otago.ac.nz> (he/him)

