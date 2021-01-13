
public class App {

	private Movie[] movies; 
	
	public App(String[][] str) {
	
		//create a list of movie objects and populate the array for each
		this.movies = new Movie[50]; 
		for(int i= 0; i < 50; i++) {
			this.movies[i] = new Movie(str[i][0], str[i][1], str[i][2], str[i][3]);  
		}
		
	}
	
	//print the top X number of movies. If there are filters than it will be the top 10 with that filter.  
	public void printMovies(int num) {
		//will keep track of the total movies printed
		int counter = 0; 
		for(int i = 0; i < 50; i++) {
			
			if(counter == num) {
				return; 
			}
			
			if(movies[i].getHandler() == false) {
				continue; 
			}
			
			System.out.print("Rank: " + (i+1) + " - \"" + movies[i].getName() + "\"" ); 
			System.out.println( " | Year: " + movies[i].getYear()); 
			System.out.println( "" + movies[i].getRating() + "/10.0 Rating" ); 
			System.out.println( "MPAA Rating: \"" + movies[i].getMPAA() + "\"" ); 
			System.out.println( "Runtime: " + movies[i].getLength() + " minutes" );
			System.out.println( "Genre(s): " + movies[i].getGenre()); 
			System.out.println( "\"" + movies[i].getSummary() + "\"\n" ); 
			counter++; 
		}
		
		if(counter == 0) {
			System.out.println("No movies were found.");
		}
	}
	
	
	
	//The given input in guaranteed to be valid as it was already checked, if it matches the genre of a movie then set the handler to true, otherwise false. 
	public void moviesWithGenre(String genre) {
		
		for(int i = 0; i < 50; i++) {
			if(movies[i].getGenre().contains(genre)) {
				movies[i].setHandler(true);
			}
			else {
				movies[i].setHandler(false);
			}
		}
	}
	
	//If a movie has the same MPAA rating as the one given, the handler will become true which will allow for it to be printed
	public void moviesWithMPAA(String MPAA) {
		
		//Since there are multiple types such as unrated/approved combine them all. 
		if(MPAA.equals("Other")) {
			for(int i = 0; i < 50; i++) {
				if(movies[i].getMPAA().equals("R") || movies[i].getMPAA().equals("PG-13") || movies[i].getMPAA().equals("PG") || movies[i].getMPAA().equals("G")) {
					movies[i].setHandler(false);
				}
				else {
					movies[i].setHandler(true);
				}
			}
			return; 
		}
		
		//if its a typical rating: 
		for(int i = 0; i < 50; i++) {
			if(movies[i].getMPAA().equals(MPAA)) {
				movies[i].setHandler(true);
			}
			else {
				movies[i].setHandler(false);
			}
		}
	}
	
	//if value = 0, all movies from that year, if vale = -1 then all movies from and prior to, if val = 1 then all movies from and past that year
	public void moviesFromYear(int year, int value) {
		//from only
		if(value == 0) {
			for(int i = 0; i < 50; i++) {
				if(movies[i].getYear() == year) {
					movies[i].setHandler(true);
				}
				else {
					movies[i].setHandler(false);
				}
			}
			return;
		}
		
		//also prior
		if(value < 0) {
			for(int i = 0; i < 50; i++) {
				if(movies[i].getYear() <= year) {
					movies[i].setHandler(true);
				}
				else {
					movies[i].setHandler(false);
				}
			}
			return; 
		}
		
		//also past
		if(value > 0) {
			for(int i = 0; i < 50; i++) {
				if(movies[i].getYear() >= year) {
					movies[i].setHandler(true);
				}
				else {
					movies[i].setHandler(false);
				}
			}
		}
	}

	//movies that have a shorter length that the time provided in minutes
	public void moviesShorterThanLength(int time) {
		for(int i = 0; i < 50; i++) {
			if(movies[i].getLength() <= time) {
				movies[i].setHandler(true);
			}
			else {
				movies[i].setHandler(false);
			}
		}
	}
	
	//movies that have a shorter length that the time provided in minutes
		public void moviesRatedHigher(Double Rating) {
			for(int i = 0; i < 50; i++) {
				if(movies[i].getRating() >= Rating) {
					movies[i].setHandler(true);
				}
				else {
					movies[i].setHandler(false);
				}
			}
		}
	
}
