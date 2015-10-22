package tdnext;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JViewport;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import tdnext.TDNextLogicAPI.ColourType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.Point;

public class GUI2 extends JFrame {

	private JPanel contentPane;
	private static JTextField textInput;
	private static JTextArea textArea;
	private static ArrayList<Task> parsedInfo;	
	private static TDNextLogicAPI logic1; 
	private static Logger guiLog= Logger.getLogger("GUI");
	
	private static final String help = 
			"This is the help section. Please see below for more information:"
			+"\n\n"
			+ "Create-\n"
			+"To create a task with deadline, use this command:\n"
			+"	ADD <task description> BY <deadline> WITH <importance>\n"
			+"To create an event with a set date and/or time, use this command:\n"
			+"	ADD <event description> ON <date, time> WITH <importance> \n"
			+"To create to-do task that has no date or time, use this command:\n"
			+"	ADD <task description> WITH <importance> \n"
			+"\n"
			+"Read-\n"
			+"To read all the tasks in the list, use this command:\n"
			+"	DISPLAY\n"
			+"\n"
			+"Update-\n"
			+"To update a task, use this command:\n"
			+"	CHANGE <number on the list> \n"
			+"\n"
			+"Delete-\n"
			+"To delete a task, use this command:\n"
			+"	DELETE <number on the list>\n"
			+"\n"
			+"Clear\n"
			+"To delete all tasks, use this command:\n"
			+"	CLEAR<ALL>\n"
			+"\n"
			+"Search\n"
			+"To search for a particular task, use this command:\n"
			+"	SEARCH <keyword>\n"
			+"\n"
			+"Sort-\n"
			+"To sort, use this command:\n"
			+"	SORT <importance/deadline/task/event/to-do>\n"
			+"\n"
			+"Exit-\n"
			+"To exit, use this command:\n"
			+"	EXIT";
	
	//Colors used in GUI display
	private static Color red = new Color(255, 195, 206);
	private static Color yellow = new Color(255, 207, 121);
	private static Color green = new Color(142, 210, 201);
	private static Color white = new Color(236, 236, 240);
	private static Color displayFontColor = null;
	private static Color inputFontColor = null;
	private static Color foreground = new Color(70, 32, 102);
	private static Color background = new Color(230, 230, 250);
	private static String systemFont = new String("Comic Sans MS");
	
	//By Maple: Input and display related
	static String getInput(JTextField textInput){
		return textInput.getText();
	}
	
