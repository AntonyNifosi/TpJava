package fr.isima.tp1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RULE")
public class Rule implements Comparable<Rule> {
    @Id
    private int id;
    private String name;
    private int points;
    private float min_bound;
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

    public float getMin_bound() {
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

    @Override
    public int compareTo(Rule rule) {
        if (this.min_bound == rule.min_bound)
            return 0;
        else
            return (this.min_bound < rule.min_bound)? -1 : 1;
    }
}
