package hidden.challenge.shoplist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="shops")
public class Shops {
    @Id
    private String id;

    private String picture;

    private String name;

    private String email;

    private String city;

    private Location location;

    public Shops(String id, String picture, String name, String email, String city, Location location) {
        this.id = id;
        this.picture = picture;
        this.name = name;
        this.email = email;
        this.city = city;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shops shops = (Shops) o;

        if (!id.equals(shops.id)) return false;
        if (!picture.equals(shops.picture)) return false;
        if (!name.equals(shops.name)) return false;
        if (!email.equals(shops.email)) return false;
        if (!city.equals(shops.city)) return false;
        return location.equals(shops.location);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + picture.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Shops{" +
                "id='" + id + '\'' +
                ", picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", location=" + location +
                '}';
    }
}
