package cm.ftg.bookingHouse.mapper;

import cm.ftg.bookingHouse.dto.AddonRequest;
import cm.ftg.bookingHouse.entity.AddonType;
import org.springframework.beans.BeanUtils;

import cm.ftg.bookingHouse.dto.AddonDto;
import cm.ftg.bookingHouse.entity.Addon;

import java.util.UUID;

public final class AddonMapper {

    public static AddonDto mapToAddonDto(Addon addon) {
        return new AddonDto(
                addon.isState(),
                addon.getType().getDescription(),
                addon.getLength(),
                addon.getWidth(),
                addon.getImages(),
                addon.getReference().toString()
        );
    }

    public static Addon mapToAddon(AddonDto addonDto) {
        Addon addon = new Addon();
        BeanUtils.copyProperties(addonDto, addon);
        addon.setType(AddonType.valueOf(addonDto.type()));
        return addon;
    }

    public static Addon mapFromRequestToAddon(AddonRequest addonRequest) {
        Addon addon = new Addon();
        BeanUtils.copyProperties(addonRequest, addon);
        addon.setType(AddonType.valueOf(addonRequest.type()));
        addon.setReference(UUID.randomUUID());
        return addon;
    }
}
