package datnnhom12api.repository;

import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.DiscountEntity;
import datnnhom12api.entity.InformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long>, JpaSpecificationExecutor<DiscountEntity> {
    @Query("SELECT c FROM DiscountEntity c WHERE c.id = ?1")
    DiscountEntity findByIdDiscount(Long id);
}
