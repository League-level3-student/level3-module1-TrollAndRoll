package _00_Intro_To_ArrayLists;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class _02_GuestBook implements ActionListener{
	/*
	 * Create a GUI with two buttons. One button reads "Add Name" and the other
	 * button reads "View Names". When the add name button is clicked, display an
	 * input dialog that asks the user to enter a name. Add that name to an
	 * ArrayList. When the "View Names" button is clicked, display a message dialog
	 * that displays all the names added to the list. Format the list as follows:
	 * Guest #1: Bob Banders Guest #2: Sandy Summers Guest #3: Greg Ganders Guest
	 * #4: Donny Doners
	 */
	JFrame window;
	JPanel panel;
	JButton addName;
	JButton viewNames;
	JTextField addButtonText;
	
	String newName;
	
	ArrayList<String> guestBook = new ArrayList<String>();
	
	public static void main(String[] args) {
		new _02_GuestBook().guestBook();
	}
	
	public void guestBook(){
		window = new JFrame();
		window.setName("Guest Book");
		
		panel = new JPanel();
		addName = new JButton();
		viewNames = new JButton();
		addName.addActionListener(this);
		viewNames.addActionListener(this);
		
		addName.setText("Add Name");
		viewNames.setText("View Name");
		panel.add(addName);		
		panel.add(viewNames);
		window.add(panel);
		
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        System.out.println("cow");
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addName) {
			newName = JOptionPane.showInputDialog("Please enter a name to add to the Guest Book");
			guestBook.add(newName);
		}
		
		if(e.getSource() == viewNames) {
			String message = "";
			for(int i = 0; i < guestBook.size(); i++) {
				message = message + "Guest #" + (i + 1) + ": " + guestBook.get(i) + "\n";
			}
			JOptionPane.showMessageDialog(null, message);
		}
	}
	
}
