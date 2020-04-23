package com.vandemos.menuservice.repository;

import com.vandemos.menuservice.dao.MenuEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuEntryRepository extends MongoRepository<MenuEntry, String> {
}
