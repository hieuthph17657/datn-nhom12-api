package datnnhom12api.repository;


import datnnhom12api.entity.accessoryProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryProductRepository extends JpaRepository<accessoryProductEntity, Long> {
    @Modifying
    @Query("DELETE FROM accessoryProductEntity ac where ac.product.id = ?1")
    void deleteAllAccessoryProductByProductId(Long id);
}
