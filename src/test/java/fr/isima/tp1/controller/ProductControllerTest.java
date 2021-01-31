package fr.isima.tp1.controller;

import fr.isima.tp1.model.Product;
import fr.isima.tp1.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest
{
    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ExistingBarcodeTest() throws Exception {
        String barcode = "3256540000698";
        Product product = new Product(barcode, "Pains au lait au levain", 4, "Mangeable", "yellow");

        Mockito.when(productService.getProductByBarcode(barcode)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/"+barcode))
                .andExpect(content().string("{\"id\":0," +
                                                           "\"barCode\":\"3256540000698\"," +
                                                           "\"name\":\"Pains au lait au levain\"," +
                                                           "\"nutritionScore\":4.0," +
                                                           "\"classe\":\"Mangeable\"," +
                                                           "\"color\":\"yellow\"}"));
    }

    @Test
    public void NonExistingBarcodeTest() throws Exception {
        String barcode = "0000000000";
        Product product = new Product(barcode, "Jambon cuit de qualite superieure", -4, "Trop Bon", "green");

        Mockito.when(productService.getProductByBarcode(barcode)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/"+barcode))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }
}
