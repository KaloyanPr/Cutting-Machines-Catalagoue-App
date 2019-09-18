package sofia.tu.kptm.main;

import static sofia.tu.kptm.main.Operations.CUTTING;
import static sofia.tu.kptm.main.Operations.DRILLING;
import static sofia.tu.kptm.main.Operations.TURNING;
import static sofia.tu.kptm.main.Operations.MILLING;
import static sofia.tu.kptm.main.Operations.GRINDING;
import static sofia.tu.kptm.main.Operations.GEAR_PROCESSING;
import static sofia.tu.kptm.main.Operations.SCRAPING;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
	private JTextField paramInput2;
	private JLabel jcomp8;
	private JLabel jcomp9;
	private JMenuBar menuBarItem;
	private JLabel draftLabel;
	private JLabel kinematicsLabel;
	private JLabel draftImageLabel;
	private JLabel kinematicsImageLabel;
	private JButton jcomp15;
	private JLabel parameter1;
	private JLabel parameter2;
	private JPanel jpanel1;
	private JPanel boxPanel;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JScrollPane scroll;
	private JFrame frame;
	
	private String operation;
	private Container container;

	/**
	 * @author Kaloyan Proynov
	 */
	private LatheParameters latheParameters;

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
		
		JLabel chooseProcessLabel = new JLabel("Избор");
		panel.add(chooseProcessLabel);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		operationsBox = new JComboBox();
		operationsBox.setModel(new DefaultComboBoxModel(new String[] {TURNING,MILLING,DRILLING,SCRAPING,GRINDING,GEAR_PROCESSING,CUTTING}));
		operationsBox.addActionListener(this);
		panel_2.add(operationsBox);
		
		parameter1 = new JLabel("Диаметър");
		panel_1.add(parameter1);
		
		paramInput1 = new JTextField();
		paramInput1.setText("");
		panel_1.add(paramInput1);
		paramInput1.setColumns(10);
		
		parameter2 = new JLabel("Параметър");
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
		
		draftImageLabel = new JLabel("");
		draftImageLabel.setBounds(new Rectangle(0, 0, 10, 5));
		panel_3.add(draftImageLabel);
		
		draftLabel = new JLabel("");
		panel_3.add(draftLabel);
		
		kinematicsImageLabel = new JLabel("");
		panel_3.add(kinematicsImageLabel);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		frame.getContentPane().add(panel_4, BorderLayout.SOUTH);
		
		JLabel label_3 = new JLabel("New label");
		panel_4.add(label_3,FlowLayout.LEFT);
		
		JLabel label_4 = new JLabel("New label");
		panel_4.add(label_4,FlowLayout.CENTER);
		
		JLabel label_5 = new JLabel("New label");
		panel_4.add(label_5,FlowLayout.RIGHT);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		connectToSqlDb();


	}

	private void connectToSqlDb() {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalogue?useSSL=false",
				"root", "123456")) {

			Statement stmt = connection.createStatement();
			int n = 1250;
			ResultSet rs = stmt.executeQuery(String.format("SELECT lathe_info FROM lathes WHERE maxProcessedDiameter = %d", n));
			parseImageFromDB(rs,draftImageLabel);
			 rs = stmt.executeQuery(String.format("SELECT lathe_kinematics FROM lathes WHERE maxProcessedDiameter = %d", n));
			 parseImageFromDB(rs,kinematicsImageLabel);
			 rs = stmt.executeQuery(String.format("SELECT lathe_draft FROM lathes WHERE maxProcessedDiameter = %d", n));
			 parseImageFromDB(rs,draftLabel);

		} catch (Exception ex) {
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
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
			} else if (operationsBox.getSelectedItem().equals(SCRAPING)) {
				operation = SCRAPING;
			} else if (operationsBox.getSelectedItem().equals(GEAR_PROCESSING)) {
				operation = GEAR_PROCESSING;
			} else if (operationsBox.getSelectedItem().equals(CUTTING)) {
				operation = CUTTING;
			}
			if (source == searchButton) {
				switch (operation) {
				case TURNING:
					TurningImpl turningImpl = new TurningImpl();
					latheParameters = turningImpl.getLatheParameters(Integer.parseInt(paramInput1.getText()),
							Integer.parseInt(paramInput2.getText()));
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

	private void parseImageFromDB(ResultSet rs, JLabel label) throws IOException, SQLException {
		byte barr[] = null;
		while (rs.next()) {
			Blob b = rs.getBlob(1);
			barr = b.getBytes(1, (int) b.length());
			//FileOutputStream fout = new FileOutputStream("./sample/sample.jpg");
			
		}
		File tmpFile = new File("tmpImage");
		OutputStream targetFile= new FileOutputStream(tmpFile);
		targetFile.write(barr);
		BufferedImage bimg = ImageIO.read(tmpFile);
		Image scaled = bimg.getScaledInstance(375, 300, Image.SCALE_SMOOTH); 
		String ss=tmpFile.getAbsolutePath();
		label.setIcon(new ImageIcon(scaled));
		//draftImageLabel.setSize(800, 600);
		targetFile.close();
	}
}