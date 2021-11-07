package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fact {
    private String id;
    private String text;
    private String type;
    private String user;
    private int votes;

    public Fact(@JsonProperty("id") String id,
                @JsonProperty("text") String text,
                @JsonProperty("type") String type,
                @JsonProperty("user") String user,
                @JsonProperty("upvotes") int votes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.votes = votes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", votes=" + votes +
                '}';
    }
}
