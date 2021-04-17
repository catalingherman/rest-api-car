package internship.rest.car.model;

import internship.rest.car.dto.CarDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Field is required")
    private String type;

    @NotNull(message = "Field is required")
    private String color;

    public Car(CarDTO carDTO) {
        this.type = carDTO.getType();
        this.color = carDTO.getColor();
    }
}
