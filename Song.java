import java.util.*;

public class Song {
	
	private String title;
	private String artist;
	private DoubleList<Song> allSongs = new DoubleList<Song>();

	public Song(String songArtist, String songTitle) {
	       this.artist = songArtist;
	       this.title = songTitle;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String songArtist) {
		this.artist = songArtist;
	}

	public String getTitle() {
	       return title;
	}

	public void setTitle(String songTitle) {
		this.title = songTitle;
	}	
}
