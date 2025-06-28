package java___project;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherClient {
	
	 
    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
        try {
            String apiKey = "aed64bc8d63e16e2fa01367a8be6081e";
            System.out.print("Enter the city name:");
             String city=sc.nextLine();
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

          
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject obj = new JSONObject(response.toString());
            JSONObject main = obj.getJSONObject("main");

            double temp = main.getDouble("temp");
            double feelsLike = main.getDouble("feels_like");
            int humidity = main.getInt("humidity");

            System.out.println("Weather in " + city + ":");
            System.out.println("Temperature: " + temp + "°C");
            System.out.println("Feels Like: " + feelsLike + "°C");
            System.out.println("Humidity: " + humidity + "%");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}