import java.util.Scanner;
import javax.sound.midi.*;
import java.util.*;
import java.io.*;
import java.math.*;


public class main {
	static Scanner scan = new Scanner(System.in);
	public static final int NOTE_ON = 0x90;
	public static final int NOTE_OFF = 0X80;
	public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
		
	
	
	public static void main(String[] args) throws InvalidMidiDataException, IOException, MidiUnavailableException {
		
		System.out.print("Please enter path of the .midi file\n");
		String midipath = scan.nextLine();
		System.out.print("What would you like the output program to be called?\n");
		String midioutputname = scan.nextLine();
		System.out.print("Which song write array would you like to use? (0-16)\n");
		String writearrayfirst = scan.nextLine();
		System.out.print("Which second song write array would you like to use (0-16, not " + writearrayfirst + ")\n");
		String writearraysecond = scan.nextLine();
		Sequence sequence = MidiSystem.getSequence(new File(midipath));
		Sequencer sequencer = MidiSystem.getSequencer();
		
			Synthesizer synth = null;
			try {
				synth = MidiSystem.getSynthesizer();
				synth.open();
			}
			catch (Exception e) {
				System.out.println(e);
			}
		
		Soundbank soundbank = synth.getDefaultSoundbank();
		sequencer.open();
		sequencer.setSequence(sequence);
		String midiDesc = soundbank.getDescription();
		
		//60000 / (BPM * PPQ)
		
		float bpm = sequencer.getTempoInBPM();
		
		int resoultion = sequence.getResolution();
		
		double ticksPerSecond = resoultion * (bpm / 60.0);
		
		System.out.print(midiDesc + ", " + "BPM: " + bpm + ", ticksPerSecond: " + ticksPerSecond + "\n");
		
		sequencer.start();
		
		int trackNumber = 0;
        for (Track track :  sequence.getTracks()) /*colon means "in"*/ { 
            trackNumber++;
            System.out.println("Track " + trackNumber + ": size = " + track.size() + "\n");
            
        }
        
        
        System.out.print("Which track number would you like to use? The song is usually stored the largest one.\n");
        String tas = scan.nextLine();
        int tai = Integer.parseInt(tas);
        int songarrayintfirst = Integer.parseInt(writearrayfirst);
        int songarrayintsecond = Integer.parseInt(writearraysecond);
        if(songarrayintfirst==songarrayintsecond){System.out.print("SONG WRITE ARRAYS ARE THE SAME. PLEASE TERMINATE THE PROGRAM AND TRY AGAIN OR THE OUTPUT WILL BE CHOPPY\n");}
        int songarrayusage = 0;
        System.out.print(tai + "\n");
            
         System.out.print(sequence.getTracks());
         
         int trackNumbertwo = 0;
         for (Track track : sequence.getTracks())
         {
        	trackNumbertwo++;
        	if (trackNumbertwo == tai) {
            for (int i=0; i < track.size(); i++) { 
                MidiEvent event = track.get(i);
                System.out.print("@" + event.getTick() + " ");
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    System.out.print("Channel: " + sm.getChannel() + " ");
                    
                    
                    if (sm.getCommand() == NOTE_ON) {
                        int key = sm.getData1();
                        int velocity = sm.getData2();
                        System.out.println("Note on, " + "key=" + key + ", velocity=" + velocity);
                    }
                    
                    
                    else if (sm.getCommand() == NOTE_OFF) {
                        int key = sm.getData1();
                        int velocity = sm.getData2();
                        System.out.println("Note off, " + "key=" + key + ", velocity=" + velocity);
                    }
                    
                    
                    else {
                      //  System.out.println("Command:" + sm.getCommand());
                    	System.out.print("\n");
                    }
                } 
                
                else {
                  //  System.out.println("Other message: " + message.getClass());
                    System.out.print("\n");
                }
            }

            System.out.println();
        }
      }
	
      System.out.print("Which channel would you like to use?\n");
      
      String channeluse = scan.nextLine();
      int channeluseint = Integer.parseInt(channeluse);
      System.out.print("Writing to file...\n");
      
      FileWriter fstream = new FileWriter(midioutputname + ".c");
      BufferedWriter out = new BufferedWriter(fstream);
      out.write("// " + midipath + "\nvoid " + midioutputname + "();\n\n\n");
      out.write("int main()\n{\n\t" + midioutputname + "();\n}");
      out.write("\n\n\nvoid " + midioutputname + "()\n{\n");
      out.write("\tcreate_connect();\n");
      
      
      
      
      
