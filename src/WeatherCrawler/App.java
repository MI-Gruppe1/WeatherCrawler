/*
 * Author: Jan-Peter Petersen & Johannes Berger
 */

package WeatherCrawler;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

public class App extends TimerTask {
	// Fancy api call der uns die daten in celsius ausgibt
	String[] urlArray = {"http://api.openweathermap.org/data/2.5/weather?id=2911288&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric", //Hamburg mitte
			"http://api.openweathermap.org/data/2.5/weather?id=2841374&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric",	//Sasel
			"http://api.openweathermap.org/data/2.5/weather?id=6694704&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric",	//Rothenburgsort
			"http://api.openweathermap.org/data/2.5/weather?id=2910685&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric",	//Harburg
			"http://api.openweathermap.org/data/2.5/weather?id=7290243&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric",	//Bergedorf
			"http://api.openweathermap.org/data/2.5/weather?id=2862026&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric",	//Norderstedt
			"http://api.openweathermap.org/data/2.5/weather?id=2853658&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric",	//Pinneberg
			"http://api.openweathermap.org/data/2.5/weather?id=2813464&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric",	//Wedel
			"http://api.openweathermap.org/data/2.5/weather?id=2919880&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric",	//Glinde
			"http://api.openweathermap.org/data/2.5/weather?id=2959083&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric",	//Ahrensburg
			"http://api.openweathermap.org/data/2.5/weather?id=2911285&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric"}; //Wandsbek
	

	public static void main(String[] args) {

		/* Create Timer. Time will execute every hour. */
		Timer timer = new Timer();
		timer.schedule(new App(), 1000, 3600000);

	}

	@Override
	public void run() {
		WebCrawler crawler = new WebCrawler();
		for (String url : urlArray) {
			try {
				JSONObject fetchedJSON = crawler.readJsonFromUrl(url);
				JSONObject parsedJSON = crawler.parseJSON(fetchedJSON);
				// crawler.persistData();
				crawler.sendToDB(parsedJSON);
			} catch (Exception e) {
				System.out.println(e);
				// MailNotification.sendMail(e);
			}
		}
	}
}
