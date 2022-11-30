package datnnhom12api.entity;


import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.StorageRequest;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "storage")
@EqualsAndHashCode(callSuper = true)
public class StorageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "storage_detail_id")
    private StorageDetailEntity storageDetail;

    private int total;

    private int number;

    public void setData(StorageRequest request) {
        this.total = request.getTotal();
        this.number = request.getNumber();
    }
}