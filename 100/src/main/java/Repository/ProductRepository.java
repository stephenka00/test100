package Repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

public class ProductRepository extends JpaRepository<Product,Long >{
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.category LIKE %:keyword%")
    List<Product> searchProducts(@Param("keyword") String keyword);
}
