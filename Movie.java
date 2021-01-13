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
	
	private boolean handler; //Will be used to flag a movie if it meets the requirements of a search. 
	
	//constructor
	public Movie(String movieTitle, String movieYear, String movieRating, String movieDescription ) {
		this.title = movieTitle.substring(0, movieTitle.length() - 2); //remove the extra 2 chars that were scraped
		this.year = Integer.parseInt( movieYear.substring(1, movieYear.length() -1) ); //remove the parenthesis surrounding the year
		this.rating = Double.parseDouble(movieRating);
		length = -1; 
		
		populate(movieDescription);
		this.handler = true; 
		
	}
	
	//format the input correctly since the scraped strings are not in proper form. 
	public void populate(String Description) {
		
		StringTokenizer str = new StringTokenizer(Description); 
		Boolean allGenres = false; 
		genre = new ArrayList<>(); 
		String des = ""; 
		
		while(str.hasMoreTokens()) {
			
			 String currentToken = str.nextToken(); 
			 
			 //the first token will always be the MPAA rating. 
			 if(MPAA == null) {
				 
				 
				 //The second token will never be useful unless, however when "Not Rated", neither will the third
				 if(currentToken.equals("Not")) {
					 str.nextToken(); str.nextToken();
					 this.MPAA = "Not Rated"; 
				 }
				 
				 else {
					 this.MPAA = currentToken;
					 str.nextToken();
				 }
				  
			 }
			 
			 if(length == -1 ) {
				 this.length = Integer.parseInt(str.nextToken()); 
				 str.nextToken(); str.nextToken(); //The next two tokens will never be useful.
				
				 continue; 
			 }
			 
			 //the following token will either be part of the genre, or summary, if it contains a comma, then the following token will also be genre, otherwise the summary begins.
			 if(allGenres == false) {
				 
				 if(!currentToken.contains(",")) {
					 genre.add(currentToken);
					 allGenres = true; 
				 }
				 
				 else {
					 genre.add(currentToken.substring(0, currentToken.length() -1 ));
				 }
				 
				 continue; 
			 }
			 
			 //everything else is a description until the "Director:" or the plural form of the token is found
			 if(currentToken.equals("Director:") || currentToken.equals("Directors:")) {
				 this.summary = des.substring(1); 
				 return; 
			 } 
			 //append the entire summary to the string. 
			 des = des + " " +  currentToken; 
			 
			 //print or make additions in terms of Votes/Director/Actor/Income
		
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
	

	public Boolean getHandler() {
		return handler; 
	}
	
	//will change the handler to the specified boolean value
	public void setHandler(Boolean value) {
		handler = value; 
	}
	
}