	static void passInput(String input){
		ArrayList<Task> output = new ArrayList<Task>();
		try {
			output = logic1.executeCommand(input);
			parsedInfo = output;
			clearInput(textInput);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	static void clearInput(JTextField textInput){
		textInput.setText("");
	}
	
	static String getDisplay(ArrayList<Task> parsedInfo, int i){
		String output = new String();
			int j = i+1;
			output = j + ". " + parsedInfo.get(i).toString();
	//		System.out.println("getDisplay: " + output);
		return output;
	}
	
	static JTextArea createLines(String s, int i){
		textArea = new JTextArea(s);
	//	System.out.println("CreateLines: " + s);
		setStyle(i);
		return textArea;
	}
	
	//By Maple: Color related

	static ColourType getColorType(ArrayList<Task> parsedInfo, int i){
		ColourType cT= parsedInfo.get(i).getColour();
	//	System.out.println("color is:" + cT);
		return cT;
	}
	
	static Color decideColor(ColourType cT){
		Color c = null;
		switch(cT){
		case RED:
			c= red;
			break;
		case WHITE:
			c= white;
			break;
		case GREEN:
			c= green;
			break;
		case YELLOW:
			c= yellow;
			break;
		}	
		return c;
	}
	
	static void setStyle(int i){
	//	System.out.println("Style set");
		textArea.setBackground(decideColor(getColorType(parsedInfo, i)));
		textArea.setEditable(false);
		textArea.setFont(new Font(systemFont, Font.PLAIN, 16));
		textArea.setBorder(new LineBorder(new Color(255,255,255)));
	}
	
	//End of functions added by Maple

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		logic1 = new TDNextLogicAPI();
		try {
			parsedInfo = logic1.startProgram();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI2 frame = new GUI2();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error! Please restart program.");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(background);
		contentPane.setBorder(new LineBorder(new Color(153, 102, 204), 1, true));
		setContentPane(contentPane);
		guiLog.log(Level.INFO, "GUI Initialised: 'contentPane'.");
		contentPane.setLayout(new MigLayout("", "[377px][6px][88px]", "[364px][34px][30px]"));
		
		final JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0 3 1,grow");
		guiLog.log(Level.INFO, "GUI Initialised: 'scrollPane'.");
		
		final JPanel panelDisplay = new JPanel();
		scrollPane.setViewportView(panelDisplay);
		guiLog.log(Level.INFO, "GUI Initialised: 'panelDisplay'.");
		panelDisplay.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Task List", TitledBorder.CENTER, TitledBorder.TOP, new Font(systemFont, Font.PLAIN, 16), foreground));
		panelDisplay.setBackground(new Color(255, 250, 250));
		panelDisplay.setLayout(new GridLayout(0, 1, 0, 1));	
		guiLog.log(Level.INFO, "GUI: startProgram called. First parsedInfo recieved.");
		for(int i =0; i<parsedInfo.size(); i++){
			String s = new String(getDisplay(parsedInfo, i));
			panelDisplay.add(createLines(s, i), -1);
			panelDisplay.revalidate();
		}
		if(parsedInfo.size() < 13){
		for(int j=0; j<(13-parsedInfo.size()); j++){
			textArea = new JTextArea("");
			textArea.setEditable(false);
			textArea.setFont(new Font(systemFont, Font.PLAIN, 16));
			textArea.setBorder(new LineBorder(new Color(255,255,255)));
			textArea.setBackground(new Color(255,255,255));
			panelDisplay.add(textArea);
			panelDisplay.revalidate();
		}
		}
		
		JPanel panelCmd = new JPanel();
		panelCmd.setBackground(background);
		panelCmd.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Type in your commands here: ", TitledBorder.LEADING, TitledBorder.TOP, new Font(systemFont, Font.PLAIN, 16), foreground));
		contentPane.add(panelCmd, "cell 0 1 1 2,growx,aligny top");
		panelCmd.setLayout(new BorderLayout(0, 0));
		guiLog.log(Level.INFO, "GUI Initialised: 'panelCmd'.");
		
		textInput = new JTextField();
		guiLog.log(Level.INFO, "GUI Initialised: 'textInput'.");
		textInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDisplay.removeAll();
				panelDisplay.repaint();
				passInput(getInput(textInput));
		//		System.out.println("passInput");
				guiLog.log(Level.INFO, "Last input displayed.");
				for(int i =0; i<parsedInfo.size(); i++){
					String s = new String(getDisplay(parsedInfo, i));
					System.out.println(s);
					panelDisplay.add(createLines(s, i), -1);
					panelDisplay.revalidate();
				}
				if(parsedInfo.size() < 13){
				for(int j=0; j<(13-parsedInfo.size()); j++){
					textArea = new JTextArea("");
					textArea.setEditable(false);
					textArea.setFont(new Font(systemFont, Font.PLAIN, 16));
					textArea.setBorder(new LineBorder(new Color(255,255,255)));
					textArea.setBackground(new Color(255,255,255));
					panelDisplay.add(textArea);
					panelDisplay.revalidate();
				}
				}
			}
		});
		panelCmd.add(textInput);
		textInput.setFont(new Font(systemFont, Font.PLAIN, 16));
		textInput.setForeground(foreground);
		textInput.setColumns(10);
		
		JButton btnHelp = new JButton("HELP");
		guiLog.log(Level.INFO, "GUI Initialised: 'btnHelp'.");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, help);
				guiLog.log(Level.INFO, "Help button pressed.");
			}
		});
		btnHelp.setBackground(new Color(255, 250, 250));
		btnHelp.setForeground(foreground);
		btnHelp.setFont(new Font(systemFont, Font.PLAIN, 15));
		contentPane.add(btnHelp, "cell 2 1,growx,aligny center");
		
		JButton btnTheme = new JButton("THEME");
		btnTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] options = {"Lavander", "Panda", "Sapphire", "Forest"};
				String theme = (String) JOptionPane.showInputDialog(null,
						"Choose one", "Input",
						JOptionPane.INFORMATION_MESSAGE, null,
						options, options[0]);
				System.out.println("theme chosen is " + theme);
			}
		});
		guiLog.log(Level.INFO, "GUI Initialised: 'btnTheme'.");
		btnTheme.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		contentPane.add(btnTheme, "cell 2 2,growx,aligny center");
		btnTheme.setBackground(new Color(255, 250, 250));
		btnTheme.setForeground(foreground);
		btnTheme.setFont(new Font(systemFont, Font.PLAIN, 15));
	}
}
