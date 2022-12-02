package datnnhom12api.repository;


import datnnhom12api.entity.accessoryProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryProductRepository extends JpaRepository<accessoryProductEntity, Long> {
}
