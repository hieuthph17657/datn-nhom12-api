package datnnhom12api.entity;


import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.CardRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Card")
public class CardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "card")
    private List<ProductEntity> products;

    @Column(name = "trandemark")
    private String trandemark;
    @Column(name = "model")
    private String model;
    @Column(name = "memory")
    private String memory;

    @Column(name = "price")
    private Double price;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


    public void setData(CardRequest request) {
        this.memory = request.getMemory();
        this.model = request.getModel();
        this.trandemark = request.getTrandemark();
        this.price = request.getPrice();
        this.category = request.getCategory();
    }
}
