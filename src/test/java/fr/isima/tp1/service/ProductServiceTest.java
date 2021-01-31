package fr.isima.tp1.service;

import fr.isima.tp1.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@Import(ProductService.class)
public class ProductServiceTest {

    @MockBean
    private ProductRepository pr;
    @MockBean
    private NutritionScoreRepository ns;
    @MockBean
    private RuleRepository ruleRepository;

    @Autowired
    private ProductService ps;


    @Test
    public void getProductByBarcodeTest() throws IOException {
        String[] fieldsN = {"energy_100g", "saturated-fat_100g", "sugars_100g", "salt_100g"};
        String[] fieldsP = {"fiber_100g", "proteins_100g"};

        double[] nValues = {1955, 5.6, 32, 0.58};
        double[] pValues = {4, 6.4};

        int[] N_res = {5, 5, 7, 0};
        int[] P_res = {4, 4};

        String barcode = "7622210449283";
        Product product = new Product(barcode, "Test1", 9, "Mangeable", "yellow");

        for (int i = 0; i < fieldsN.length; ++i) {
            Mockito.when(ruleRepository.findByNameAndValue(nValues[i], fieldsN[i])).thenReturn(N_res[i]);
        }

        for (int i = 0; i < fieldsP.length; ++i) {
            Mockito.when(ruleRepository.findByNameAndValue(pValues[i], fieldsP[i])).thenReturn(P_res[i]);
        }

        Mockito.when(ns.findByScore((int) product.getNutritionScore())).thenReturn(new NutritionScore("Mangeable", 3, 10, "yellow"));

        Mockito.when(pr.findByBarCode(barcode)).thenReturn(product);

        Product newProduct = ps.getProductByBarcode(barcode);
        assertEquals(product, newProduct);
    }
}
