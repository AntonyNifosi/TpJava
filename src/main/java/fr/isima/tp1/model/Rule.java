package fr.isima.tp1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* Model pour la table rule dans la base de données */
@Entity
@Table(name = "RULE")
public class Rule {
    @Id
    private int id;
    private String name;
    private int points;
    private double min_bound;
    private String component;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getMin_bound() {
        return min_bound;
    }

    public void setMin_bound(float min_bound) {
        this.min_bound = min_bound;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", points=" + points +
                ", min_bound=" + min_bound +
                ", component='" + component + '\'' +
                '}';
    }
}
