package com.vandemos.menuservice.service.impl;

import com.vandemos.menuservice.dao.MenuEntry;
import com.vandemos.menuservice.exception.MenuEntryNameExistException;
import com.vandemos.menuservice.exception.NotFoundException;
import com.vandemos.menuservice.repository.MenuEntryRepository;
import com.vandemos.menuservice.service.MenuEntryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public MenuEntry save(MenuEntry menuEntry) {
        try {
            findByName(menuEntry.getName());
            throw new MenuEntryNameExistException("Menu entry with name " + menuEntry.getName() + " already exist. " +
                    "Try editing existing one or give new entry unique name.");
        } catch (NotFoundException e){
            return menuEntryRepository.save(menuEntry);
        }
    }

    @Override
    public MenuEntry findByName(String name) {
        return menuEntryRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Menu entry with name " + name + " was not found."));
    }
}
