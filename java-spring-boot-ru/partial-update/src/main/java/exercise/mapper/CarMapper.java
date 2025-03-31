package exercise.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import exercise.dto.CarCreateDTO;
import exercise.dto.CarUpdateDTO;
import exercise.dto.CarDTO;
import exercise.model.Car;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

// BEGIN
@Mapper(
        uses = { JsonNullableMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CarMapper {
    public abstract void update(CarUpdateDTO data, @MappingTarget Car car);
    public abstract CarDTO map(Car car);
    public abstract Car map(CarCreateDTO data);
}
// END
