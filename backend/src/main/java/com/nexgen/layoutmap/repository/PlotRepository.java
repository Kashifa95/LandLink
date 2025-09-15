package com.nexgen.layoutmap.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nexgen.layoutmap.model.Plot;

public interface PlotRepository extends MongoRepository<Plot, String> {
//    List<Plot> findByLayoutId(String plotId);
}
