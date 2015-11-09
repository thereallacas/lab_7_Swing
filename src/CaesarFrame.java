import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CaesarFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean CODE_DECODE=true;
	private Object[] ABC = new Object[26];
	private JComboBox<Object> selectBox;
	private JTextField textInput = new JTextField(20);
	private JTextField textOutput = new JTextField(20);
	private JButton codeButton = new JButton("Code!"); 
	private JLabel outputLabel = new JLabel("Output:");
	private FlowLayout caesarlayout = new FlowLayout();

	public void addComponentsToPane(final Container pane) {
		final JPanel inputPanel = new JPanel();
		final JPanel outputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		outputPanel.setLayout(new FlowLayout());
		inputPanel.add(selectBox);
		inputPanel.add(textInput);
		inputPanel.add(codeButton);
		outputPanel.add(outputLabel);
		outputPanel.add(textOutput);
		pane.add(inputPanel);
		pane.add(outputPanel);
	}

	private class ActionKeyListener implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			textOutput.setText(caesarCode(textInput.getText(), (char)selectBox.getSelectedItem()));

		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			textOutput.setText(caesarCode(textInput.getText(), (char)selectBox.getSelectedItem()));

		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			textOutput.setText(caesarCode(textInput.getText(), (char)selectBox.getSelectedItem()));			
		}
	}

	private class OkButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (CODE_DECODE)
				textOutput.setText(caesarCode(textInput.getText(), (char)selectBox.getSelectedItem()));
			else
				textOutput.setText(caesarDecode(textOutput.getText(), (char)selectBox.getSelectedItem()));
		}
	}

	private class InputFocusDetect implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			CODE_DECODE=true;
		}

		@Override
		public void focusLost(FocusEvent e) {

		}

	}

	private class OutputFocusDetect implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			CODE_DECODE=false;

		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private static String caesarCode(String input, char offset) {
		input = input.toUpperCase();
		char[] inputarray=input.toCharArray();
		int t = ((int)offset-'A'); 
		for (int i=0; i<inputarray.length;i++){
			if (inputarray[i]==' '){}
			else{
				int charnum = inputarray[i];
				inputarray[i]=(char)('A'+(((charnum-'A')+t)%26));
			}
		}
		String s=new String(inputarray);
		return s;
	}

	private static String caesarDecode(String input, char offset){
		input = input.toUpperCase();
		char[] inputarray=input.toCharArray();
		int t = ((int)offset-'A'); 
		for (int i=0; i<inputarray.length;i++){
			if (inputarray[i]==' '){}
			else{
				int charnum = inputarray[i];
				inputarray[i]=(char)('A'+(((charnum+'A')-t)%26));
			}
		}
		String s=new String(inputarray);
		return s;
	}

	public CaesarFrame(){
		for(char i='A'; i<='Z'; i++)
			ABC[(int)(i-'A')]=i;
		selectBox= new JComboBox<>(ABC);
		this.addComponentsToPane(this.getContentPane());
		this.codeButton.addActionListener(new OkButtonActionListener());
		this.textInput.getDocument().addDocumentListener(new ActionKeyListener());
		this.textInput.addFocusListener(new InputFocusDetect());
		this.textOutput.addFocusListener(new OutputFocusDetect());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("SwingLab");
		this.setSize(400,110);
		this.setResizable(true);
		this.setLayout(caesarlayout);
		Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CaesarFrame cf = new CaesarFrame();
		cf.setVisible(true);

	}
}