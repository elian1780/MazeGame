package dbconnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class SetScores {

	private Connection con;
	private PreparedStatement ps;

	String name, scores;

	public SetScores(String name, String scores) {
		this.name = name;
		this.scores = scores;
	}

	public void setData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/MazeGame?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"Elian", "elian");
			ps = con.prepareStatement("INSERT INTO Scores VALUES (?,?)");
			ps.setString(1, name);
			ps.setString(2, scores);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Success!");
		} catch (Exception e) {
                    System.err.println(e);
		}

	}
}
