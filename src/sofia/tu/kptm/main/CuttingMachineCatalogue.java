package sofia.tu.kptm.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sofia.tu.kptm.machine.LatheParameters;

public class CuttingMachineCatalogue extends JFrame implements ActionListener {
	private JButton searchButton;
	@SuppressWarnings("rawtypes")
	private JComboBox operationsBox;
	private JLabel chooseProcessLabel;
	private JTextField paramInput1;
	private JTextArea jcomp5;
	private JTextField paramInput2;
	private JTextField paramInput3;
	private JLabel jcomp8;
	private JLabel jcomp9;
	private JMenuBar menuBarItem;
	private JTextArea jcomp11;
	private JLabel jcomp12;
	private JTextArea jcomp13;
	private JLabel jcomp14;
	private JButton jcomp15;
	private JLabel parameter1;
	/**
	 * Author: Kaloyan Proynov
	 */
	private LatheParameters latheParameters;
	private static final long serialVersionUID = -6856265190838119905L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CuttingMachineCatalogue().setVisible(true);
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CuttingMachineCatalogue() {
		super("Cutting machines");
		this.setSize(800, 600);

		String[] operations = { "Turning", "Drilling", "Milling", "Grinding ", "Shredding", "Gear Grinding",
				"Cutting" };
		JMenu fileMenu = new JMenu("File");
		JMenuItem printItem = new JMenuItem("Print");
		fileMenu.add(printItem);
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		JMenu helpMenu = new JMenu("Help");
		JMenuItem contentsItem = new JMenuItem("Contents");
		helpMenu.add(contentsItem);
		JMenuItem aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);

		searchButton = new JButton("Търси");
		operationsBox = new JComboBox(operations);
		chooseProcessLabel = new JLabel("Избор на процес");
		paramInput1 = new JTextField(5);
		jcomp5 = new JTextArea(5, 5);
		paramInput2 = new JTextField(5);
		paramInput3 = new JTextField(5);
		jcomp8 = new JLabel("Параметри");
		jcomp9 = new JLabel("Текстова информация за машината");
		menuBarItem = new JMenuBar();
		menuBarItem.add(fileMenu);
		menuBarItem.add(helpMenu);
		jcomp11 = new JTextArea(5, 5);
		jcomp12 = new JLabel("Чертеж на машината");
		jcomp13 = new JTextArea(5, 5);
		jcomp14 = new JLabel("Кинематична схема на машината");
		jcomp15 = new JButton("Още информация и схеми ");
		parameter1 = new JLabel("Максимален обработван диаметър");

		setPreferredSize(new Dimension(667, 366));
		setLayout(null);

		add(searchButton);
		add(operationsBox);
		add(chooseProcessLabel);
		add(paramInput1);
		add(jcomp5);
		add(paramInput2);
		add(paramInput3);
		add(jcomp8);
		add(jcomp9);
		add(menuBarItem);
		add(jcomp11);
		add(jcomp12);
		add(jcomp13);
		add(jcomp14);
		add(jcomp15);
		add(parameter1);

		searchButton.setBounds(5, 225, 140, 20);
		searchButton.addActionListener(this);
		operationsBox.setBounds(10, 50, 140, 30);
		operationsBox.addActionListener(this);
		chooseProcessLabel.setBounds(30, 25, 100, 25);
		paramInput1.setBounds(5, 110, 100, 25);
		jcomp5.setBounds(365, 300, 315, 190);
		paramInput2.setBounds(5, 140, 100, 25);
		paramInput3.setBounds(5, 180, 100, 25);
		jcomp8.setBounds(5, 85, 100, 25);
		jcomp9.setBounds(370, 260, 220, 30);
		menuBarItem.setBounds(0, 0, 200, 25);
		jcomp11.setBounds(30, 305, 285, 180);
		jcomp12.setBounds(40, 265, 235, 25);
		jcomp13.setBounds(365, 65, 315, 170);
		jcomp14.setBounds(370, 20, 250, 35);
		jcomp15.setBounds(350, 500, 280, 40);
		parameter1.setBounds(115, 110, 210, 25);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Object source = e.getSource();
			if (operationsBox.getSelectedItem().equals("Turning")) {
				latheParameters.setMaxProcessedDiameter(Integer.parseInt(paramInput1.getText()));
			} else if (operationsBox.getSelectedItem().equals("Drilling")) {
				parameter1.setText("zz");
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		// TODO Auto-generated method stub

	}

}