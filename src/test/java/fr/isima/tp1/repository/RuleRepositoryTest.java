package fr.isima.tp1.repository;


import fr.isima.tp1.model.Rule;
import fr.isima.tp1.model.RuleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RuleRepositoryTest {

    @Autowired
    private RuleRepository repository;

    @Test
    public void findByNameAndValueLimitTest() {

        String[] fieldsN = {"energy_100g", "saturated-fat_100g", "sugars_100g", "salt_100g"};
        String[] fieldsP = {"fiber_100g", "proteins_100g"};
        double[][] limitsN = {
                {0, 335, 670, 1005, 1340, 1675, 2010, 2345, 2680, 3015, 3350},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {0, 4.5, 9, 13.5, 18, 22.5, 27, 31, 36, 40, 45},
                {0, 90, 180, 270, 360, 450, 540, 630, 720, 810, 900}
        };

        double[][] limitsP = {
                {0, 0.90, 1.9, 2.8, 3.7, 4.7},
                {0, 1.6, 3.2, 4.8, 6.4, 8.0}
        };

        //Check size
        List<Rule> l = repository.findAll();
        assertEquals(l.size(), 56);

        /* Test des différentes limite des composantes N */
        int score;
        for (int i = 0; i < fieldsN.length; ++i) {
            for (int j = 0; j < limitsN[i].length; ++j) {
                score = repository.findByNameAndValue((double) limitsN[i][j], fieldsN[i]);
                assertEquals(score, j);
            }
        }

        /* Test des différentes limite des composantes P */
        for (int i = 0; i < fieldsP.length; ++i) {
            for (int j = 0; j < limitsP[i].length; ++j) {
                System.out.println("Valeur de i : " + i + " / Valeur de j : " + j);
                score = repository.findByNameAndValue(limitsP[i][j], fieldsP[i]);
                assertEquals(score, j);
            }
        }

    }

    @Test
    public void findByNameAndValueRandomTest() {

        String[] fieldsN = {"energy_100g", "saturated-fat_100g", "sugars_100g", "salt_100g"};
        String[] fieldsP = {"fiber_100g", "proteins_100g"};
        double[][] randomN = {
                {121, 425, 772, 1254, 1547, 1833, 2304, 2546, 2888, 3158, 4000},
                {0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 15},
                {2, 7, 11, 15, 20, 25, 30, 34, 38, 41, 70},
                {12, 164, 221, 342, 370, 523, 600, 717, 802, 847, 1000}
        };

        double[][] randomP = {
                {0.1, 1.6, 2.3, 3.1, 4.4, 10},
                {0.2, 3.1, 4.2, 5.8, 7.6, 10}
        };

        int score;
        for (int i = 0; i < fieldsN.length; ++i) {
            for (int j = 0; j < randomN[i].length; ++j) {
                score = repository.findByNameAndValue(randomN[i][j], fieldsN[i]);
                assertEquals(score, j);
            }
        }

        for (int i = 0; i < fieldsP.length; ++i) {
            for (int j = 0; j < randomP[i].length; ++j) {
                score = repository.findByNameAndValue(randomP[i][j], fieldsP[i]);
                assertEquals(score, j);
            }
        }
    }
}
