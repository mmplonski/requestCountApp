package pl.marcin.plonski.requestcountapp.users.model;

import jakarta.persistence.*;

@Entity
@Table(name="REPOSITORY_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;

    private int numberOfCalls;

    public int getNumberOfCalls() {
        return numberOfCalls;
    }

    public void setNumberOfCalls(int numberOfCalls) {
        this.numberOfCalls = numberOfCalls;
    }

    public Long getId() {
        return id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void incrementNumberOfCalls() {
        this.numberOfCalls += 1;
    }
}
