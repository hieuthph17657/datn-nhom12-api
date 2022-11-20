package datnnhom12api.repository;

import datnnhom12api.entity.ConfigurationEntity;
import datnnhom12api.entity.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity,Long> {
    @Query("select c from ConfigurationEntity c where  c.product.id = :ids")
    ConfigurationEntity findByProductId(@Param("ids") Long ids);
}
