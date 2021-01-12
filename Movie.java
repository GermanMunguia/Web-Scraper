
public class Movie {

	private String title; 
	private int year; 
	private double rating; //Rating out of 10 
	private String MPAA; //PG Rating
	private int length; 
	private String[] genre; 
	private String summary; 
	
	//constructor
	public Movie(String movieTitle, String movieYear, String movieRating, String movieDescription ) {
		this.title = movieTitle.substring(0, movieTitle.length() - 2); //remove the extra 2 chars that were scraped
		this.year = Integer.parseInt( movieYear.substring(1, movieYear.length() -1) ); //remove the parenthesis surrounding the year
		this.rating = Double.parseDouble(movieRating);
		
	}
	
	public String getName() {
		return title; 
	}
	
	public int getYear() {
		return year; 
	}
	
	public double getRating() {
		return rating; 
	}

}
