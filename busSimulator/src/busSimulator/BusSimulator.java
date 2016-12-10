package busSimulator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.ParseException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class BusSimulator {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		Gson gson = new Gson();

		try {
			// Database configuration
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "", "");
			Statement stmt = (Statement) con.createStatement();

			// Json file reading
			JsonReader jsonReader = new JsonReader(new FileReader("D:\\transport\\data.json"));
			jsonReader.beginArray();
			int i = 0;
			String lat = "";
			String lng = "";
			while (jsonReader.hasNext()) {
				jsonReader.beginObject();
				i++;
				while (jsonReader.hasNext()) {

					jsonReader.nextName();
					lat = jsonReader.nextString();
					jsonReader.nextName();
					lng = jsonReader.nextString();
				}
				
				// Inserting data into MySQL DB
				String query = "INSERT INTO routes values (" + i + "," + lat + "," + lng + ")";
				PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
				Thread.sleep(1000);
				preparedStmt.execute();
				jsonReader.endObject();
			}
			jsonReader.endArray();
			jsonReader.close();

			// String query = "INSERT INTO routes values (2, 'lat2', 'lng2')";
			// PreparedStatement preparedStmt = (PreparedStatement)
			// con.prepareStatement(query);
			// preparedStmt.execute();

			// System.out.println("Inserted data");

			ResultSet rs = stmt.executeQuery("select * from routes");
			while (rs.next())
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
