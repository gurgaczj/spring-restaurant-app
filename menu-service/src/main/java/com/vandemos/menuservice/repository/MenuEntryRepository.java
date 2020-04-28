package com.vandemos.menuservice.repository;

import com.vandemos.menuservice.dao.MenuEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MenuEntryRepository extends MongoRepository<MenuEntry, String> {

    Optional<MenuEntry> findByName(String name);
}
