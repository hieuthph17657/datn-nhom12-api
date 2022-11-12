package datnnhom12api.repository;

import datnnhom12api.entity.ReturnDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnDetailRepository extends JpaRepository<ReturnDetailEntity, Long>,
        JpaSpecificationExecutor<ReturnDetailEntity> {

    @Query("select o from ReturnDetailEntity o where o.Return.id = :id")
    List<ReturnDetailEntity> findReturnByIds(@Param("id")Long id);
}
