package com.vandemos.menuservice.util;

import com.vandemos.menuservice.dao.MenuEntry;
import com.vandemos.menuservice.dto.MenuEntryDto;

import java.util.List;
import java.util.function.Supplier;


public interface MenuEntryUtil {

    MenuEntryDto toDto(MenuEntry menuEntry);
    MenuEntry toDao(MenuEntryDto menuEntryDto);
}
