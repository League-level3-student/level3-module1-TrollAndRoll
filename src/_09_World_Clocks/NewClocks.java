package _09_World_Clocks;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.Timer;

public class NewClocks {
	ClockUtilities clockUtil;
	Timer timer;
	TimeZone timeZone;

//	JFrame frame;
//	JPanel panel;
//	JTextArea textArea;

	String city;
	String dateStr;
	String timeStr;

	public NewClocks(String city) {
		clockUtil = new ClockUtilities();

		// The format for the city must be: city, country (all caps)
		//city = "Chicago, US";
		timeZone = clockUtil.getTimeZoneFromCityName(city);

		Calendar calendar = Calendar.getInstance(timeZone);
		String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
		dateStr = dayOfWeek + " " + month + " " + calendar.get(Calendar.DAY_OF_MONTH) + " "
				+ calendar.get(Calendar.YEAR);

//		System.out.println(dateStr);
//
//		// Sample starter program
//		frame = new JFrame();
//		panel = new JPanel();
//		textArea = new JTextArea();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		frame.setSize(100, 100);
//		frame.add(panel);
//		// frame.add(panel1);
//		panel.add(textArea);
//		textArea.setText(city + "\n" + dateStr);
//		System.out.println(dateStr);

//        // This Timer object is set to call the actionPerformed() method every
//        // 1000 milliseconds
//        timer = new Timer(1000, this);
//        timer.start();
	}
}