package _06_Intro_To_Hash_Maps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class _02_LogSearch extends JFrame implements ActionListener {
	HashMap<Integer, String> logs = new HashMap<Integer, String>();

	JButton button1 = new JButton();
	JButton button2 = new JButton();
	JButton button3 = new JButton();
	JButton button4 = new JButton();

	JPanel panel = new JPanel();

	JFrame window = new JFrame();

	/*
	 * Crate a HashMap of Integers for the keys and Strings for the values. Create a
	 * GUI with three buttons.
	 * 
	 * Button 1: Add Entry When this button is clicked, use an input dialog to ask
	 * the user to enter an ID number. After an ID is entered, use another input
	 * dialog to ask the user to enter a name. Add this information as a new entry
	 * to your HashMap.
	 * 
	 * Button 2: Search by ID When this button is clicked, use an input dialog to
	 * ask the user to enter an ID number. If that ID exists, display that name to
	 * the user. Otherwise, tell the user that that entry does not exist.
	 * 
	 * Button 3: View List When this button is clicked, display the entire list in a
	 * message dialog in the following format: ID: 123 Name: Harry Howard ID: 245
	 * Name: Polly Powers ID: 433 Name: Oliver Ortega etc...
	 * 
	 * When this is complete, add a fourth button to your window. Button 4: Remove
	 * Entry When this button is clicked, prompt the user to enter an ID using an
	 * input dialog. If this ID exists in the HashMap, remove it. Otherwise, notify
	 * the user that the ID is not in the list.
	 */
	public static void main(String[] args) {
		_02_LogSearch runner = new _02_LogSearch();
		runner.run();
	}

	void run() {
		button1.setText("Add Entry");
		button1.addActionListener(this);
		button2.setText("Search By ID");
		button2.addActionListener(this);
		button3.setText("View List");
		button3.addActionListener(this);
		button4.setText("Remove Entry");
		button4.addActionListener(this);

		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);

		window.add(panel);
		window.setVisible(true);
		window.setName("Log Search");
		// window.setSize(500, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.button1) {
			Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter an ID number."));
			String name = JOptionPane.showInputDialog(null, "Please enter a name.");

			logs.put(id, name);
		} else if (e.getSource() == this.button2) {
			Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter an ID number."));
			boolean exists = false;
			for (Integer i : logs.keySet()) {
				if (i == id) {
					JOptionPane.showMessageDialog(null, "ID: " + id + ", Name: " + logs.get(i));
					exists = true;
					break;
				}
			}
			if(!exists) {
				JOptionPane.showMessageDialog(null, "This entry does not exists.");
			}
		} else if (e.getSource() == this.button3){
			String message = "";
			for (Integer id : logs.keySet()) {
				String name = logs.get(id);
				message = message + "ID: " + id + ", Name: " + name + "\n";
			}
			JOptionPane.showMessageDialog(null, message);
		}
		else {
			Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter an ID number."));
			boolean exists = false;
			for (Integer i : logs.keySet()) {
				if (i == id) {
					logs.remove(id);
					JOptionPane.showMessageDialog(null, "Log " + id + " has been removed");
					exists = true;
					break;
				}
			}
			if(!exists) {
				JOptionPane.showMessageDialog(null, "This entry does not exists.");
			}
		}
	}

}
