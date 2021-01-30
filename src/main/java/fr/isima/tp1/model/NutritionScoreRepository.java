package fr.isima.tp1.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface NutritionScoreRepository extends JpaRepository<NutritionScore, Integer> {

    @Query("SELECT ns FROM NutritionScore ns WHERE ?1 <= ns.upper_bound AND ?1 >= ns.lower_bound")
    NutritionScore findByScore(int score);
}