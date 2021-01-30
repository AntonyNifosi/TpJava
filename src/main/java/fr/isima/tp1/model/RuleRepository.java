package fr.isima.tp1.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RuleRepository extends JpaRepository<Rule, Integer> {
    @Query("SELECT MAX(r.points) FROM Rule r WHERE  ?1 >= r.min_bound AND r.name = ?2")
    Integer findByNameAndValue(float value, String name);
}