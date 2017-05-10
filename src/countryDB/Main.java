package countryDB;

import java.util.List;

/**
 *
 * print statement includes all records in the table
 * included country languages in the print statement
 * added a driver to run SQL
 *
 * Read from the Country database and print data on the countries
 * @author Tim Day
 * @version v.2
 */
public class Main {
    public static void main(String[] args) {
        CountryDB cdb = new CountryDB();
        List<Country> countries = cdb.getCountries();
        System.out.println("All countries:");
        for (Country coun : countries ) {

            System.out.println("Name: " + coun.getName()
                    + "  Population: " + coun.getPopulation()
                    + "  Median Age: " + coun.getMedianAge()
                    + "  Languages" + coun.getLanguages());
        }
    }
}
