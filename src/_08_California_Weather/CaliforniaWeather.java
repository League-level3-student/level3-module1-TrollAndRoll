package _08_California_Weather;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * OBJECTIVE:
 * 1. Create a program that allows the user to search for the weather
 * conditions of a given city in California. Use the example program below
 * and the Utilities class inside this project to get the temperature data
 * from a day in December 2020.
 * Example: User: Encinitas
 *          Program: Encinitas is Overcast with a tempeature of 59.01 �F
 * 
 * 2. Create a way for the user to specify the weather condition and then
 * list the cities that have those conditions.
 * Example: User: Mostly Cloudy
 *          Program: Long Beach, Pomona, Oceanside, ...
 * 
 * 3. Create a way for the user to enter a minimum and maximum temperature
 * and then list the cities that have temperatures within that range
 * Example: User: minimum temperature �F = 65.0, max temperature �F = 70.0
 *          Program: Fortana, Glendale, Escondido, Del Mar, ...
 * 
 * EXTRA:***************************************************************************************************************************
 * Feel free to add pictures for specific weather conditions or a thermometer
 * for the temperature. Also If you want your program to get the current day's
 * temperature, you can get a free API key at: https://openweathermap.org/api
 */

public class CaliforniaWeather implements ActionListener {

	JFrame frame = new JFrame();

	JPanel panel = new JPanel();

	JButton searchName = new JButton();
	JButton searchCondition = new JButton();
	JButton searchTemperature = new JButton();

	HashMap<String, WeatherData> weatherData = Utilities.getWeatherData();

	void start() {
		frame.setVisible(true);
		frame.setTitle("California Weather");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		searchName.addActionListener(this);
		searchName.setText("Search City By Name");
		searchCondition.addActionListener(this);
		searchCondition.setText("Search Cities By Weather Condition");
		searchTemperature.addActionListener(this);
		searchTemperature.setText("Search Cities by temperature range");

		panel.add(searchName);
		panel.add(searchCondition);
		panel.add(searchTemperature);

		frame.add(panel);
		frame.pack();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.searchName) {
			// All city keys have the first letter capitalized of each word
			String cityName = Utilities.capitalizeWords(JOptionPane.showInputDialog("Enter the name of a city"));
			WeatherData datum = this.weatherData.get(cityName);

			if (datum == null) {
				JOptionPane.showMessageDialog(null, "Unable to find weather data for: " + cityName);
			} else {
				JOptionPane.showMessageDialog(null, cityName + " is " + datum.weatherSummary + " with a temperature of "
						+ datum.temperatureF + " F");
			}
		}

		else if (e.getSource() == this.searchCondition) {
			ArrayList<String> citiesWithCondition = new ArrayList<String>();
			String weatherCondition = Utilities.capitalizeWords(JOptionPane.showInputDialog("Enter a weather condition"));

			for (String cityName : weatherData.keySet()) {
				String thisWeatherCondition = Utilities.capitalizeWords(this.weatherData.get(cityName).weatherSummary);
				System.out.println(thisWeatherCondition);
				if (thisWeatherCondition.equals(weatherCondition)) {
					citiesWithCondition.add(cityName);
				}
			}

			String message = "";
			int i = 0;
			for (String cityName : citiesWithCondition) {
				message = message + "\n" + cityName;
				i++;
			}
			System.out.println(i);
			JOptionPane.showMessageDialog(null, "Cities under this weather condition:\n" + message);
		}

		else {// searchTemperature button
			ArrayList<String> citiesInRange = new ArrayList<String>();
			double minTemp = 0, maxTemp = 0;

			try {
				minTemp = Double.parseDouble(JOptionPane.showInputDialog("Input a MINIMUM temperature ( F)"));
			} catch (Exception f) {
				JOptionPane.showMessageDialog(null, "That is not a valid value.");
			}
			try {
				maxTemp = Double.parseDouble(JOptionPane.showInputDialog("Input a MAXIMUM temperature ( F)"));
			} catch (Exception f) {
				JOptionPane.showMessageDialog(null, "That is not a valid value.");
			}
			
			for (String cityName : weatherData.keySet()) {
				double cityTemperature = this.weatherData.get(cityName).temperatureF;
				if (cityTemperature >= minTemp && cityTemperature <= maxTemp) {
					citiesInRange.add(cityName);
				}
			}
			
			String message = "";
			for (String cityName : citiesInRange) {
				message = message + "\n" + cityName;
			}
			JOptionPane.showMessageDialog(null, "Cities within this temperature range:\n" + message);
		}
	}

}
