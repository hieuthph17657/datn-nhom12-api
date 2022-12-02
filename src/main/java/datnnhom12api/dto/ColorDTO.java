package datnnhom12api.dto;


import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ColorDTO implements Serializable {
    private Long id;

    private String name;
}
