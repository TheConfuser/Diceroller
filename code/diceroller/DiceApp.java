package code.diceroller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

/**
 * A class to show the gui for the diceroller-application. 
 * The historyfunction is not yet applied fully. There are remainders in the code. 
 * 
 * @author Joakim Rehn
 *
 */
@SuppressWarnings("serial")
public class DiceApp extends JFrame {
	
	private JTextArea resultTextArea;
	private ArrayList<DiceThrow> diceThrowHistoryList;

	/**
	 * Constructor. 
	 *  
	 */
	public DiceApp() {
		super("Skypegruppens OB-diceroller v0.51");
		setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel();
		JButton jb;
		for (int num = 2; num <= 5; num++) {
			jb = new JButton("Ob" + num);
			jb.setName(Integer.toString(num));
			jb.setPreferredSize(new Dimension(80, 40));
			jb.addActionListener(new ButtonListener());
			jb.addKeyListener(new KeyboardListener());
			if(num == 3)
				jb.setPreferredSize(new Dimension(120, 40));
			buttonPanel.add(jb);
			} // for
		
		JButton xButton = new JButton("ObX");
		xButton.addActionListener(new PopupNumberListener());
		xButton.setPreferredSize(new Dimension(80, 40));
			
		buttonPanel.add(xButton);
		add(buttonPanel, BorderLayout.NORTH);
		
		resultTextArea = new JTextArea();
		JScrollPane jsp = new JScrollPane(resultTextArea);
		resultTextArea.setEditable(false);
		resultTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		resultTextArea.setFont(new Font("Verdana", Font.PLAIN, 17));
		resultTextArea.setLineWrap(true);
		resultTextArea.setMargin(new Insets(2, 5, 5, 5));
		resultTextArea.addKeyListener(new KeyboardListener());
		add(jsp, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		JButton questionMark = new JButton("?");
		questionMark.addActionListener(new QuestionListener());
		//JButton historyButton = new JButton("History");
		//historyButton.addActionListener(new HistoryListener());
		bottomPanel.add(questionMark);
		//bottomPanel.add(historyButton);
		add(bottomPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500, 300);
		setLocation(400, 400);
		
	}
	
	/**
	 * Listener for the Ob2-5-buttons in the GUI. 
	 * 
	 * @author Joakim Rehn
	 *
	 */
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			resultTextArea.setBackground(Color.WHITE);
			Object pressed = e.getSource();
			JButton pressedButton = (JButton) pressed;
			int numberOfDices = Integer.parseInt(pressedButton.getName());
			DiceThrow dt = new DiceThrow(numberOfDices);
			resultTextArea.setText(dt.toString());
			if(dt.perfect == true) {
				resultTextArea.setBackground(Color.GREEN);
				for(int i = 1; i < 32; i++)
					resultTextArea.append("Perfect");
			}
			//diceThrowHistoryList.add(dt);
			}
		
	}
	
	/**
	 * Listener for the questionmark-button. 
	 * 
	 * @author Joakim Rehn
	 *
	 */
	class QuestionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String informationText = "Welcome to DiceRoller. " + "\n" + 
					"If you are reading this, you are noob. Fine. " + "\n" + 
					"Each button represents a number of dices to throw." + "\n" + 
					"One group is one throw, one or more sixes gives a new group "
					+ "of dices that are thrown. Simple as that! Stop n00bing plx. \n \n"
					+ "Btw, Swing sucks. A lot. Lol. ";
			resultTextArea.setText(informationText);	
			}
		
	}
	
	/**
	 * Listener for the <code>ObX</code>-button in the GUI. 
	 * 
	 * @author Joakim Rehn
	 *
	 */
	class PopupNumberListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int numberOfDicesToCreate = 0;
			try {
				numberOfDicesToCreate = Integer.parseInt(JOptionPane.showInputDialog("How many OB-dices?"));
				if(numberOfDicesToCreate < 1000)
					resultTextArea.setText(new DiceThrow(numberOfDicesToCreate).toString());
				else
					resultTextArea.setText("Jerk, are you trying to spoil the fun?"
							+ " Pick a smaller number, dumbtard. ");
			} catch (NumberFormatException NFE) {
				resultTextArea.setText("Newb, ge mig en siffra or gtfo. ");
			}
			
			} // actionPerformed
	} // class
	
	/**
	 * Listens for keys that are typed for easier generation of dicethrows. 
	 * Added as keylisteners to individual objects. This can probably be solved 
	 * easier in the future. 
	 * 
	 * @author Joakim Rehn
	 *
	 */
	class KeyboardListener implements KeyListener {
		
		private void createDiceThrow(int numberOfDices) {
			resultTextArea.setBackground(Color.WHITE);
			DiceThrow dt = new DiceThrow(numberOfDices);
			resultTextArea.setText(dt.toString());
			if(dt.perfect == true) {
				resultTextArea.setBackground(Color.GREEN);
				for(int i = 1; i < 32; i++)
					resultTextArea.append("Perfect");
			}
		}

		@Override
		public void keyReleased(KeyEvent ke) {
			if (ke.getKeyCode() >= 48 && ke.getKeyCode() <= 57) {
				createDiceThrow(ke.getKeyCode() - 48);
			} // if
			else if (ke.getKeyCode() >= 96 && ke.getKeyCode() <= 105) {
				createDiceThrow(ke.getKeyCode() - 96);
			} // if
		} // keyReleased

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	/*
	 * Ignored at this moment. Something is not working. 
	 */
	@Deprecated
	class HistoryListener implements ActionListener {

		private JPanel createHistoryPanel(ArrayList<DiceThrow> diceThrowList) {
			JPanel historyPanel = new JPanel();
			JTextArea historyTextArea = new JTextArea(200, 200);
			JScrollPane jsp = new JScrollPane(historyTextArea);
			historyTextArea.setEditable(false);
			historyTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			historyTextArea.setFont(new Font("Verdana", Font.PLAIN, 17));
			historyTextArea.setLineWrap(true);
			historyTextArea.setMargin(new Insets(2, 5, 5, 5));
			
			for (DiceThrow dt : diceThrowList)
				historyTextArea.append(dt.toString());;
			historyPanel.add(jsp);
			
			return historyPanel;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, createHistoryPanel(diceThrowHistoryList));
	} 
} //historylistener
	
}
