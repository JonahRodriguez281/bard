## Summary

The purpose of this app is to keep the user productive through the use of a musical incentive. The app creates a playlist (connected to Spotify) with a length based on a timer set by the user to maximize productivity using the Pomodoro Method.
The Pomodoro method breaks your workday into 25 minute long chunks, with 5-minute breaks in between. After about 4 breaks, the fifth one is longer, which is generally around 15 minutes.
The app will pull songs from the user's saved music library to then construct a playlist that will play while the user performs their desired tasks from a task list constructed in the application. After the timer expires, the music stops and then alerts the user that they should take a 5-minute break.
After the 5 minute break is up, the user will receive a notification that the break is over, and they can start another 25-minute long work session. 

The user can choose to save the playlist that was created directly into their Spotify account, so they can use it at a later date. Otherwise, after they've decided their timer, the application will generate a new playlist for the user. The User can save Task Types, which can then be used to Generate Tasks that will be completed alongside the Playlist.

I've created this app as a person that has never been able to focus without music playing. From a very young age, I had difficulty focusing on any one task for very long. Once I began to play music as a musician, I found it profoundly helpful to have music playing while I was trying to focus. After realizing that, I've always done any studying, testing, and overall intensive thinking while accompanied by music. This app is dedicated to the idea that listening to music and keeping focus go hand in hand. I've tailored it to use the Pomodoro method as well, considering the massive amount of help it has been to my beloved Wife, who has also struggled with "Attention Retention" in her youth.

## Intended users

* Students cramming for tests, finals, etc.
    > As a busy student who loses track of time and gets stuck in my studies without taking breaks, I need an app to remind me to reset and take scheduled breaks to make sure I don't overwork myself and keep my mind fresh.

* People who need musical accompaniment to stay focused

    > As someone who often loses track of time and loses focus when working, I need an app that simultaneously reminds me to take breaks to reset my brain, so that I may keep on schedule and give myself breaks when needed when working or exercising.

## Functionality

* Take scheduled breaks using the Pomodoro Method's 25-minute interval system to maximize productivity.
* Connect to Spotify and sync your music library to accompany your work/study sessions.
* Generate a playlist from your library either at random using your saved library or from your personal playlists, with the exact length of 25 minutes, notifying you of your break with a pause in the music.
* Save the playlist that was generated to be able to use in the future.
* Note that the application cannot function without access to the internet.
* Bard also requires a Spotify premium account, as well as the Spotify Application, downloaded on the machine running the Bard application, as it is called upon when authenticating the User and permissions.

## Current State

The current state of the app is certainly "In Progress", and requires further development to be used as intended. All of the pieces presented are compilable, buildable, and runnable. The app does contain some functionality in terms of round trip usage of the database for everything locally on the machine, however much of the unimplemented functionality revolves around the Spotify API as I was not able to implement a way to display the Tracks that it successfully retrieves from the website. Because of this, I was not able to implement a timer for the session when doing tasks. As of now the implementation for the application consists of a few preloaded Task Types in the Task Screen, which can be edited, deleted, or added to the Session by creating new Tasks based on those Task Types. In the Session Screen, it displays the created Tasks from the Task Screen and is where the Timer and Music Player would have been implemented to construct the basis for the Pomodoro Application.

### Incomplete Elements

The Music Screen is retrieving tracks from the Spotify API, however, all it is doing is Logging them in Logcat and not doing anything with them. There is a songList and a SongAdapter that is in charge of converting the Track Objects received into Song Objects to be displayed, however, it is currently not working, leaving the Music Screen Blank.
The Session Screen has no functionality to implement a timer based on the duration of the created Tasks, so as of now, it is just displaying the created Tasks from the Task Screen.
As the app is only intended for one User, there is no functionality to save a User to the database, nor is there a way to persist the Login for more than about 15 minutes, as the default Spotify Token refresh time can only persist for that long.

### Aesthetic Elements not Implemented

The buttons in the Task Screen for Editing Tasks, Adding Tasks to the Session, and Deleting Tasks should have at least a label for accessibility or even a pop-up box notifying the User of the functions of each button.

### Stretch-Goals

The Spotify API is capable of many different functions, including the ability to modify, save, and delete items from Playlists the User has created in Spotify and their saved Libraries, follow and unfollow other users, and so on. As I continue to work on the app over the course of my Coding career, I intend to implement MUCH more functionality regarding interaction with the User's Spotify Account.

## Technical Requirements & Dependencies

* Ran & Tested on LG Stylo 4 Android Phone - Android API 26
* Minimum API needed to run is Android API 21

### 3rd-Party Libraries Used

* Spotify
* Gson
* Retrofit
* ReactiveX

### Permissions Needed

* Android Permissions
    * Internet Access
* Spotify Permissions
    * View Account Data (email, name & username, profile picture, followers, public playlists)
    * View Spotify Activity (Saved to Library, Playlists you've made, Playlists you follow)
    * Take actions in Spotify on your behalf (Stream & Control Spotify in the Bard app & other devices, create edit and follow private playlists & playlists)

## Persistent data

* Saved & settable timers the user can determine for themselves.
* Spotify account information & preferences.
* Use of internal clock to determine the amount of time passed.
* Saved playlists to re-use at a later date.
    
## Device/external services

* [Spotify](https://developer.spotify.com/documentation/web-api/reference/) 

    * Music database
    * Account information & preferences
    * Synchronization of user's saved music and playlists
    * Music streaming data 
    * Authorization privileges
    
## [Wireframe](wireframe.md)

## [Entity Relationship Diagram](bard-erd.md)

## [Data Definition Language](ddl.md)

## [Bard Javadocs](api/index.html)

## [Notice](notice.md)

## [Build & Basic Instructions](README.md)