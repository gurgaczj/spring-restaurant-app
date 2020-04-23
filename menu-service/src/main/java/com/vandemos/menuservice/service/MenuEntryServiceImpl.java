package com.vandemos.menuservice.service;

import com.vandemos.menuservice.dao.MenuEntry;
import com.vandemos.menuservice.exception.NotFoundException;
import com.vandemos.menuservice.repository.MenuEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuEntryServiceImpl implements MenuEntryService {

    private final MenuEntryRepository menuEntryRepository;

    public MenuEntryServiceImpl(MenuEntryRepository menuEntryRepository) {
        this.menuEntryRepository = menuEntryRepository;
    }

    @Override
    public MenuEntry findById(String id) {
        return menuEntryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu entry with id " + id + " was not found."));
    }

    @Override
    public List<MenuEntry> findAll() {
        return menuEntryRepository.findAll();
    }
}
