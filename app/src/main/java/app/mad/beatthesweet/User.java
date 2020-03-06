package app.mad.beatthesweet;

public class User {
    private String email;
    private Double weight;
    User (String email, Double weight) {
        this.email = email;
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
