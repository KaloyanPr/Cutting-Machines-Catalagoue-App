package sofia.tu.kptm.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;

public class CatalogueService {

	public String getMachineName(int param1, int param2, String query)
			throws MachineNotFoundException, SQLException {
		ResultSet rs = null;
		String name = null;
		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/catalogue?useSSL=false&&allowPublicKeyRetrieval=true", "root", "123456");
				Statement stmt = connection.createStatement()) {
			if (param2 == 0) {
				rs = stmt.executeQuery(String.format(query, param1));
				checkResult(rs);
				name = rs.getNString(1);
			} else {
				rs = stmt.executeQuery(String.format(query, param1, param2));
				checkResult(rs);
				name = rs.getNString(1);	
			}
		} catch (MachineNotFoundException mnfe) {
			throw new MachineNotFoundException(mnfe.getMessage());
		}
		return name;
	}

	public Image getImage(int param1, int param2, String query)
			throws IOException, SQLException, MachineNotFoundException {
		ResultSet rs = null;
		Image image = null;
		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/catalogue?useSSL=false&&allowPublicKeyRetrieval=true", "root", "123456");
				Statement stmt = connection.createStatement()) {
			if (param2 == 0) {
				rs = stmt.executeQuery(String.format(query, param1));
				checkResult(rs);
				image = parseImageFromDB(rs);
			} else {
				rs = stmt.executeQuery(String.format(query, param1, param2));
				checkResult(rs);
				image = parseImageFromDB(rs);
			}
		} catch (MachineNotFoundException mnfe) {
			throw new MachineNotFoundException(mnfe.getMessage());
		}
		return image;
	}

	public Image parseImageFromDB(ResultSet rs) throws IOException, SQLException {
		byte[] barr = null;
		if (rs.first()) {
			Blob b = rs.getBlob(1);
			barr = b.getBytes(1, (int) b.length());
		}
		File tmpFile = new File("tmpImage");
		try (OutputStream targetFile = new FileOutputStream(tmpFile)) {
			targetFile.write(barr);
			BufferedImage bimg = ImageIO.read(tmpFile);
			return bimg.getScaledInstance(375, 300, Image.SCALE_SMOOTH);
		}
	}

	private void checkResult(ResultSet rs) throws MachineNotFoundException, SQLException {
		if (!rs.first()) {
			throw new MachineNotFoundException("Не беше намерена машина в каталога за подадените параметри!");
		}
	}
}
