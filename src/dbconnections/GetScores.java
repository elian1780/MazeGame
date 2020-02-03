package dbconnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetScores {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	int i = 0;
	public String[] s;

	public GetScores() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/MazeGame?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"Elian", "elian");
			st = con.createStatement();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void getData() {
		count();
		String data[] = new String[i];
		try {

			String qwery = "select * from Scores";
			rs = st.executeQuery(qwery);
			int j = 0;
			while (rs.next()) {

				String name = rs.getString("names");
				String score = rs.getString("scores");
				data[j] = name + " - " + score + " seconds ";

			j++;

			}
			s = data;
		} catch (Exception e) {
                    System.out.println(e);
		}

	}

	private int count() {
		try {
			String qwery = "select * from Scores";
			rs = st.executeQuery(qwery);

			while (rs.next()) {

				i++;
			}

		} catch (Exception e) {

		}
		return i;
	}

}
