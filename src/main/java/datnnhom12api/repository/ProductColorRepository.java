package datnnhom12api.repository;

import datnnhom12api.entity.ProductColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColorEntity, Long> {
    @Modifying
    @Query("DELETE FROM ProductColorEntity pc where pc.product.id = ?1")
    void deleteAllProductColorByProductId(Long id);
}
