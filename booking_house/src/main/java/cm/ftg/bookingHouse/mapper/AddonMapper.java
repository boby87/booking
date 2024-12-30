package cm.ftg.bookingHouse.mapper;

import cm.ftg.bookingHouse.entity.AddonType;
import org.springframework.beans.BeanUtils;

import cm.ftg.bookingHouse.dto.AddonDto;
import cm.ftg.bookingHouse.entity.Addon;

public final class AddonMapper {

    public static AddonDto mapToAddonDto(Addon addon) {
        return new AddonDto(addon.isState(), addon.getType().getDescription(), addon.getLength(), addon.getWidth(), addon.getImages());
    }

    public static Addon mapToAddon(AddonDto addonDto) {
        Addon addon = new Addon();
        BeanUtils.copyProperties(addonDto, addon);
        addon.setType(AddonType.valueOf(addonDto.type()));
        return addon;
    }
}
