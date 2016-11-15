/*
 * Quelle: http://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
 * Author: Johannes berger, Lotaire, Jan-Peter Petersen
 */

package WeatherCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Date;

public class WebCrawler {

	/**
	 * Create Readers to read from the URL. URL is an inputparameter. Create
	 * form the returned String (Methode: readAll) one JSONObject. JSONObject
	 * ist returned
	 */
	public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	/** Read all from Reader rd and put it to one String. Return whole String */
	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	/**
	 * Parses the received JSON for the values we care for
	 * 
	 * @param fetchedJSON
	 *            raw JSON
	 * @return Cleaned Json
	 * @throws JSONException
	 */
	public String parseJSON(JSONObject fetchedJSON) throws JSONException {
		JSONObject desc = fetchedJSON.getJSONArray("weather").getJSONObject(0);
		JSONObject coord = fetchedJSON.getJSONObject("coord");
		JSONObject main = fetchedJSON.getJSONObject("main");
		JSONObject wind = fetchedJSON.getJSONObject("wind");
		String weatherIcon = desc.get("icon").toString();
		String weatherDesc = desc.get("main").toString();
		String weatherDescDetail = desc.get("description").toString();
		String stationName = fetchedJSON.getString("name");
		double longitude = Double.parseDouble(coord.getString("lon"));
		double latitude = Double.parseDouble(coord.getString("lat"));
		double temperature = Double.parseDouble(main.get("temp").toString());
		int humidity = Integer.parseInt(main.get("humidity").toString());
		int pressure = Integer.parseInt(main.get("pressure").toString());
		int windDeg = Integer.parseInt(wind.get("deg").toString());
		double windSpeed = Double.parseDouble(wind.get("speed").toString());
		long dateTime = new Date().getTime();
		WeatherDataObject weatherDataObject = new WeatherDataObject(weatherIcon, weatherDesc, weatherDescDetail,
				stationName, longitude, latitude, temperature, humidity, pressure, windDeg, windSpeed, dateTime);
		return weatherDataObject.toJSON();
	}

	/**
	 * Sends the JSON via REST to the WeatherDBService
	 * 
	 * @param json
	 *            JSON to send
	 * @throws UnirestException
	 */
	public void sendToDB(String json) throws UnirestException {
		Unirest.post("http://localhost:4567/newWeatherData").body(json).asString();
	}
}