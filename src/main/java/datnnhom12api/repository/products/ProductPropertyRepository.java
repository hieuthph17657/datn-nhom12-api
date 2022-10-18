package datnnhom12api.repository.products;

import datnnhom12api.entity.products.ProductEntity;
import datnnhom12api.entity.products.ProductPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPropertyRepository extends JpaRepository<ProductPropertyEntity,Long>{
}
