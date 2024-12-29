package cm.ftg.bookingHouse.converter;

import java.util.stream.Stream;

import cm.ftg.bookingHouse.entity.AddonType;
import cm.ftg.bookingHouse.entity.TypeHouse;
import jakarta.persistence.AttributeConverter;

public class TypeHouseConverter implements AttributeConverter<TypeHouse, Integer>{

    @Override
    public Integer convertToDatabaseColumn(TypeHouse typeHouse) {
        // TODO Auto-generated method stub
        return typeHouse != null? typeHouse.getKey(): null;
    }

    @Override
    public TypeHouse convertToEntityAttribute(Integer dbData) {
        // TODO Auto-generated method stub
  return Stream.of(TypeHouse.values()).filter(addonType -> addonType.getKey().equals(dbData)).findFirst().orElse(null);
    }

    


}
