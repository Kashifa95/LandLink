package com.nexgen.layoutmap.repository;

import com.nexgen.layoutmap.model.SVGLayout;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SVGLayoutRepository extends MongoRepository<SVGLayout, String> {
}