package countryDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Read data from the Countries database
 * updated a list to include languages of each country
 *
 * @author Tim Day & Cara Tang
 * @version v.2
 */
public class CountryDB {
	private static final String DB_NAME = "Countries";
	private static final String DB_URL = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/" + DB_NAME;
	private static final String USERNAME = "233jstudent";
	private static final String PASSWORD = "tnedutsj332";
	private static final String GET_COUNTRIES_SQL = "SELECT * FROM COUNTRY";
	private List<Country> countries;
	private static final String GET_LANGUAGE_SQL = "SELECT * FROM COUNTRY_LANGUAGE WHERE CountryId = ?";

	/**
	 * Create a CountryDB object
	 * Read from the Countries database and populate the countries list
	 */
	public CountryDB() {
		countries = readCountriesBasics();
		readLanguages(countries);
	}

	/**
	 * Create and return a connection to the database
	 * @return connection to the countries database
	 */
	private Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		return connection;
	}

	/**
	 * Read country info from the Country table.
	 * If an error occurs, a stack trace is printed to standard error and an empty list is returned.
	 * @return the list of countries read
	 */
	private List<Country> readCountriesBasics() {
		List<Country> countries = new ArrayList<>(); //this waa bizarre to me@! Seems a list and array list are interchangeabl?
		try (
				Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(GET_COUNTRIES_SQL);
				ResultSet rs = stmt.executeQuery()
				) {
			while (rs.next()) {
				countries.add(new Country(rs.getInt("Id"),
						rs.getString("Name"),
						rs.getLong("Population"),
						rs.getDouble("MedianAge")));
			}
		}
		catch (SQLException e) {
			System.err.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
		return countries;
	}

	/**
	 *
	 * @param countries imports a list of countries
	 */
	private void readLanguages(List<Country> countries) {
		try (
				Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_LANGUAGE_SQL)
		) {
			for (Country country : countries) {
				stmt.setInt(1, country.getId());
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					country.addLanguage(rs.getString("Language"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @re this getter returns a list of countries with languages spoke
	 * turn list of countries read from the country database
	 */
	public List<Country> getCountries() {
		return countries;
	}
}
