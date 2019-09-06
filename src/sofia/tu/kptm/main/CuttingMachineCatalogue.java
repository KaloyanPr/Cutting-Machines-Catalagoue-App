package sofia.tu.kptm.main;

import static sofia.tu.kptm.main.Operations.CUTTING;
import static sofia.tu.kptm.main.Operations.DRILLING;
import static sofia.tu.kptm.main.Operations.TURNING;
import static sofia.tu.kptm.main.Operations.MILLING;
import static sofia.tu.kptm.main.Operations.GRINDING;
import static sofia.tu.kptm.main.Operations.GEAR_GRINDING;
import static sofia.tu.kptm.main.Operations.SHREDDING;

import java.awt.Container;
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

import sofia.tu.kptm.impl.TurningImpl;
import sofia.tu.kptm.machine.LatheParameters;

public class CuttingMachineCatalogue extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1925207399835543294L;
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
	private JLabel parameter2;
	private JLabel parameter3;

	private String operation;
	private Container container;

	/**
	 * @author Kaloyan Proynov
	 */
	private LatheParameters latheParameters;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new CuttingMachineCatalogue().setVisible(true));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CuttingMachineCatalogue() {
		super("Cutting machines");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		container = this.getContentPane();

		String[] operations = { TURNING, DRILLING, MILLING, GRINDING, GEAR_GRINDING, SHREDDING, CUTTING };
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
		parameter2 = new JLabel("Ширина");
		parameter3 = new JLabel("todo");

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
		add(parameter2);
		add(parameter3);

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
		parameter2.setBounds(120, 115, 210, 25);
		parameter3.setBounds(120, 115, 210, 25);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Object source = e.getSource();
			if (operationsBox.getSelectedItem().equals(TURNING)) {
				parameter1.setText("Максимален обработван диаметър");
				operation = TURNING;
			} else if (operationsBox.getSelectedItem().equals(DRILLING)) {
				parameter1.setText("Диаметър на свредло");
				parameter2.setText("Брой подавания");
				operation = DRILLING;
			} else if (operationsBox.getSelectedItem().equals(MILLING)) {
				parameter1.setText("Диаметър");
				operation = MILLING;
			} else if (operationsBox.getSelectedItem().equals(GRINDING)) {
				operation = GRINDING;
			} else if (operationsBox.getSelectedItem().equals(SHREDDING)) {
				operation = SHREDDING;
			} else if (operationsBox.getSelectedItem().equals(GEAR_GRINDING)) {
				operation = GEAR_GRINDING;
			} else if (operationsBox.getSelectedItem().equals(CUTTING)) {
				operation = CUTTING;
			}
			if (source == searchButton) {
				switch (operation) {
				case TURNING:
					TurningImpl turningImpl = new TurningImpl();
					latheParameters = turningImpl.getLatheParameters(Integer.parseInt(paramInput1.getText()),
							Integer.parseInt(paramInput2.getText()), Integer.parseInt(paramInput3.getText()));
					TurningImpl.handleTurningMachineRequest(latheParameters);
					break;
				case DRILLING:
					break;
				default:
					break;
				}
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		// TODO Auto-generated method stub

	}

}