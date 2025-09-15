package com.nexgen.layoutmap.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nexgen.layoutmap.model.LayoutListEntry;

public interface LayoutListRepository extends MongoRepository<LayoutListEntry, String> {
   }