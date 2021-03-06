package WeatherCrawler;

import com.google.gson.Gson;

/**
 * 
 * @author Johannes Berger
 *
 *         Representation of one dataset as a Java object
 */
public class WeatherDataObject {

	private String weatherIcon;
	private String weatherDesc;
	private String weatherDescDetail;
	private String stationName;
	private double longitude;
	private double latitude;
	private double temperature;
	private int humidity;
	private int pressure;
	private double windDeg;
	private double windSpeed;
	private long timeStamp;

	/**
	 * Will construct a new WeatherDataObject and fill it with the given values
	 * 
	 * @param weatherIcon
	 * @param weatherDesc
	 * @param weatherDescDetail
	 * @param stationName
	 * @param temperature
	 * @param humidity
	 * @param pressure
	 * @param windDeg
	 * @param windSpeed
	 * @param timeStamp
	 */
	public WeatherDataObject(String weatherIcon, String weatherDesc, String weatherDescDetail, String stationName,
			double longitude, double latitude, double temperature, int humidity, int pressure, double windDeg,
			double windSpeed, long timeStamp) {
		super();
		this.weatherIcon = weatherIcon;
		this.weatherDesc = weatherDesc;
		this.weatherDescDetail = weatherDescDetail;
		this.stationName = stationName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		this.windDeg = windDeg;
		this.windSpeed = windSpeed;
		this.timeStamp = timeStamp;
	}

	/**
	 * Converts a JSON String into a Java object
	 * 
	 * @param json
	 * @return WeatherDataObject
	 */
	public static WeatherDataObject jsonToJavaObeject(String json) {
		Gson gson = new Gson();
		WeatherDataObject weatherDataObject = gson.fromJson(json, WeatherDataObject.class);
		return weatherDataObject;
	}

	/**
	 * Converts this object into a JSON String
	 * 
	 * @return JSON String
	 */
	public String toJSON() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public String getWeatherIcon() {
		return weatherIcon;
	}

	public String getWeatherDesc() {
		return weatherDesc;
	}

	public String getWeatherDescDetail() {
		return weatherDescDetail;
	}

	public String getStationName() {
		return stationName;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getlatitude() {
		return latitude;
	}

	public double getTemperature() {
		return temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public int getPressure() {
		return pressure;
	}

	public double getWindDeg() {
		return windDeg;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public long getTimeStamp() {
		return timeStamp;
	}
}
