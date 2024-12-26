package cm.ftg.bookingHouse.converter;

import cm.ftg.bookingHouse.entity.AddonType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class AddonTypeConverter implements AttributeConverter<AddonType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AddonType addonType) {
        return addonType != null? addonType.getKey(): null;
    }

    @Override
    public AddonType convertToEntityAttribute(Integer dbData) {
        return Stream.of(AddonType.values()).filter(addonType -> addonType.getKey().equals(dbData)).findFirst().orElse(null);
    }
}
