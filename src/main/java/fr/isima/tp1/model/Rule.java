package fr.isima.tp1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rule")
public class Rule {
    @Id
    private int id;
    private String name;
    private int points;
    private float min_bound;
    private String component;
}
