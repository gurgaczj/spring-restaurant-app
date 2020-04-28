package com.vandemos.menuservice.controller;

import com.vandemos.menuservice.dao.MenuEntry;
import com.vandemos.menuservice.dto.MenuEntryDto;
import com.vandemos.menuservice.service.MenuEntryService;
import com.vandemos.menuservice.util.MenuEntryUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuEntryService menuEntryService;
    private final MenuEntryUtil menuEntryUtil;

    public MenuController(MenuEntryService menuEntryService, MenuEntryUtil menuEntryUtil) {
        this.menuEntryService = menuEntryService;
        this.menuEntryUtil = menuEntryUtil;
    }

    @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MenuEntryDto>> getAllMenuEntries(){
        List<MenuEntryDto> result = new ArrayList<>();
        menuEntryService.findAll().forEach(menuEntry -> result.add(menuEntryUtil.toDto(menuEntry)));
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = {"/add", "/add/"},consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewMenuEntry(@RequestBody MenuEntryDto menuEntryDto){
        return ResponseEntity.ok(menuEntryUtil.toDto(menuEntryService.save(menuEntryUtil.toDao(menuEntryDto))));
    }
}
