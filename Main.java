import java.util.*;
import java.io.*;

public class Main {
	
	private static DoubleList<Song> songPlaylist;

	public static void main(String[] args) {
		
		songPlaylist = new DoubleList<Song>();
		String command = "";
		Scanner input = new Scanner(System.in);

		System.out.println("*** Playlist Manager! ***");
		System.out.println("Commands:");
		System.out.println("add");
		System.out.println("remove");
		System.out.println("count");
		System.out.println("play");
		System.out.println("shuffle");
		System.out.println("reverse");
		System.out.println("save");
		System.out.println("load");
		System.out.println("quit");
		System.out.println();
		System.out.print(": ");
		
		while(!(command.equals("quit"))) {
			command = input.next();
			command += input.nextLine();

			if (command.equals("add")) {
				System.out.println();
				add(input);
				System.out.println();
			}

			if (command.equals("remove")) {
				System.out.println();
				remove(input);
				System.out.println();
			}


			if (command.equals("count")) {
				System.out.println();
				songPlaylist.count();
				System.out.println();
			}

			if (command.equals("play")) {
				System.out.println();
				play();
				System.out.println();
			}

			if (command.equals("shuffle")) {
				System.out.println();
				shuffle();	
				System.out.println();
			}

			if (command.equals("reverse")) {
				System.out.println();
				reverse();
				System.out.println();
			}

		 	if (command.equals("save")) {
				System.out.println();
				save();
				System.out.println();
			}

			if (command.equals("load")) {
				System.out.println();
				load();
				System.out.println();
			}

			System.out.print(": ");
		}
	}
	
	//adds a song to the playlist
	public static void add(Scanner input) {
		String songArtist;
		String songTitle;
		Song newSong;
		
		System.out.print("Enter the song artist: ");
		songArtist = input.next();
		songArtist += input.nextLine();
		System.out.print("Enter the song title: ");
		songTitle = input.next();
		songTitle += input.nextLine();

		newSong = new Song(songArtist, songTitle);
		songPlaylist.addEnd(newSong);
	}
	
	//Removes a song from the playlist by creating a 
	//new playlist without the song to be removed.
	//The overall playlist is then set to the new playlist
	//with the song removed.
	public static void remove(Scanner input) {
		String songArtist;
		String songTitle;
		int playlistLength = songPlaylist.count();
		DoubleList<Song> removedPlaylist;
		Song songCheck;
		int songToRemoveIndex = 0;

		System.out.println("Enter the artist of the song you want to remove: ");
		songArtist = input.next();
		songArtist += input.nextLine();
		System.out.println("Enter the title of the song you want to remove: ");
		songTitle = input.next();
		songTitle += input.nextLine();

		removedPlaylist = new DoubleList<Song>();

		for (int i = 0; i < playlistLength; i++) {
			songCheck = songPlaylist.getValueAtIndex(i);
			
			if (songCheck.getArtist().equals(songArtist)) {
				if (songCheck.getTitle().equals(songTitle)) {
					songToRemoveIndex = i;
				}

				else {	
				}
			}
		}

		if (songToRemoveIndex == 0) {
			System.out.println("That song is not in the playlist.");
		}

		else {

			for (int i = 0; i < playlistLength; i++) {

				if (i == songToRemoveIndex) {
				}

				else {
					songCheck = songPlaylist.getValueAtIndex(i);
					removedPlaylist.addEnd(songCheck);
				}

			songPlaylist = removedPlaylist;
			}
		}
	}	

	//Returns the number of songs in the playlist
	public static int count() {
		int playlistLength = songPlaylist.count();

		return playlistLength;
	}

	//Prints out the entire song playlist with
	//the artist and title of each song.
	public static void play() {
		int playlistLength = songPlaylist.count();
		Song songToPlay;

		for (int i = 0; i < playlistLength; i++) {
		       songToPlay = songPlaylist.getValueAtIndex(i);
		       
		       System.out.println(songToPlay.getArtist() + " - " + songToPlay.getTitle());
		}
	}		
	
	//Shuffles all songs in the playlist by generating
	//a random number that is set to the index. The song 
	//at that index is then passed to a holder variable,
	//removed from the playlist, then added to the front.
	public static void shuffle() {
		int playlistLength = songPlaylist.count();
		int shuffleIndex;
		Song songToShuffle;

		Random randGen = new Random();
		for (int i = 0; i < playlistLength / 2; i++) { 
			
			shuffleIndex = randGen.nextInt(playlistLength - i) + i;

			songToShuffle = songPlaylist.getValueAtIndex(shuffleIndex);
			songPlaylist.remove(songToShuffle);
			songPlaylist.addStart(songToShuffle);
		}	
	}

	//Reverses the order of all the songs in the playlist.
	public static void reverse() {
		int playlistLength = songPlaylist.count();
		int first = 0;
		int last = playlistLength;
		Song songToReverse;

		for (int i = 0; i < playlistLength - 1; i++) {
			songToReverse = songPlaylist.getValueAtIndex(playlistLength - (2 + i));
			songPlaylist.remove(songToReverse);
			songPlaylist.addEnd(songToReverse);
		}
	}

	public static void save() {
		String savedPlaylist;
		Song songToWrite;
		int playlistLength = songPlaylist.count();
		Scanner input = new Scanner(System.in);

		System.out.println("Enter file: ");
		savedPlaylist = input.nextLine();

		try {
			FileWriter writer = new FileWriter(savedPlaylist, true);
		       	
			for (int i = 0; i < playlistLength; i++) {
				songToWrite = songPlaylist.getValueAtIndex(i);
				writer.write(songToWrite.getArtist() + " - " + songToWrite.getTitle());
				writer.write("\r\n");
			}

			writer.close();
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	//Loads a playlist file and sets the global 
	//playlist equal to the file contents.
	public static void load() {
		String loadedPlaylist;
		String trackerLine;
		String[] songArtistAndTitle;
		Song songToLoad;
		Scanner input = new Scanner(System.in);
		DoubleList<Song> loadedPlaylistSongs = new DoubleList<Song>();

		System.out.println("Enter file: ");
		loadedPlaylist = input.next();
		loadedPlaylist += input.nextLine();

		try {
			FileReader reader = new FileReader(loadedPlaylist);
			BufferedReader bufferedReader = new BufferedReader(reader);
		
			while ((trackerLine = bufferedReader.readLine()) != null) {
				songArtistAndTitle = trackerLine.split(" - ");
				songToLoad = new Song(songArtistAndTitle[0], songArtistAndTitle[1]);
				loadedPlaylistSongs.addEnd(songToLoad);
			}
			
			songPlaylist = loadedPlaylistSongs;
			reader.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}


}
