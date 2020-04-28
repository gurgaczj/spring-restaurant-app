package com.vandemos.menuservice.service;

import com.vandemos.menuservice.dao.MenuEntry;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface MenuEntryService {

    MenuEntry findById(String id);
    List<MenuEntry> findAll();
    MenuEntry save(MenuEntry menuEntry);
    MenuEntry findByName(String name);
}
