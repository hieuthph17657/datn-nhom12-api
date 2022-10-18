package datnnhom12api.repository;

import datnnhom12api.entity.products.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository  extends JpaRepository<ConfigurationEntity,Long> {
}
