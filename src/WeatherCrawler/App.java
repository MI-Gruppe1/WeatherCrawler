/*
 * Author: Jan-Peter Petersen & Johannes Berger
 */

package WeatherCrawler;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

public class App extends TimerTask {
	// Fancy api call der uns die daten in celsius ausgibt (Anfang und Ende, dazwischen muss die ID)
	String apiCallFirstPart = "http://api.openweathermap.org/data/2.5/weather?id=";
	String apiCallSecondPart = "&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric";
	
	//IDs der einzelnen Stationen
	String[] idArray = {"2911288", //Hamburg mitte
			"2841374",	//Sasel
			"6694704",	//Rothenburgsort
			"2910685",	//Harburg
			"7290243",	//Bergedorf
			"2862026",	//Norderstedt
			"2853658",	//Pinneberg
			"2813464",	//Wedel
			"2919880",	//Glinde
			"2959083",	//Ahrensburg
			"2911285"}; //Wandsbek
	

	public static void main(String[] args) {

		/* Create Timer. Time will execute every hour. */
		Timer timer = new Timer();
		timer.schedule(new App(), 1000, 3600000);

	}

	@Override
	public void run() {
		WebCrawler crawler = new WebCrawler();
		for (String id : idArray) {
			try {
				System.out.println("Fetching");
				JSONObject fetchedJSON = crawler.readJsonFromUrl(apiCallFirstPart + id + apiCallSecondPart);
				System.out.println("Parsing");
				String parsedJSON = crawler.parseJSON(fetchedJSON);
				// crawler.persistData();
				System.out.println("Sending");
				crawler.sendToDB(parsedJSON);
			} catch (Exception e) {
				System.out.println(e);
				// MailNotification.sendMail(e);
			}
		}
	}
}
