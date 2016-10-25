/*
 * Quelle: http://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
 * Author: Andreas L�ffler
 */

package WeatherCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

public class WebCrawler {

	private JSONObject json = null;

	public WebCrawler(String url) throws JSONException, IOException {
		super();
		json = this.readJsonFromUrl(url);
	}


	/* Read all from Reader rd and put it to one String. Return whole String */
	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	/*
	 * Create Readers to read from the URL. URL is an inputparameter. Create
	 * form the returned String (Methode: readAll) one JSONObject. JSONObject
	 * ist returned
	 */
	private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally { // finally will always execute, if an try block exists.
					// Doesnt matter if there is an Exception or not.
			is.close();
		}
	}

	/*
	 * Input-Parameter is JSONArray. Iterate through the array and print needed
	 * Information
	 */
	public void persistData()
			throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = (Connection) DriverManager.getConnection(
				//Original statement
				// "jdbc:mysql://mysqldb:3306/mi",
				// "mi",
				// "miws16"
				"jdbc:mysql://localhost:3306/mi", "root", "mi-gruppe1");

		String query = " insert into crawledWeatherData (weatherIcon, weatherDesc, weatherDescDetail, stationName, temperature, humidity, pressure, windDeg, windSpeed)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement preparedStmt = connection.prepareStatement(query);
		JSONObject desc = json.getJSONArray("weather").getJSONObject(0);
		preparedStmt.setString(1, desc.get("icon").toString());
		preparedStmt.setString(2, desc.get("main").toString());
		preparedStmt.setString(3, desc.get("description").toString());
		preparedStmt.setString(4, json.getString("name"));
		JSONObject main = json.getJSONObject("main");
		preparedStmt.setString(5, main.get("temp").toString());
		preparedStmt.setString(6, main.get("humidity").toString());
		preparedStmt.setString(7, main.get("pressure").toString());
		JSONObject wind = json.getJSONObject("wind");
		preparedStmt.setString(8, wind.get("deg").toString());
		preparedStmt.setString(9, wind.get("speed").toString());

		// execute the preparedstatement
		preparedStmt.execute();

		connection.close();
		System.out.println("Done");
	}
}
