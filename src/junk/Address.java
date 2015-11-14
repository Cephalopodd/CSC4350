/**
 * Address class
 * 
 * This data type is a convenience to encapsulate the address
 * information
 * 
 */
package junk;

/**
 * 
 * @author Team SLAM
 */
public class Address {

    private String street, apt, city, state, zip, country;

    /**
     * @Constructor Creates an Address
     * @param street
     * @param apt
     * @param city
     * @param state
     * @param zip
     * @param country
     */
    public Address(String street, String apt, String city, 
                    String state, String zip, String country) {
        this.street = street;
        this.apt = apt;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }
    
    public Address() {
        this("","","","","","");
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the apt
     */
    public String getApt() {
        return apt;
    }

    /**
     * @param apt the apt to set
     */
    public void setApt(String apt) {
        this.apt = apt;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
