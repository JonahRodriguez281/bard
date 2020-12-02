## Summary

The purpose of this app is to keep the user productive through the use of musical incentive. The app creates a playlist (connected to Spotify) with a length based on a timer set by the user to maximize productivity using the Pomodoro Method.
The Pomodoro method breaks your workday into 25 minute long chunks, with 5 minute breaks in between. After about 4 breaks, the fifth one is longer, which is generally around 15 minutes.
The app will pull songs form the user's saved music library to then construct a playlist that will play while the user performs their desired tasks from a task list constructed in the application. After the timer expires, the music stops, and then alerts the user that they should take a 5 mintute break.
After the 5 minute break is up, the user will recieve a notification that the break is over, and they can start another 25 minute long work session. 

The user can choose to save the playlist that was created directly into their Spotify account, so they can use it at a later date. Otherwise, after they've decided their timer, the application will generate a new playlist for the user. The User can save Task Types, which can then be used to Generate Tasks that will be completed alongside the Playlist.

I've created this app as a person that has never been able to focus without music playing. From a very young age, I had difficulty focusing on any one task for very long. Once I began to play music as a musician, I found it profoundly helpful to have music playing while I was trying to focus. After realizing that, I've always done any studying, testing, and overall intensive thinking while accompanied by music. This app is dedicated to the idea that listening to music and keeping focus go hand in hand. I've tailored it to use the Pomodoro method as well, considering the massive amount of help it has been to my beloved Wife, who has also struggled with "Attention Retention" in her youth.

## Intended users

* Students cramming for tests, finals etc.
    > As a busy student who loses track of time and gets stuck in my studies without taking breaks, I need an app to remind me to reset and take scheduled breaks to make sure I don't overwork myself, and keep my mind fresh.

* People who need musical accompaniment to stay focused

    > As someone who often loses track of time and loses focus when working, I need an app that simultaneously reminds me to take breaks to reset my brain , so that I may keep on schedule and give myself breaks when needed when working or exercizing.

## Functionality

* Take scheduled breaks using the Pomodoro Method's 25 minute interval system to maximize productivity.
* Connect to Spotify and sync your music library to accompany your work/study sessions.
* Generate a playlist from your library either at random using your saved library, or from your personal playlists, that has the exact length of 25 minutes, notifying you of your break with a pause in the music.
* Save the playlist that was generated to be able to use in the future.
* Note that the application cannot function without access to the internet.

## Current State

* 
*
*
*

## [Wireframe](wireframe.md)

## [Entity Relationship Diagram](bard-erd.md)

## [Data Definition Language](ddl.md)

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
	
## [Notice](notice.md)