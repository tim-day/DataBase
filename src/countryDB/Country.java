package countryDB;
import java.util.ArrayList;

/**
 * included an languabe getter and add a languae to a list
 *
 * A country in the world
 * @author Your Tim Day & Cara Tang
 * @version v.2
 */
public class Country {
	private int id;
	private String name;
	private long population;
	private double medianAge;
	private ArrayList<String> languages;
	
	/**
	 * Create a Country object with the given properties
	 */
	public Country(int id, String name, long population, double medianAge) {
		this.id = id;
		this.name = name;
		this.population = population;
		this.medianAge = medianAge;
		languages = new ArrayList<String>();
	}

	/**
	 *
	 * @return id returns id #
	 */

	public int getId() {
		return id;
	}

	/**
	 *
	 * @return name returns the name of the country
	 */

	public String getName() {
		return name;
	}

	/**
	 *
	 * @return population returns the population of the country
	 */

	public long getPopulation() {
		return population;
	}

	/**
	 *
	 * @return medianAge returns the median age of the country
	 */


	public double getMedianAge() {
		return medianAge;
	}

	/**
	 * add a language to list
	 * @param speak
	 */

	public void addLanguage(String speak){
		languages.add(speak);
	}

	/**
	 *
	 * @return languages returns the languages of the country
	 */

	public ArrayList<String> getLanguages() {
		return languages;
	}
}
