
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;

public class Scraper {
	
	public static void populate(String[][] str) {
		 
		//create the app that will handle of the movie list related functions.
		App movies = new App(str); 
		
		movies.printMovies(50);
		
	}
	
	public static void main(String[] args) throws IOException {

		//Four Strings are scraped for each of the Top 50 movies. 
		String[][] str = new String[50][4];

		Document document = Jsoup.connect("https://www.imdb.com/search/title/?groups=top_250&sort=user_rating").get();

		// The web site title
		System.out.println("Title: " + document.title());

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
			System.out.println(movieTitle);
			System.out.println(movieYear);
			System.out.println(movieRating);
			System.out.println(movieDescription + "\n");

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
