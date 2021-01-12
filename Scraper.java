
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;

public class Scraper {
	
	public static void populate(String[][] str) {
		
		 Movie someMovie = new Movie(str[0][0], str[0][1], str[0][2], str[0][3]);  
		 System.out.println( "\"" + someMovie.getName() + "\" was the name" ); 
		 System.out.println( "\"" + someMovie.getYear() + "\" was the year" ); 
		 System.out.println( "\"" + someMovie.getRating() + "\" was the rating" ); 
		 System.out.println( "\"" + someMovie.getMPAA() + "\" was the MPAA rating" ); 
		 System.out.println( "\"" + someMovie.getLength() + "\" is the length in minutes" );
		 System.out.println( "\"" + someMovie.getGenre() + "\" are the genre(s)" ); 
		 System.out.println( "\"" + someMovie.getSummary() + "\" was the summary" ); 
		 
	}
	
	public static void main(String[] args) throws IOException {

		String[][] str = new String[50][4];

		Document document = Jsoup.connect("https://www.imdb.com/search/title/?groups=top_250&sort=user_rating").get();

		// The web site title
		System.out.println("Title: " + document.title());

		// Get the element from the document
		Elements movies = document.getElementsByClass("lister-item-content");

		int counter = 0;
		// iterate through all 50 movies displayed on the site.
		for (Element movie : movies) {

			// Extract the title, year, rating, and the description
			String movieDescription = movie.getElementsByTag("p").text();
			String movieYear = movie.getElementsByClass("lister-item-year text-muted unbold").text();
			String movieRating = movie.getElementsByTag("strong").text();
			String movieTitle = movie.getElementsByAttributeValueStarting("href", "/title/").text();

			// Print
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
