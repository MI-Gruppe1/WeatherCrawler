/*
 * Author: Andreas Loeffler
 * E-Mail: andreas.loeffler@haw-hamburg.de 
 */

package WeatherCrawler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;

public class App extends TimerTask {
	
	public static void main(String[] args) {
		
		/*Create Timer. Time will execute every hour.*/
		Timer timer = new Timer();
		timer.schedule(new App(), 1000, 3600000);

	}

	@Override
	public void run(){
		WebCrawler crawler;
		try {
			crawler = new WebCrawler("http://api.openweathermap.org/data/2.5/weather?id=2911288&appid=92b7bce4aa80a16d6e28c89cbac02736&units=metric");
			crawler.persistData();	
		} catch (Exception e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			// MailNotification.sendMail(e);
		} 
			
	}

}
