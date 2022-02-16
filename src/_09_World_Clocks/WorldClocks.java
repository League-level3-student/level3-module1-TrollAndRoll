package _09_World_Clocks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/*
 * You task is to create a java program that:
 * 1. Displays the time for multiple cities around the world on one display.
 * 2. Gives the user the ability to add a city to the display. One possible
 *    way to do this is to create a HashMap of city names and their
 *    corresponding time zones, e.g. HashMap<String, TimeZone>, then use each
 *    city's TimeZone to get the current date/time every second using a
 *    Timer object (see example code below).
 * 
 * The code below is an example of how to print out a clock for San Diego.
 * Use the ClockUtilities class to find the time zone of each city, then use
 * Calendar.getInstance to return a Calendar object to get the current time for
 * that city. Example:
 *   TimeZone timeZone = clockUtil.getTimeZoneFromCityName("San Diego, US");
 *   Calendar c = Calendar.getInstance(timeZone);
 *   System.out.println("Full date and time: " + calendar.getTime());
 * 
 * NOTE: The program may take a second or two to execute
 * 
 * Calendar class:
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 */

public class WorldClocks implements ActionListener {
	ClockUtilities clockUtil;
	Timer timer;
	TimeZone timeZone, timeZone1, timeZone2;

	JFrame frame;
	JPanel panel, panel1, panel2;
	JButton addButton;
	JTextArea textArea, textArea1, textArea2;

	String city, city1, city2;
	String dateStr, dateStr1, dateStr2;
	String timeStr, timeStr1, timeStr2;

	NewClocks newClock;

	public WorldClocks() {
		clockUtil = new ClockUtilities();

		// The format for the city must be: city, country (all caps)
		city = "Chicago, US";
		timeZone = clockUtil.getTimeZoneFromCityName(city);

		Calendar calendar = Calendar.getInstance(timeZone);
		String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
		dateStr = dayOfWeek + " " + month + " " + calendar.get(Calendar.DAY_OF_MONTH) + " "
				+ calendar.get(Calendar.YEAR);

		city1 = "San Diego, US";
		timeZone1 = clockUtil.getTimeZoneFromCityName(city1);

		Calendar calendar1 = Calendar.getInstance(timeZone1);
		String month1 = calendar1.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		String dayOfWeek1 = calendar1.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
		dateStr1 = dayOfWeek1 + " " + month1 + " " + calendar1.get(Calendar.DAY_OF_MONTH) + " "
				+ calendar1.get(Calendar.YEAR);

		System.out.println(dateStr);

		// Sample starter program
		frame = new JFrame();
		panel = new JPanel();
		textArea = new JTextArea();
		textArea1 = new JTextArea();
		textArea2 = new JTextArea();
		addButton = new JButton();
		addButton.setText("Add New Clock");
		addButton.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(100, 100);
		frame.add(panel);
		// frame.add(panel1);
		panel.add(textArea);
		panel.add(textArea1);
		panel.add(addButton);
		textArea.setText(city + "\n" + dateStr);
		textArea1.setText(city1 + "\n" + dateStr1);
		System.out.println(dateStr);

		// This Timer object is set to call the actionPerformed() method every
		// 1000 milliseconds
		timer = new Timer(1000, this);
		timer.start();
	}

	boolean buttonAdded = false;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Calendar c = Calendar.getInstance(timeZone);
		String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
		String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":"
				+ c.get(Calendar.SECOND) + "]";
		timeStr = militaryTime + twelveHourTime;
		System.out.println(timeStr);
		textArea.setText(city + "\n" + dateStr + "\n" + timeStr);

		Calendar c1 = Calendar.getInstance(timeZone1);
		String militaryTime1 = c1.get(Calendar.HOUR_OF_DAY) + ":" + c1.get(Calendar.MINUTE) + ":"
				+ c1.get(Calendar.SECOND);
		String twelveHourTime1 = " [" + c1.get(Calendar.HOUR) + ":" + c1.get(Calendar.MINUTE) + ":"
				+ c1.get(Calendar.SECOND) + "]";
		timeStr1 = militaryTime1 + twelveHourTime1;
		textArea1.setText(city1 + "\n" + dateStr1 + "\n" + timeStr1);

		frame.pack();

		if (arg0.getSource() == addButton) {
			city2 = JOptionPane.showInputDialog("Name of City, Country (e.g. San Diego, US)");
			newClock = new NewClocks(city2);// TODO on button click, make new new clock object appear or whatever (get a
											// 3rd user inputed clock in frame)
			textArea2.setText(newClock.dateStr);
			panel.add(textArea2);

			buttonAdded = true;
		}

		if (buttonAdded) {
			Calendar c2 = Calendar.getInstance(newClock.timeZone);
			String militaryTime2 = c2.get(Calendar.HOUR_OF_DAY) + ":" + c2.get(Calendar.MINUTE) + ":"
					+ c2.get(Calendar.SECOND);
			String twelveHourTime2 = " [" + c2.get(Calendar.HOUR) + ":" + c2.get(Calendar.MINUTE) + ":"
					+ c2.get(Calendar.SECOND) + "]";
			timeStr2 = militaryTime2 + twelveHourTime2;
			textArea2.setText(city2 + "\n" + newClock.dateStr + "\n" + timeStr2);
		}
	}
}