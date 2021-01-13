
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;

public class Scraper {
	
	public static void populate(String[][] str) {
		 
		//create the app that will handle of the movie list related functions.
		App movies = new App(str); 
		
		//The loop will run and ask for input until closed. 
		while(true) {
			
			System.out.println("IMDB Top 50 List - Type an option:");
			System.out.println("(1) Print Entire List (2) Filter by Rank (3) Filter by Rating (4) Filter by Genre (5) Filter by MPPA Rating (6) Filter by Time Length (7) Exit");
			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine(); 
			
			//print all 50
			if(choice.equals("1")) {
				//Since the handler could have been modified, by calling moviesFromYear with the following parameters everything will be set to true.
				movies.moviesFromYear(0, 1);
				movies.printMovies(50);
			}
			
			//filter by rank, Ex: top 10 movies then input 10. 
			else if(choice.equals("2")) {
				System.out.println("Type the Number of Top Movies to Search (1-50) (ELSE) Exit");
				choice = sc.nextLine();
				int n = Integer.parseInt(choice); 
				//check that its valid 
				if(n < 50 && n > 1) {

					//Since the handler could have been modified, by calling moviesFromYear with the following parameters everything will be set to true.
					movies.moviesFromYear(0, 1);
					movies.printMovies(n);
				} 
				//not valid, something else therefore exit 
				else {
					break; 
				}
			}
			
			//Print all with rating higher than or equal to 
			else if(choice.equals("3")) {
				System.out.println("Type the Minimum Rating to Filter By (0.0-10.0) (ELSE) Exit");
				choice = sc.nextLine();
				
				//make sure it is a numerical value before parsing. 
				if(isNumerical(choice) == false ) {
					break; 
				}
				
				double d = Double.parseDouble(choice); 
				
				//check that its valid 
				if(d > 0 && d < 10) {
					movies.moviesRatedHigher(d);
					movies.printMovies(50);
				} 
				//not valid, something else therefore exit 
				else {
					break; 
				}
			}
			
			else if(choice.equals("4")) {
				System.out.println("Choose a Genre to Filter By (1) ");
			}
			
			else if(choice.equals("5")) {
				movies.printMovies(50);
			}
			
			else if(choice.equals("6")) {
				movies.printMovies(50);
			}
			
			else if(choice.equals("7")) {
				movies.printMovies(50);
			}
			
			//not one of the options, therefore exit
			else {
				break; 
			}
			
			//Option to search again or exit
			System.out.println("(1) Search Again (ELSE) Exit"); 
			choice = sc.nextLine(); 
			if(choice.equals("1")) {
				continue; 
			}
			else {
				break; 
			}
			
		}
		
		//movies.moviesWithMPAA("PG-13");
		//movies.moviesFromYear(1956, 0);
		//movies.moviesShorterThanLength(90);
		//movies.moviesWithGenre("Drama");
		//movies.moviesRatedHigher((double)8.5);
		//movies.printMovies(10);
		
	}
	
	public static boolean isNumerical (String choice) { 
		try {  
			Double.parseDouble(choice);  
		    return true;
		} 
		catch(NumberFormatException e){  
		    return false; 	    
		}  
	}
	
	public static void main(String[] args) throws IOException {

		//Four Strings are scraped for each of the Top 50 movies. 
		String[][] str = new String[50][4];

		Document document = Jsoup.connect("https://www.imdb.com/search/title/?groups=top_250&sort=user_rating").get();

		// The web site title
		//System.out.println("Title: " + document.title());

		//Get the element from the item document
		Elements movies = document.getElementsByClass("lister-item-content");

		int counter = 0;
		// iterate through all 50 movies displayed on the site.
		for (Element movie : movies) {

			// Extract the title, year, rating, and the description
			String movieDescription = movie.getElementsByTag("p").text();
			String movieYear = movie.getElementsByClass("lister-item-year text-muted unbold").text();
			String movieRating = movie.getElementsByTag("strong").text();
			String movieTitle = movie.getElementsByAttributeValueStarting("href", "/title/").text();

			//Print for tests
//			System.out.println(movieTitle);
//			System.out.println(movieYear);
//			System.out.println(movieRating);
//			System.out.println(movieDescription + "\n");

			// add it to the 2d array
			str[counter][0] = movieTitle;
			str[counter][1] = movieYear;
			str[counter][2] = movieRating;
			str[counter][3] = movieDescription;
			counter++;

		}

		populate(str); 
		
	}
}
