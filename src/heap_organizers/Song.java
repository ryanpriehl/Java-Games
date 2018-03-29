package heap_organizers;

/**
 * This class creates songs for the Jukebox class.
 * All songs have a title, artist, author, and rating.
 * 
 * @author Ryan
 */
public class Song implements Comparable<Song>{

	/**
	 * Stores the song's title.
	 */
	private String title;
	
	/**
	 * Stores the song's artist.
	 */
	private String artist;
	
	/**
	 * Stores the song's album.
	 */
	private String album;
	
	/**
	 * Stores the song's rating.
	 */
	private int rating;
	
	/**
	 * Creates a new song with given attributes.
	 * @param t The title of the new song.
	 * @param art The artist of the new song.
	 * @param alb The album of the new song.
	 * @param r The rating of the new song.
	 */
	public Song(String t, String art, String alb, int r){
		title = t;
		artist = art;
		album = alb;
		rating = r;
	}
	
	/**
	 * Creates a new song from an old song but with a new rating.
	 * @param s The old song to create the new one from.
	 * @param r The new rating for the song.
	 */
	public Song(Song s, int r){
		title = s.title;
		artist = s.artist;
		album = s.album;
		rating = r;
	}
	
	/**
	 * Compares two songs based on their rating then their title.
	 * @param The song to compare this to.
	 * @return An int, negative if they are in order, 0 if equal, positive if out of order.
	 */
	@Override
	public int compareTo(Song s) {
		if(this.rating != s.rating)
			return this.rating - s.rating;
		else
			return -this.title.compareTo(s.title);
	}
	
	/**
	 * Gets a string with information about the song.
	 * @return The string with the song's information.
	 */
	@Override
	public String toString(){
		return "\"" +title +"\"" +" by " +artist +" (" +rating + ")";
	}
}
