package com.nexgen.layoutmap.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nexgen.layoutmap.model.Layout;

public interface LayoutRepository extends MongoRepository<Layout, String> {
	Optional<Layout> findById(String layoutId);

}