      trackNumbertwo = 0;
      int notesperchannel = 0;
      for (Track track : sequence.getTracks())
      {
     	trackNumbertwo++;
     	if (trackNumbertwo == tai) {
         for (int i=0; i < track.size(); i++) { 
             MidiEvent event = track.get(i);
            // System.out.print("@" + event.getTick() + "\n");
             MidiMessage message = event.getMessage();
             if (message instanceof ShortMessage) {
                 ShortMessage sm = (ShortMessage) message;
                 if (sm.getChannel() == channeluseint)
                 {
                 	
                	 if ((sm.getCommand() == NOTE_OFF) || sm.getData2() == 0) {
                         //int key = sm.getData1();
                         //System.out.println("Note off, " + "key=" + key);
                		 notesperchannel++;
                     }
                	 
                	 else if (sm.getCommand() == NOTE_ON) {
                       //  int key = sm.getData1();
                        // System.out.println("Note on, " + "key=" + key);
                     }
                     
                     
                     else {
                         //System.out.println("Command:" + sm.getCommand());
                     }
                 	
                 }
                 else {}
                
             } 
             
             else {
                 //System.out.println("Other message: " + message.getClass());
             }
         }

         System.out.println();
     }
   } 
      
      //Song tracks will be divided into mini songs of 16 notes or less called songfrac s
      int songremaindermod = notesperchannel % 16; //Get remainder of notes in the last songfrac 
      double createTime;  //The amount of time (double)
      int createTimeint; //the amount of thime (int)
      double songwritex = notesperchannel / 16;
      int songfracs = (int)songwritex; //Number of songfracs - 1
      int songwritenumber = 0; //what note the song will write to
      double secondsPassed;
      double secondsPerTick = 1/ticksPerSecond;
      double ticksPassedOn = 0;
      double ticksPassedOff = 0;
      double ticksPassed = 0;
      trackNumbertwo = 0;
      int songwriteloopnumber = 0; //how many times 16 notes have been written
      for (Track track : sequence.getTracks())
      {
     	trackNumbertwo++;
     	if (trackNumbertwo == tai) {
         for (int i=0; i < track.size(); i++) { 
             MidiEvent event = track.get(i);
            // System.out.print("@" + event.getTick() + " ");
             MidiMessage message = event.getMessage();
             if (message instanceof ShortMessage) {
                 ShortMessage sm = (ShortMessage) message;
                 if (sm.getChannel() == channeluseint)
                 {
                	 
                	 if ((sm.getCommand() == NOTE_OFF) || sm.getData2() == 0) {
                		 
                        	 int key = sm.getData1();
                        	 int velocity = sm.getData2();
                        	 ticksPassedOff = event.getTick();
                        	 ticksPassed = ticksPassedOff - ticksPassedOn;
                        	 secondsPassed = ticksPassed * secondsPerTick; //secondsPassed = how long each note is.
                        	 createTime = secondsPassed * 64;
                        	 createTimeint = (int) Math.round(createTime);
                        	 if(createTimeint == 0){createTimeint=1;}
                        	 
                        	 if(songwritenumber == 0){
                        		 if(songwriteloopnumber < songfracs) //loops before last loop
                        		 {
                        			 if(songarrayusage == 0)
                        			 {
                        				 out.write("\tgc_song_array[" + songarrayintfirst +"][" + songwritenumber + "]=16;\n");
                            		     songwritenumber++;
                            		     out.write("\tgc_song_array[" + songarrayintfirst + "][" + songwritenumber + "]=" + key + ";\n");
                                		 songwritenumber++;
                                		 out.write("\tgc_song_array[" + songarrayintfirst + "][" + songwritenumber + "]=" + createTimeint + ";\n"); //duration
                                		 songwritenumber++;
                        			 }
                        			 else if(songarrayusage == 1)
                        			 {
                        				 out.write("\tgc_song_array[" + songarrayintsecond +"][" + songwritenumber + "]=16;\n");
                            		     songwritenumber++;
                            		     out.write("\tgc_song_array[" + songarrayintsecond + "][" + songwritenumber + "]=" + key + ";\n");
                                		 songwritenumber++;
                                		 out.write("\tgc_song_array[" + songarrayintsecond + "][" + songwritenumber + "]=" + createTimeint + ";\n"); //duration
                                		 songwritenumber++;
                        			 }
                        		 }
                        		 
                        		 else if(songwriteloopnumber == songwritex) //last loop
                        		 {
                        			 if(songarrayusage == 0)
                        			 {
                        				 out.write("\tgc_song_array[" + songarrayintfirst +"][" + songremaindermod + "]=" + notesperchannel + ";\n");
                            		     songwritenumber++;
                            		     out.write("\tgc_song_array[" + songarrayintfirst + "][" + songwritenumber + "]=" + key + ";\n");
                                		 songwritenumber++;
                                		 out.write("\tgc_song_array[" + songarrayintfirst + "][" + songwritenumber + "]=" + createTimeint + ";\n"); //duration
                                		 songwritenumber++;
                        			 }
                        			 else if(songarrayusage == 1)
                        			 {
                        				 out.write("\tgc_song_array[" + songarrayintsecond +"][" + songremaindermod + "]=" + notesperchannel + ";\n");
                            		     songwritenumber++;
                            		     out.write("\tgc_song_array[" + songarrayintsecond + "][" + songwritenumber + "]=" + key + ";\n");
                                		 songwritenumber++;
                                		 out.write("\tgc_song_array[" + songarrayintsecond + "][" + songwritenumber + "]=" + createTimeint + ";\n"); //duration
                                		 songwritenumber++;
                        			 }
                        		     
                        		 }
                        	 }
                        	 
                        	 else if(songwritenumber == 15)
                             {
                        		 if(songarrayusage == 0)
                        		 {
                        			 out.write("\tgc_song_array[" + songarrayintfirst + "][" + songwritenumber + "]=" + key + ";\n");
                            		 songwritenumber++;
                            		 out.write("\tgc_song_array[" + songarrayintfirst + "][" + songwritenumber + "]=" + createTimeint + ";\n"); //duration
                            		 songwritenumber++;
                            		 out.write("\tcreate_load_song(" + songarrayintfirst + ");\n");
                            		 out.write("\twhile(get_create_song_playing(.01)) {msleep(50);}\n");
                                     out.write("\tcreate_play_song(" + songarrayintfirst + ");\n");
                                     songwritenumber = 0;
                                     songarrayusage = 1;
                        		 }
                        		 else if(songarrayusage == 1)
                        		 {
                        			 out.write("\tgc_song_array[" + songarrayintsecond + "][" + songwritenumber + "]=" + key + ";\n");
                            		 songwritenumber++;
                            		 out.write("\tgc_song_array[" + songarrayintsecond + "][" + songwritenumber + "]=" + createTimeint + ";\n"); //duration
                            		 songwritenumber++;
                            		 out.write("\tcreate_load_song(" + songarrayintsecond + ");\n");
                            		 out.write("\twhile(get_create_song_playing(.01)) {msleep(50);}\n");
                                     out.write("\tcreate_play_song(" + songarrayintsecond + ");\n");
                                     songwritenumber = 0;
                                     songarrayusage = 0;
                        		 }
                             }
                        	 
                        	 else {
                        		 
                        		 if(songarrayusage == 0)
                        		 {
                        			 out.write("\tgc_song_array[" + songarrayintfirst + "][" + songwritenumber + "]=" + key + ";\n");
                            		 songwritenumber++;
                            		 out.write("\tgc_song_array[" + songarrayintfirst + "][" + songwritenumber + "]=" + createTimeint + ";\n"); //duration
                            		 songwritenumber++;
                        		 }
                        		 else if(songarrayusage == 1)
                        		 {
                        			 out.write("\tgc_song_array[" + songarrayintsecond + "][" + songwritenumber + "]=" + key + ";\n");
                            		 songwritenumber++;
                            		 out.write("\tgc_song_array[" + songarrayintsecond + "][" + songwritenumber + "]=" + createTimeint + ";\n"); //duration
                            		 songwritenumber++;
                        		 }
                        	 }
                             
                     }
                	 
                	 
                	 
                	 
                	 //load song has to come before get song playing
                	 
                	 /* Song data bytes 4, 6, 8, etc.: Note Duration (0 Ð 255)
					  *	The duration of a musical note, in increments of 1/64th of a second. 
                	  * */
                	 
                	 //multiply timeSeconds by 64 to get the time
                	 
                	 
                	 
                	 else if (sm.getCommand() == NOTE_ON) {
                 	
                 			ticksPassedOn = event.getTick();
                 		}
                 		
                         
                     
                     
                     else {
                         //System.out.println("Command:" + sm.getCommand());
                     }
                 	
                 	
                 	/*32 = .5
                 	 *16 = .25
                 	 *
                 	 *
                 	 *
                 	 * */
                 	
                 	
                 }
                 else {}
                
             } 
             
             else {
                 //System.out.println("Other message: " + message.getClass());
             }
         }

         System.out.println();
     }
   }  
     if(songarrayusage == 0){out.write("\tcreate_load_song("+ songarrayintsecond +");\n\twhile(get_create_song_playing(.01)) {msleep(50);}\n\tcreate_play_song(" + songarrayintsecond + ");\n");}
     else if(songarrayusage == 1){out.write("\tcreate_load_song("+ songarrayintsecond +");\n\twhile(get_create_song_playing(.01)) {msleep(50);}\n\tcreate_play_song(" + songarrayintsecond + ");\n");}
     out.write("}");
     out.close();
     System.out.print("File Written.\n");
     System.exit(0);
		
		//********************************************************************************
		
		
	}
}
