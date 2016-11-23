# WeatherCrawler

## ToDo
* transmit complete crawl in one json to WeatherDBService
* unittests
* fix Exceptions

## Known Issues
org.json.JSONException: JSONObject["deg"] not found.
	at org.json.JSONObject.get(JSONObject.java:498)
	at WeatherCrawler.WebCrawler.parseJSON(WebCrawler.java:75)
	at WeatherCrawler.App.run(App.java:48)
	at java.util.TimerThread.mainLoop(Timer.java:555)
	at java.util.TimerThread.run(Timer.java:505)



java.lang.NumberFormatException: For input string: "123.5"
	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
	at java.lang.Integer.parseInt(Integer.java:580)
	at java.lang.Integer.parseInt(Integer.java:615)
	at WeatherCrawler.WebCrawler.parseJSON(WebCrawler.java:75)
	at WeatherCrawler.App.run(App.java:48)
	at java.util.TimerThread.mainLoop(Timer.java:555)
	at java.util.TimerThread.run(Timer.java:505)

