package internship.rest.car.dto;

import internship.rest.car.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String type;
    private String color;

    public CarDTO(Car car) {
        this.id = car.getId();
        this.type = car.getType();
        this.color = car.getColor();
    }
}
