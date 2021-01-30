package fr.isima.tp1.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NutritionScoreRepository extends JpaRepository<NutritionScore, Integer> {

}