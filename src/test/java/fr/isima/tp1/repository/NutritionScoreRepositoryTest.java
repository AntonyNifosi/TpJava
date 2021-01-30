package fr.isima.tp1.repository;


import fr.isima.tp1.model.NutritionScore;
import fr.isima.tp1.model.NutritionScoreRepository;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class NutritionScoreRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;


    @Autowired
    private NutritionScoreRepository repository;

    @Test
    public void findByScoreLimitsTest() {


        //Test de la taille totale
        List<NutritionScore> nsList = repository.findAll();
        assertEquals(nsList.size(),5);


        //Test des différentes bornes pour le score nutritionnel
        NutritionScore ns = repository.findByScore(-10);
        assertEquals(ns.getId() , 1);

        ns = repository.findByScore(-1);
        assertEquals(ns.getId() , 1);

        ns = repository.findByScore(0);
        assertEquals(ns.getId() , 2);

        ns = repository.findByScore(2);
        assertEquals(ns.getId() , 2);

        ns = repository.findByScore(3);
        assertEquals(ns.getId() , 3);

        ns = repository.findByScore(10);
        assertEquals(ns.getId() , 3);

        ns = repository.findByScore(11);
        assertEquals(ns.getId() , 4);

        ns = repository.findByScore(18);
        assertEquals(ns.getId() , 4);

        ns = repository.findByScore(19);
        assertEquals(ns.getId() , 5);

        ns = repository.findByScore(40);
        assertEquals(ns.getId() , 5);
    }

    @Test
    public void findByScoreRandomTest() {

        NutritionScore ns = repository.findByScore(-8);
        assertEquals(ns.getId() , 1);

        ns = repository.findByScore(1);
        assertEquals(ns.getId() , 2);

        ns = repository.findByScore(7);
        assertEquals(ns.getId() , 3);

        ns = repository.findByScore(17);
        assertEquals(ns.getId() , 4);

        ns = repository.findByScore(31);
        assertEquals(ns.getId() , 5);

    }

    @Test
    public void findByScoreOutLimitTest() {
        NutritionScore ns = repository.findByScore(-30);
        assertEquals(ns , null);

        ns = repository.findByScore(60);
        assertEquals(ns , null);
    }

}
