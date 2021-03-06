package appcenternl.capgemini.com.simplesql.services.domain;

/**
 * Created by appiekap on 03/10/2017.
 */

public class Address
{
    private Geo geo;

    private String zipcode;

    private String street;

    private String suite;

    private String city;

    public Geo getGeo ()
    {
        return geo;
    }

    public void setGeo (Geo geo)
    {
        this.geo = geo;
    }

    public String getZipcode ()
    {
        return zipcode;
    }

    public void setZipcode (String zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getStreet ()
    {
        return street;
    }

    public void setStreet (String street)
    {
        this.street = street;
    }

    public String getSuite ()
    {
        return suite;
    }

    public void setSuite (String suite)
    {
        this.suite = suite;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [geo = "+geo+", zipcode = "+zipcode+", street = "+street+", suite = "+suite+", city = "+city+"]";
    }
}