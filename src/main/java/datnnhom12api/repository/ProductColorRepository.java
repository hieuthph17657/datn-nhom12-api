package datnnhom12api.repository;

import datnnhom12api.entity.ProductColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColorEntity, Long> {
}
