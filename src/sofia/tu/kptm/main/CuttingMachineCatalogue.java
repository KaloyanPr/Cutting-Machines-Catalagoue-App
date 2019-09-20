package sofia.tu.kptm.main;

import static sofia.tu.kptm.main.Operations.CUTTING;
import static sofia.tu.kptm.main.Operations.DRILLING;
import static sofia.tu.kptm.main.Operations.TURNING;
import static sofia.tu.kptm.main.Operations.MILLING;
import static sofia.tu.kptm.main.Operations.GRINDING;
import static sofia.tu.kptm.main.Operations.GEAR_PROCESSING;
import static sofia.tu.kptm.main.Operations.SCRAPING;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sofia.tu.kptm.impl.CatalogueService;
import sofia.tu.kptm.impl.MachineNotFoundException;

@SuppressWarnings("rawtypes")
public class CuttingMachineCatalogue implements ActionListener {

	private static final String DEFAULT_DIAMETER = "Обработван диаметър";
	private static final String DEFAULT_WIDTH = "Обработвана ширина (по избор)";
	private JButton searchButton;
	private JComboBox operationsBox;
	private JLabel chooseProcessLabel;
	private JTextField paramInput1;
	private JTextField paramInput2;
	private JLabel draftImageLabel;
	private JLabel infoImageLabel;
	private JLabel kinematicsImageLabel;
	private JLabel machineNameLabel;
	private JLabel parameter1;
	private JLabel parameter2;
	private JFrame frame;

	private String operation = TURNING;
	private int param1;
	private int param2;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			CuttingMachineCatalogue window = new CuttingMachineCatalogue();
			window.frame.setVisible(true);
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CuttingMachineCatalogue() {
		frame = new JFrame();
		frame.setTitle("Cutting Machines Catalogue");
		frame.setBounds(100, 100, 1158, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		chooseProcessLabel = new JLabel("Избор");
		panel.add(chooseProcessLabel);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		operationsBox = new JComboBox();
		operationsBox.setModel(new DefaultComboBoxModel(
				new String[] { TURNING, MILLING, DRILLING, SCRAPING, GRINDING, GEAR_PROCESSING, CUTTING }));
		operationsBox.addActionListener(this);
		panel_2.add(operationsBox);

		parameter1 = new JLabel(DEFAULT_DIAMETER);
		panel_1.add(parameter1);

		paramInput1 = new JTextField();
		paramInput1.setText("");
		panel_1.add(paramInput1);
		paramInput1.setColumns(10);

		parameter2 = new JLabel(DEFAULT_WIDTH);
		panel.add(parameter2);

		paramInput2 = new JTextField();
		panel.add(paramInput2);
		paramInput2.setColumns(10);

		searchButton = new JButton("Търси");
		searchButton.addActionListener(this);
		panel.add(searchButton);

		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		infoImageLabel = new JLabel("");
		infoImageLabel.setBounds(new Rectangle(0, 0, 10, 5));
		panel_3.add(infoImageLabel);

		draftImageLabel = new JLabel("");
		panel_3.add(draftImageLabel);

		kinematicsImageLabel = new JLabel("");
		panel_3.add(kinematicsImageLabel);

		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);

		machineNameLabel = new JLabel("");
		panel_6.add(machineNameLabel);

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);

		JLabel infoLabel = new JLabel("Техническа характеристика на машината   	 ");
		panel_5.add(infoLabel);
		
		JLabel draftLabel = new JLabel("Чертеж на машината   	 ");
		panel_5.add(draftLabel);	

		JLabel kinematicsLabel = new JLabel("Кинематика на машината  	");
		panel_5.add(kinematicsLabel);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Object source = e.getSource();
			if (operationsBox.getSelectedItem().equals(TURNING)) {
				showParameters();
				parameter1.setText(DEFAULT_DIAMETER);
				parameter2.setText(DEFAULT_WIDTH);
				operation = TURNING;
			} else if (operationsBox.getSelectedItem().equals(DRILLING)) {
				parameter1.setText("Диаметър на свредлото");
				hideParameters();
				operation = DRILLING;
			} else if (operationsBox.getSelectedItem().equals(MILLING)) {
				parameter1.setText("Разстояние от оста на вретеното до работната повърнина на масата");
				hideParameters();
				operation = MILLING;
			} else if (operationsBox.getSelectedItem().equals(GRINDING)) {
				showParameters();
				parameter1.setText(DEFAULT_DIAMETER);
				parameter2.setText(DEFAULT_WIDTH);
				operation = GRINDING;
			} else if (operationsBox.getSelectedItem().equals(SCRAPING)) {
				showParameters();
				parameter1.setText(DEFAULT_DIAMETER);
				parameter2.setText(DEFAULT_WIDTH);
				operation = SCRAPING;
			} else if (operationsBox.getSelectedItem().equals(GEAR_PROCESSING)) {
				showParameters();
				parameter1.setText("Модул на зъбно колело");
				parameter2.setText("Външен диаметър");
				operation = GEAR_PROCESSING;
			} else if (operationsBox.getSelectedItem().equals(CUTTING)) {
				parameter1.setText("Отрязван материал (кръгъл) в мм");
				hideParameters();
				operation = CUTTING;
			}
			if (source == searchButton) {
				try {
				switch (operation) {
				case TURNING:
					CatalogueService catalogueService = new CatalogueService();
					 param1 = Integer.parseInt(paramInput1.getText());
					if(!paramInput2.getText().isBlank()) {
					 param2 = Integer.parseInt(paramInput2.getText());
					}
					infoImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2, getQueryForTurningOperation(param1, param2, "lathe_info"))));
					draftImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2, getQueryForTurningOperation(param1, param2, "lathe_draft"))));
					kinematicsImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2, getQueryForTurningOperation(param1, param2, "lathe_kinematics"))));
					machineNameLabel.setText("Име на машина: " + catalogueService.getMachineName(param1, param2, getQueryForTurningOperation(param1, param2, "lathe_name")));
					break;
				case DRILLING:
					break;
				case MILLING:
					break;
				case GRINDING:
					break;
				case SCRAPING:
					break;
				case GEAR_PROCESSING:
					break;
				case CUTTING:
					break;
				default:
					break;
				}
				}catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(frame, "Моля въведете цяло число в текстовото поле!","Грешка",JOptionPane.ERROR_MESSAGE);
				}catch (MachineNotFoundException mnfe) {
					JOptionPane.showMessageDialog(frame, mnfe.getMessage(), "Инфо", JOptionPane.INFORMATION_MESSAGE);
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(frame,"Възникна неочаквана грешка" , "Грешка", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	private void showParameters() {
		parameter2.setVisible(true);
		paramInput2.setVisible(true);
	}
	private void hideParameters() {
		parameter2.setVisible(false);
		paramInput2.setVisible(false);
	}
	
	private String getQueryForTurningOperation(int param1, int param2, String column) {
		String query = String.format("SELECT %s FROM lathes WHERE maxProcessedDiameter > %d ORDER BY maxProcessedDiameter ", column, param1);
		if (param2 != 0) {
			query = String.format("SELECT %s FROM lathes WHERE maxProcessedDiameter > %d AND maxProcessedWidth > %d ORDER BY maxProcessedDiameter ", column, param1 , param2);
		}
		return query;
	}

}