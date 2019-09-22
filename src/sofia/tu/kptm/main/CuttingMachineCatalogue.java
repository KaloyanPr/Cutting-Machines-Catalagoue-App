package sofia.tu.kptm.main;

import static sofia.tu.kptm.main.Operations.CUTTING;
import static sofia.tu.kptm.main.Operations.DRILLING;
import static sofia.tu.kptm.main.Operations.TURNING;
import static sofia.tu.kptm.main.Operations.MILLING;
import static sofia.tu.kptm.main.Operations.GRINDING;
import static sofia.tu.kptm.main.Operations.GEAR_PROCESSING;
import static sofia.tu.kptm.main.Operations.SCRAPING;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sofia.tu.kptm.impl.CatalogueService;
import sofia.tu.kptm.impl.MachineNotFoundException;

@SuppressWarnings("rawtypes")
public class CuttingMachineCatalogue implements ActionListener {

	private static final String DEFAULT_DIAMETER = "Обработван диаметър в мм";
	private static final String DEFAULT_WIDTH = "Обработвана ширина (по избор) в мм";
	private static final String NAME_OF_MACHINE = "Име на намерена машина: ";
	private static final String ERROR_TITLE = "Грешка";
	private static final String INFO_TITLE = "Инфо";

	private JFrame frame;
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel panel6;
	private JButton searchButton;
	private JComboBox operationsBox;
	private JLabel chooseProcessLabel;
	private JTextField paramInput1;
	private JTextField paramInput2;
	private JLabel draftLabel;
	private JLabel infoLabel;
	private JLabel kinematicsLabel;
	private JLabel draftImageLabel;
	private JLabel infoImageLabel;
	private JLabel kinematicsImageLabel;
	private JLabel machineNameLabel;
	private JLabel parameter1;
	private JLabel parameter2;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnAbout;
	private JMenuItem catalogue;

