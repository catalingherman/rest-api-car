package internship.rest.car.service;

import internship.rest.car.dao.CarDAO;
import internship.rest.car.dto.CarDTO;
import internship.rest.car.exceptionshandler.CustomCarServiceException;
import internship.rest.car.model.Car;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@Data
@RequiredArgsConstructor
public class CarService {

    private final CarDAO carDAO;

    public List<CarDTO> getCars() throws CustomCarServiceException {
        try {
            List<CarDTO> carDTOS;
            List<Car> carsFromDB = carDAO.findAll();

            carDTOS = carsFromDB.stream()
                    .map(CarDTO::new)
                    .collect(Collectors.toList());

            return carDTOS;
        } catch (Exception e) {
            throw new CustomCarServiceException("Data Source issue, could not get the cars", INTERNAL_SERVER_ERROR);
        }
    }

    public void addCar(CarDTO carDTO) throws CustomCarServiceException {
        validateCar(carDTO);
        try {
            carDAO.save(new Car(carDTO));
        } catch (Exception e) {
            throw new CustomCarServiceException("Data Source issue, car could not be saved", INTERNAL_SERVER_ERROR);
        }
    }

    public void removeCar(Long id) throws CustomCarServiceException {
        try {
            carDAO.deleteById(id);
        } catch (Exception e) {
            throw new CustomCarServiceException("Data Source issue, could not delete car", INTERNAL_SERVER_ERROR);
        }
    }

    public CarDTO getOneCar(Long id) throws CustomCarServiceException {
        Optional<Car> optionalCar = carDAO.findById(id);
        if (optionalCar.isPresent()) {
            return new CarDTO(optionalCar.get());
        }
        throw new CustomCarServiceException("The car could not be found", INTERNAL_SERVER_ERROR);
    }

    public void updateCar(Long id, CarDTO carDTO) throws CustomCarServiceException {
        Optional<Car> optionalCar = carDAO.findById(id);
        validateCar(carDTO);
        Car toBeUpdated = carPresent(optionalCar);
        toBeUpdated.setType(carDTO.getType());
        toBeUpdated.setColor(carDTO.getColor());
        try {
            carDAO.save(toBeUpdated);
        } catch (Exception e) {
            throw new CustomCarServiceException("Data Source issue, car could not be updated", INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateCar(CarDTO carDTO) throws CustomCarServiceException {
        try {
            if (validateName(carDTO.getType())) {
                if (validateName(carDTO.getColor())) {
                    return true;
                } else {
                    throw new CustomCarServiceException("You are trying to set an invalid color", BAD_REQUEST);
                }
            }
            throw new CustomCarServiceException("You are trying to set an invalid type", BAD_REQUEST);
        } catch (NullPointerException npe) {
            throw new CustomCarServiceException("No null values here buddy", BAD_REQUEST);
        }
    }

    private Car carPresent(Optional<Car> optionalCar) throws CustomCarServiceException {
        if (optionalCar.isPresent()) {
            return optionalCar.get();
        }
        throw new CustomCarServiceException("Car not found", INTERNAL_SERVER_ERROR);
    }

    private boolean validateName(String str) {
        return !str.isEmpty() && !str.matches(".*\\d.*");
    }
}
