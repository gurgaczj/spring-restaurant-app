package com.vandemos.menuservice.controller;

import com.vandemos.menuservice.dto.MenuEntryDto;
import com.vandemos.menuservice.util.MenuEntryUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuEntryUtils menuEntryUtils;

    public MenuController(MenuEntryUtils menuEntryUtils) {
        this.menuEntryUtils = menuEntryUtils;
    }

    @GetMapping({"/", ""})
    public ResponseEntity<List<MenuEntryDto>> getAllMenuEntries(){
        return ResponseEntity.ok(menuEntryUtils.getAllMenuEntries());
    }
}
