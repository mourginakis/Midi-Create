# Midi Create
### 2012 Botball Showcase item
This was a showcase item for a Botball robotics tournament I was a part of in middle school. It transcodes .midi files into c programs that the robots in our tournament could undersand and play. No one had ever done anything like this before, and the 14 year old me was very proud. This was my first introduction into using more advanced programming features such as libraries. It's written in Java.

### Here's my showcase:
**George Gregos-Mourginakis**

**Tri-Desert Robotics**


Midi Create is a program that converts .midi files into a music .c file the Create can play. You can use it to have the create play your favorite songs, just by selecting a .midi file and choosing which instrument track you would like to convert. Amaze and entertain your robotics audience with an upbeat song while you’re winning!

Java is an excellent programming language that gives a cross-platform runtime environment for any of its applications. This means that it works on Macintosh, Windows, and many Linux distributions. Not to mention, it offers a wide range of integrated code for use with .midi files. This makes it a great choice for making the Midi Create program.

The program first starts out by asking the user for a .midi file. After the path is specified, it gives the user a list of instruments to chose from. Depending upon which instrument track is selected, the program writes a .c file containing which notes and how long they are played. These .c files can be compiled and run by any create with a CBC.

I created Midi Create to give teams an easy way of converting their favorite .midi files to be played on the Create. This program will entertain the audience with music, and help make your robot the center of attention!

This program may sound like it will make your robot more popular, and don't be mistaken, it will; however, there are some downsides:

First off, the current song writing from the CBC to the Create only allows for 16 note songs. Because of this, I had to truncate the user's song into mini, Create compatible songs of 16 notes, which is why you get the delay every so often (you'll see what I mean if you test it out).

Second, many midi files today are comprised of chords, which are multiple notes playing at once. The create can only play one note at a time, so whichever MIDI file you are transcoding needs to contain a melody. For example, the Tetris theme song, or some other type of early-ish music. Sorry, dubstep fans, but this is NOT GUARANTEED TO WORK WITH EVERY MIDI SONG.

Keep in mind that you might not select the right sequence the first time, and the Midi Create program does require a bit of guess-and-check. Usually, there are many different instruments playing within a midi file and you need to select which melody to use. Midi visualizers such as Synthesia come in handy when doing this.

You're probably wondering where you can download this program to test out. I've provided a link on mediafire (for the time being) of the actual project and the source code here, or as well as file attachment on this entry. PLEASE READ THE REST OF THIS POST, OR YOU WON'T KNOW HOW TO USE THE PROGRAM.
a# Midi Create
### 2012 Botball Showcase item
This was a showcase item for a Botball robotics tournament I was a part of in middle school. It transcodes .midi files into c programs that the robots in our tournament could undersand and play. No one had ever done anything like this before, and the 14 year old me was very proud. This was my first introduction into using more advanced programming features such as libraries. It's written in Java.

### Here's my showcase:
**George Gregos-Mourginakis**
**Tri-Desert Robotics**

Midi Create is a program that converts .midi files into a music .c file the Create can play. You can use it to have the create play your favorite songs, just by selecting a .midi file and choosing which instrument track you would like to convert. Amaze and entertain your robotics audience with an upbeat song while you’re winning!

Java is an excellent programming language that gives a cross-platform runtime environment for any of its applications. This means that it works on Macintosh, Windows, and many Linux distributions. Not to mention, it offers a wide range of integrated code for use with .midi files. This makes it a great choice for making the Midi Create program.

The program first starts out by asking the user for a .midi file. After the path is specified, it gives the user a list of instruments to chose from. Depending upon which instrument track is selected, the program writes a .c file containing which notes and how long they are played. These .c files can be compiled and run by any create with a CBC.

I created Midi Create to give teams an easy way of converting their favorite .midi files to be played on the Create. This program will entertain the audience with music, and help make your robot the center of attention!

This program may sound like it will make your robot more popular, and don't be mistaken, it will; however, there are some downsides:

First off, the current song writing from the CBC to the Create only allows for 16 note songs. Because of this, I had to truncate the user's song into mini, Create compatible songs of 16 notes, which is why you get the delay every so often (you'll see what I mean if you test it out).

Second, many midi files today are comprised of chords, which are multiple notes playing at once. The create can only play one note at a time, so whichever MIDI file you are transcoding needs to contain a melody. For example, the Tetris theme song, or some other type of early-ish music. Sorry, dubstep fans, but this is NOT GUARANTEED TO WORK WITH EVERY MIDI SONG.

Keep in mind that you might not select the right sequence the first time, and the Midi Create program does require a bit of guess-and-check. Usually, there are many different instruments playing within a midi file and you need to select which melody to use. Midi visualizers such as Synthesia come in handy when doing this.

You're probably wondering where you can download this program to test out. I've provided a link on mediafire (for the time being) of the actual project and the source code here, or as well as file attachment on this entry. PLEASE READ THE REST OF THIS POST, OR YOU WON'T KNOW HOW TO USE THE PROGRAM.

Because this software is still indev, it will be a bit confusing to use. Here's a quick tutorial on how to use it:

Because the program doesn't have a GUI, you have to launch it through the command line as a jar:

In Windows, open up command prompt and navigate to the directory where you have midiCreate.jar and your desired MIDI file with the "cd" command.

In Macintosh/Linux, launch terminal and navigate to the directory where you have stored midiCreate.jar and your desired MIDI file with the "cd" command.

****From now on, the instructions are going to be the same for each operating system****

In your command prompt type "java -jar [jar filename]". For example, "java -jar midiCreate.jar". This will start the program.

Keep in mind that YOU NEED TO HAVE THE MIDI FILE IN THE SAME DIRECTORY AS THE JAR FILE FOR THE PROGRAM TO WORK. I am planning on fixing this bug in a later version, but you're going to have to deal with it now. Sorry!

Now the program will ask you for the path of the midi file. All you need to do is give it the name of the midi, ending with ".mid" YOU DO NOT GIVE THE PATH OF THE MIDI.

From here, the program will ask you which song array to write the song too. For default, select "0" for the first array, and "1" for the second.

From now, the program is guess and check. You will need to find which sequence is the melody that you want to use, and select the channel that seems the most prominent afterwards.

Good luck, and happy transcoding.
Because this software is still indev, it will be a bit confusing to use. Here's a quick tutorial on how to use it:

Because the program doesn't have a GUI, you have to launch it through the command line as a jar:

In Windows, open up command prompt and navigate to the directory where you have midiCreate.jar and your desired MIDI file with the "cd" command.

In Macintosh/Linux, launch terminal and navigate to the directory where you have stored midiCreate.jar and your desired MIDI file with the "cd" command.

****From now on, the instructions are going to be the same for each operating system****

In your command prompt type "java -jar [jar filename]". For example, "java -jar midiCreate.jar". This will start the program.

Keep in mind that YOU NEED TO HAVE THE MIDI FILE IN THE SAME DIRECTORY AS THE JAR FILE FOR THE PROGRAM TO WORK. I am planning on fixing this bug in a later version, but you're going to have to deal with it now. Sorry!

Now the program will ask you for the path of the midi file. All you need to do is give it the name of the midi, ending with ".mid" YOU DO NOT GIVE THE PATH OF THE MIDI.

From here, the program will ask you which song array to write the song too. For default, select "0" for the first array, and "1" for the second.

From now, the program is guess and check. You will need to find which sequence is the melody that you want to use, and select the channel that seems the most prominent afterwards.

Good luck, and happy transcoding.