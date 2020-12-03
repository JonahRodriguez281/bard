## Build Instructions

1. On GitHub, in the [Bard GitHub repository](https://github.com/JonahRodriguez281/bard), in the top right corner, click on the green Code pull-down menu and, with SSH selected, click on the clipboard icon on the right to copy that link to your machine's clipboard.

2. In IntelliJ/Android Studio, create new project and select "Import from Version Control".

3. Choose a destination, and select "Clone" to clone the repository locally on your machine.

4. Once it has been successfully loaded and built upon opening, run the program to install either on your hardware device or emulator of choice.

## Basic User Instructions

1. Upon opening the application, you will be presented with a login button, which will call upon the Spotify application installed on your device to authenticate the User as well as ask for the required permissions for Bard functionality. Please login, and accept the permissions when prompted by Spotify.
2. After logging in, you will be taken to the Session Screen, the main Screen. Now either by using the Navigation window in the top left and navigating to Task, or by simply pressing the Plus button in the bottom right of the Session Screen, you will be taken to the Task Screen, which is where the pre-built S Task Types will be. There is a Task A, Task B, and Task C each with a description and a duration in minutes. 
3. From here, each listing will have three buttons. A pencil (Edit), a plus (Add to Session), and a trashcan (Delete). The Edit button will edit the Task Type, allowing you to change the Name, Description and Duration of the Task. All three fields are required to submit an edited or new Task Type.
4. The Add to Session button adds the selected Task Type to the Session as a Task. This is essentially a clone of the Task Type that is being used in the Session.
5. The Delete key is used for deleting a Task Type altogether from your saved Tasks. Note that this is a PERMANENT DECISION, and deleted Task Types cannot be recovered.
6. Finally, the big Plus button in the bottom right corner creates an entirely new Task Type to be used and customized. All three fields are required to create a new Task Type.
7. Navigate back to the Session Screen and you will see all Task Types that were added to the Session as new Task items listed in the Session. Underneath each item you will find a delete key that will delete the Task from the Session, but will not Delete the Task type that it was created from to use in the future.
8. Navigating to the Music screen does nothing at the moment, however if the project is open in IntelliJ or Android Studio, navigating to the Music Screen will Log 20 Tracks pulled from your Spotify Account in Logcat.