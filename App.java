
public class App {

	private Movie[] movies; 
	
	public App(String[][] str) {
	
		//create a list of movie objects and populate the array for each
		this.movies = new Movie[50]; 
		for(int i= 0; i < 50; i++) {
			this.movies[i] = new Movie(str[i][0], str[i][1], str[i][2], str[i][3]);  
		}
		
	}
	
	//print the top X number of movies. 
	public void printMovies(int num) {
		for(int i = 0; i < num; i++) {
			 System.out.print("Rank: " + (i+1) + " - \"" + movies[i].getName() + "\"" ); 
			 System.out.println( " - \"" + movies[i].getYear() + "\"" ); 
			 System.out.println( "\"" + movies[i].getRating() + "\" Rating" ); 
			 System.out.println( "\"" + movies[i].getMPAA() + "\" MPAA Rating" ); 
			 System.out.println( "\"" + movies[i].getLength() + "\" minutes long" );
			 System.out.println( "\"" + movies[i].getGenre() + "\" are the genre(s)" ); 
			 System.out.println( "\"" + movies[i].getSummary() + "\"\n" ); 
	
		}
		
	}
	

}
