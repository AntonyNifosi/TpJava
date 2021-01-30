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
    private TestEntityManager entityManager;


    @Autowired
    private RuleRepository repository;

    @Test
    public void findByNameAndValueLimitTest() {

        String[] fieldsN = {"energy_100g", "saturated-fat_100g", "sugars_100g", "salt_100g"};
        String[] fieldsP = {"fiber_100g", "proteins_100g"};
        double[][] limitsN = {
                {0, 335, 670, 1005, 1340, 1675, 2010, 2345, 2680, 3015, 3350},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {0, 4.5, 9, 13.5, 18, 22.5, 27, 31, 36, 40 ,45},
                {0, 90, 180, 270, 360, 450, 540, 630, 720, 810, 900}
        };

        double[][] limitsP = {
                {0, 0.90, 1.9, 2.8, 3.7, 4.7},
                {0, 1.6, 3.2, 4.8, 6.4, 8.0}
        };

        //Check size
        List<Rule> l = repository.findAll();
        assertEquals(l.size(),56);

        int score;
        for (int i = 0; i < fieldsN.length; ++i)
        {
            for(int j = 0; j < limitsN[i].length; ++j)
            {
                score = repository.findByNameAndValue((double)limitsN[i][j],fieldsN[i]);
                assertEquals(score, j);
            }
        }

        for (int i = 0; i < fieldsP.length; ++i)
        {
            for(int j = 0; j < limitsP[i].length; ++j)
            {
                System.out.println("Valeur de i : " + i + " / Valeur de j : " + j);
                score = repository.findByNameAndValue(limitsP[i][j],fieldsP[i]);
                assertEquals(score, j);
            }
        }

    }

    @Test
    public void findByNameAndValueRandomTest() {

        String[] fieldsN = {"energy_100g", "saturated-fat_100g", "sugars_100g", "salt_100g"};
        String[] fieldsP = {"fiber_100g", "proteins_100g"};
        double[][] limitsN = {
                {100, 340, 680, 1100, 1400, 1700, 2200, 2400, 2700, 3100, 3400},
                {0.5f, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5},
                {4, 6, 10, 14, 20, 24, 28, 33, 37 ,42, 50},
                {45, 100, 190, 280, 370, 460, 550, 640, 730, 820, 910}
        };

        double[][] limitsP = {
                {0.5, 1.5, 2.5, 3.5, 4.5, 5.5},
                {0.5, 2.5, 3.5, 5.5, 7.5, 8.5}
        };

        int score;
        for (int i = 0; i < fieldsN.length; ++i)
        {
            for(int j = 0; j < limitsN[i].length; ++j)
            {
                score = repository.findByNameAndValue(limitsN[i][j],fieldsN[i]);
                assertEquals(score, j);
            }
        }

        for (int i = 0; i < fieldsP.length; ++i)
        {
            for(int j = 0; j < limitsP[i].length; ++j)
            {
                score = repository.findByNameAndValue(limitsP[i][j],fieldsP[i]);
                assertEquals(score, j);
            }
        }
    }
}
