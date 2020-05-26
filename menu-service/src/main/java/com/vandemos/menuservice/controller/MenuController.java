package com.vandemos.menuservice.controller;

import com.mongodb.MongoWriteException;
import com.vandemos.menuservice.dto.MenuEntryDto;
import com.vandemos.menuservice.model.ErrorMessage;
import com.vandemos.menuservice.service.MenuEntryService;
import com.vandemos.menuservice.util.MenuEntryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger("MenuController");

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

    @ControllerAdvice
    protected static class MenuControllerAdvice {
        private static final Logger logger = LoggerFactory.getLogger("MenuControllerAdvice");

        @ExceptionHandler({ MongoWriteException.class, DuplicateKeyException.class})
        protected ResponseEntity<?> handle(MongoWriteException exception, HttpServletRequest request){
            logger.error(exception.getMessage(), exception);
            HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
            ErrorMessage errorMessage = new ErrorMessage(badRequestStatus.value(),
                    badRequestStatus.name(),
                    "Menu entry with given name already exist.",
                    request.getRequestURI());
            return ResponseEntity
                    .badRequest()
                    .body(errorMessage);
        }
    }
}
