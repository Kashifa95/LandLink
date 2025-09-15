package com.nexgen.layoutmap.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.nexgen.layoutmap.model.Plot;

public interface PlotService {
    Optional<Plot> getPlotById(String id);
    void importPlotsFromCSV(MultipartFile file) throws IOException;
}
