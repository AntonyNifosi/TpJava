package fr.isima.tp1.repository;

import fr.isima.tp1.model.Product;
import fr.isima.tp1.model.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;


    @Autowired
    private ProductRepository pr;

    @Test
    public void TestFindByBarcode() {

        entityManager.persist(new Product("3017620422003", "Test1", 18, "Mouais", "orange"));
        entityManager.persist(new Product("3175681851849", "Test2", -2, "Trop Bon", "green"));
        entityManager.persist(new Product("3274080005003", "Test3", 0, "Bon", "light green"));

        /* Test de la taille */
        List<Product> l = pr.findAll();
        assertEquals(l.size(), 3);

        /* Test si le produit existe */
        String barcode = "3017620422003";
        Product p = pr.findByBarCode(barcode);
        assertNotNull(p);
        assertEquals(p.getBarCode(), barcode);

        /* Test si le produit n'existe pas */
        p = pr.findByBarCode("cheval");
        assertNull(p);
    }
}
