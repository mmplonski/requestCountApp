package pl.marcin.plonski.requestcountapp.users.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

public class UserDto {
    private Long id;
    private String login;
    private String name;
    private String type;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("created_at")
    private OffsetDateTime createdAt;
    private BigDecimal calculations;
    private int followers;
    @JsonProperty("public_repos")
    private int publicRepos;


    public UserDto() {
    }

    public UserDto(Long id, String login, String name, String type, String avatarUrl, OffsetDateTime createdAt, BigDecimal calculations, int followers, int publicRepos) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.calculations = calculations;
        this.followers = followers;
        this.publicRepos = publicRepos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getCalculations() {
        return calculations;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public void setCalculations() {
        BigDecimal six = BigDecimal.valueOf(6);
        BigDecimal liczbaFollowers = BigDecimal.valueOf(followers);
        BigDecimal liczbaPublicRepos = BigDecimal.valueOf(publicRepos);
        BigDecimal two = BigDecimal.valueOf(2);

        try {
            this.calculations = six.divide(liczbaFollowers.multiply(two.add(liczbaPublicRepos)), 10, RoundingMode.HALF_UP);
        } catch (ArithmeticException e) {
            this.calculations = BigDecimal.valueOf(0);
        }
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createdAt=" + createdAt +
                ", calculations=" + calculations +
                '}';
    }
}
