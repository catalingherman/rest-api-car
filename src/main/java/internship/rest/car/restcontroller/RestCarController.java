package internship.rest.car.restcontroller;

import internship.rest.car.dto.CarDTO;
import internship.rest.car.service.CarService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/car")
public class RestCarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<Object> cars() throws Exception {
        return new ResponseEntity<>(carService.getCars(), OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody CarDTO carDTO) throws Exception {
        carService.addCar(carDTO);
        return new ResponseEntity<>("All good, car saved", CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> remove(@PathVariable Long id) throws Exception {
        carService.removeCar(id);
        return new ResponseEntity<>("Car deleted", OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCarDetails(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(carService.getOneCar(id), OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCarDetails(@PathVariable Long id, @RequestBody CarDTO carDTO) throws Exception {
        carService.updateCar(id, carDTO);
        return new ResponseEntity<>("Car updated successfully", OK);
    }
}
