package datnnhom12api.repository;

import datnnhom12api.dto.StatisticalProductDTO;
import datnnhom12api.dto.SumProductDTO;
import datnnhom12api.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    @Query("SELECT c FROM ProductEntity c WHERE c.name = ?1")
    ProductEntity findByProductName(String name);

    @Query("SELECT c FROM ProductEntity c WHERE c.id = ?1")
    ProductEntity findByProductID(Long id);

    @Query("SELECT new SumProductDTO(count (c.id))FROM ProductEntity c")
    SumProductDTO sumProduct();
}
