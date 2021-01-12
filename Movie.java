import java.util.ArrayList;
import java.util.StringTokenizer;

public class Movie {

	private String title; 
	private int year; 
	private double rating; //Rating out of 10 
	private String MPAA; //PG Rating
	private int length; 
	private ArrayList<String> genre; 
	private String summary; 
	
	//constructor
	public Movie(String movieTitle, String movieYear, String movieRating, String movieDescription ) {
		this.title = movieTitle.substring(0, movieTitle.length() - 2); //remove the extra 2 chars that were scraped
		this.year = Integer.parseInt( movieYear.substring(1, movieYear.length() -1) ); //remove the parenthesis surrounding the year
		this.rating = Double.parseDouble(movieRating);
		length = -1; 
		
		populate(movieDescription); 
		
	}
	
	public void populate(String Description) {
		
		StringTokenizer str = new StringTokenizer(Description); 
		Boolean allGenres = false; 
		genre = new ArrayList<>(); 
		String des = ""; 
		
		while(str.hasMoreTokens()) {
			
			 //the first token will always be the MPAA rating. 
			 if(MPAA == null) {
				 this.MPAA = str.nextToken(); 
				 str.nextToken(); //The second token will never be useful. 
			 }
			 
			 if(length == -1 ) {
				 this.length = Integer.parseInt(str.nextToken()); 
				 str.nextToken(); str.nextToken(); //The next two tokens will never be useful.
			 }
			 
			 //the following token will either be part of the genre, or summary, if it contains a comma, then the following token will also be genre, otherwise the summary begins.
			 if(allGenres == false) {
				 String currentGenre = str.nextToken(); 
				 genre.add(currentGenre);
				 
				 if(!currentGenre.contains(",")) {
					 allGenres = true; 
				 }
				 continue; 
			 }
			 
			 
			 //everything else is a description until the "Director:" token is found
			 String currentToken = str.nextToken(); 
			 if(currentToken.equals("Director:")) {
				 return; 
			 } 
			 
			 
			 
			 System.out.println( " '"+currentToken+"'" );
		}
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
	
	public String getMPAA() {
		return MPAA; 
	}
	
	public int getLength() {
		return length; 
	}
	
	public ArrayList<String> getGenre() {
		return genre; 
	}
	
	public String getSummary() {
		return summary; 
	}


	
}
