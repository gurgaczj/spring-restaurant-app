package com.vandemos.menuservice.service;

import com.vandemos.menuservice.dao.MenuEntry;

import java.util.List;

public interface MenuEntryService {

    MenuEntry findById(String id);
    List<MenuEntry> findAll();
}
