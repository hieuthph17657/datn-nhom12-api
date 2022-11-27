package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.GraphicsRequest;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="Graphics")

public class GraphicsEntity  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "card_onboard_id")
    private CardEntity card_onboard;

    @ManyToOne()
    @JoinColumn(name = "card_discrete_id")
    private CardEntity card_discrete;

    public void setData(GraphicsRequest request) {
        this.card_discrete = request.getCard_discrete();
        this.card_onboard = request.getCard_onboard();
    }

}
