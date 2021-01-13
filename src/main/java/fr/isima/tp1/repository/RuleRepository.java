package fr.isima.tp1.repository;


import fr.isima.tp1.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

}