	private String operation = TURNING;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			CuttingMachineCatalogue window = new CuttingMachineCatalogue();
			window.frame.setVisible(true);
		});
	}

	@SuppressWarnings({ "unchecked" })
	public CuttingMachineCatalogue() {
		frame = new JFrame();
		frame.setTitle("Cutting Machines Catalogue");
		frame.setBounds(100, 100, 1158, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		chooseProcessLabel = new JLabel("Избор");
		panel.add(chooseProcessLabel);

		panel1 = new JPanel();
		panel.add(panel1);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

		panel2 = new JPanel();
		panel1.add(panel2);
		panel2.setLayout(new GridLayout(0, 2, 0, 0));

		operationsBox = new JComboBox();
		operationsBox.setModel(new DefaultComboBoxModel(
				new String[] { TURNING, MILLING, DRILLING, SCRAPING, GRINDING, GEAR_PROCESSING, CUTTING }));
		operationsBox.addActionListener(this);
		panel2.add(operationsBox);

		parameter1 = new JLabel(DEFAULT_DIAMETER);
		panel1.add(parameter1);

		paramInput1 = new JTextField();
		paramInput1.setText("");
		panel1.add(paramInput1);
		paramInput1.setColumns(10);

		parameter2 = new JLabel(DEFAULT_WIDTH);
		panel.add(parameter2);

		paramInput2 = new JTextField();
		panel.add(paramInput2);
		paramInput2.setColumns(10);

		searchButton = new JButton("Търси");
		searchButton.addActionListener(this);
		panel.add(searchButton);

		panel3 = new JPanel();
		frame.getContentPane().add(panel3, BorderLayout.CENTER);
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		infoImageLabel = new JLabel("");
		infoImageLabel.setBounds(new Rectangle(0, 0, 10, 5));
		panel3.add(infoImageLabel);

		draftImageLabel = new JLabel("");
		panel3.add(draftImageLabel);

		kinematicsImageLabel = new JLabel("");
		panel3.add(kinematicsImageLabel);

		panel4 = new JPanel();
		frame.getContentPane().add(panel4, BorderLayout.SOUTH);
		panel4.setLayout(new GridLayout(2, 1, 0, 0));

		panel6 = new JPanel();
		panel4.add(panel6);

		machineNameLabel = new JLabel(" ");
		panel6.add(machineNameLabel);

		panel5 = new JPanel();
		panel5.setLayout(null);
		panel4.add(panel5);

		infoLabel = new JLabel("Техническа характеристика на машината");
		infoLabel.setBounds(100, -10, 300, 30);
		panel5.add(infoLabel);

		draftLabel = new JLabel("Чертеж на машината");
		draftLabel.setBounds(510, -10, 300, 30);
		panel5.add(draftLabel);

		kinematicsLabel = new JLabel("Кинематика на машината");
		kinematicsLabel.setBounds(835, -10, 300, 30);
		panel5.add(kinematicsLabel);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		catalogue = new JMenuItem("Отвори справочник");
		mnFile.add(catalogue);
		catalogue.addActionListener(this);

		mnAbout = new JMenu("About");
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
				parameter1.setText("Диаметър на свредлото в мм ");
				hideParameters();
				operation = DRILLING;
			} else if (operationsBox.getSelectedItem().equals(MILLING)) {
				parameter1
						.setText("Разстояние от оста на вретеното до работната повърнина на масата (максимално) в мм ");
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
				parameter1.setText("Модул на зъбно колело в мм ");
				parameter2.setText("Външен диаметър в мм ");
				operation = GEAR_PROCESSING;
			} else if (operationsBox.getSelectedItem().equals(CUTTING)) {
				parameter1.setText("Отрязван материал (кръгъл) в мм");
				hideParameters();
				operation = CUTTING;
			}
			if (source == searchButton) {
				handleSearchOperation();
				paramInput1.setText("");
				paramInput2.setText("");
			}
			if (source == catalogue) {
				handleCatalogueOpening();
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(frame, "Моля въведете цяло число в текстовото поле!", ERROR_TITLE,
					JOptionPane.ERROR_MESSAGE);
		} catch (IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(frame, iae.getMessage(), ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
		} catch (MachineNotFoundException mnfe) {
			JOptionPane.showMessageDialog(frame, mnfe.getMessage(), INFO_TITLE, JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, "Възникна неочаквана грешка", ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	private void handleSearchOperation() throws IOException, SQLException, MachineNotFoundException {
		CatalogueService catalogueService = new CatalogueService();
		int param1 = Integer.parseInt(paramInput1.getText());
		int param2 = 0;
		if (!paramInput2.getText().isBlank()) {
			param2 = Integer.parseInt(paramInput2.getText());
		}
		if (param1 < 0 || param2 < 0) {
			throw new IllegalArgumentException("Параметрите не може да са отрицателни!");
		}
		switch (operation) {
		case TURNING:
			infoImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForTurningOperation(param1, param2, "lathe_info"))));
			draftImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForTurningOperation(param1, param2, "lathe_draft"))));
			kinematicsImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForTurningOperation(param1, param2, "lathe_kinematics"))));
			machineNameLabel.setText(NAME_OF_MACHINE + catalogueService.getMachineName(param1, param2,
					getQueryForTurningOperation(param1, param2, "lathe_name")));
			break;
		case DRILLING:
			infoImageLabel.setIcon(new ImageIcon(
					catalogueService.getImage(param1, param2, getQueryForDrillingOperation(param1, "drill_info"))));
			draftImageLabel.setIcon(new ImageIcon(
					catalogueService.getImage(param1, param2, getQueryForDrillingOperation(param1, "drill_draft"))));
			kinematicsImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForDrillingOperation(param1, "drill_kinematics"))));
			machineNameLabel.setText(NAME_OF_MACHINE + catalogueService.getMachineName(param1, param2,
					getQueryForDrillingOperation(param1, "drill_name")));
			break;
		case MILLING:
			infoImageLabel.setIcon(new ImageIcon(
					catalogueService.getImage(param1, param2, getQueryForMillingOperation(param1, "mill_info"))));
			draftImageLabel.setIcon(new ImageIcon(
					catalogueService.getImage(param1, param2, getQueryForMillingOperation(param1, "mill_draft"))));
			kinematicsImageLabel.setIcon(new ImageIcon(
					catalogueService.getImage(param1, param2, getQueryForMillingOperation(param1, "mill_kinematics"))));
			machineNameLabel.setText(NAME_OF_MACHINE + catalogueService.getMachineName(param1, param2,
					getQueryForMillingOperation(param1, "mill_name")));
			break;
		case GRINDING:
			param1 = checkIncompatibleParams(param1, param2);
			infoImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForGrindingOperation(param1, param2, "grind_info"))));
			draftImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForGrindingOperation(param1, param2, "grind_draft"))));
			kinematicsImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForGrindingOperation(param1, param2, "grind_kinematics"))));
			machineNameLabel.setText(NAME_OF_MACHINE + catalogueService.getMachineName(param1, param2,
					getQueryForGrindingOperation(param1, param2, "grind_name")));
			break;
		case SCRAPING:
			param1 = checkIncompatibleParams(param1, param2);
			infoImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForScrapingOperation(param1, param2, "scraper_info"))));
			draftImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForScrapingOperation(param1, param2, "scraper_draft"))));
			kinematicsImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForScrapingOperation(param1, param2, "scraper_kinematics"))));
			machineNameLabel.setText(NAME_OF_MACHINE + catalogueService.getMachineName(param1, param2,
					getQueryForScrapingOperation(param1, param2, "scraper_name")));
			break;
		case GEAR_PROCESSING:
			param2 = Integer.parseInt(paramInput2.getText());
			infoImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForGearProcessingOperation(param1, param2, "gear_info"))));
			draftImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForGearProcessingOperation(param1, param2, "gear_draft"))));
			kinematicsImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForGearProcessingOperation(param1, param2, "gear_kinematics"))));
			machineNameLabel.setText(NAME_OF_MACHINE + catalogueService.getMachineName(param1, param2,
					getQueryForGearProcessingOperation(param1, param2, "gear_name")));
			break;
		case CUTTING:
			infoImageLabel.setIcon(new ImageIcon(
					catalogueService.getImage(param1, param2, getQueryForCuttingOperation(param1, "cutter_info"))));
			draftImageLabel.setIcon(new ImageIcon(
					catalogueService.getImage(param1, param2, getQueryForCuttingOperation(param1, "cutter_draft"))));
			kinematicsImageLabel.setIcon(new ImageIcon(catalogueService.getImage(param1, param2,
					getQueryForCuttingOperation(param1, "cutter_kinematics"))));
			machineNameLabel.setText(NAME_OF_MACHINE + catalogueService.getMachineName(param1, param2,
					getQueryForCuttingOperation(param1, "cutter_name")));
			break;
		default:
			break;
		}
	}

	private String getQueryForTurningOperation(int param1, int param2, String column) {
		if (param2 == 0) {
			return String.format(
					"SELECT %s FROM lathes WHERE maxProcessedDiameter >= %d ORDER BY maxProcessedDiameter ", column,
					param1);
		}

		return String.format(
				"SELECT %s FROM lathes WHERE maxProcessedDiameter >= %d AND maxProcessedWidth > %d ORDER BY maxProcessedDiameter ",
				column, param1, param2);

	}

	private String getQueryForDrillingOperation(int param1, String column) {
		return String.format("SELECT %s FROM drills WHERE drillDiameter >= %d ORDER BY drillDiameter", column, param1);
	}

	private String getQueryForMillingOperation(int param1, String column) {
		return String.format("SELECT %s FROM mills WHERE maxDistance >= %d AND minDistance <= %d ORDER BY maxDistance",
				column, param1, param1);
	}

	private String getQueryForGrindingOperation(int param1, int param2, String column) {
		if (param2 == 0) {
			return String.format(
					"SELECT %s FROM grinders WHERE maxProcessedDiameter >= %d ORDER BY maxProcessedDiameter ", column,
					param1);
		}
		return String.format("SELECT %s FROM grinders WHERE maxProcessedWidth >= %d ORDER BY maxProcessedWidth ",
				column, param2);
	}

	private String getQueryForScrapingOperation(int param1, int param2, String column) {
		if (param2 == 0) {
			return String.format(
					"SELECT %s FROM scraping WHERE maxProcessedDiameter >= %d ORDER BY maxProcessedDiameter ", column,
					param1);
		}
		return String.format("SELECT %s FROM scraping WHERE maxProcessedWidth >= %d ORDER BY maxProcessedWidth ",
				column, param2);
	}

	private String getQueryForGearProcessingOperation(int param1, int param2, String column) {
		return String.format(
				"SELECT %s FROM gears WHERE maxModule >= %d AND maxExternalDiameter >= %d ORDER BY maxExternalDiameter",
				column, param1, param2);
	}

	private String getQueryForCuttingOperation(int param1, String column) {
		return String.format("SELECT %s FROM cutting where maxCutMaterial >= %d ORDER BY maxCutMaterial", column,
				param1);
	}

	private int checkIncompatibleParams(int param1, int param2) {
		if (param2 != 0) {
			param1 = 0;
			JOptionPane.showMessageDialog(frame,
					"Диаметърът се приема за 0, когато има зададена ширина за тази операция.", INFO_TITLE,
					JOptionPane.INFORMATION_MESSAGE);
		}
		return param1;
	}

	private void handleCatalogueOpening() throws IOException {
		File file = new File("Метал.pdf");
		if (!Desktop.isDesktopSupported()) {
			JOptionPane.showMessageDialog(frame, "Не може да отворите файла на тази платформа", INFO_TITLE,
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Desktop desktop = Desktop.getDesktop();
		if (file.exists()) {
			desktop.open(file);
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
}