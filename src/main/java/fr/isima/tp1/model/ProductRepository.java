package fr.isima.tp1.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/* Repository sotckant tous les produits dont on a déjà fait la requête */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findByBarCode(String barCode);
}
