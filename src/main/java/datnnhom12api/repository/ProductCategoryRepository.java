package datnnhom12api.repository;


import datnnhom12api.entity.ProcessorEntity;
import datnnhom12api.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long>,
        JpaSpecificationExecutor<ProductCategoryEntity> {
}
