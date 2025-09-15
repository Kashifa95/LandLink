package com.nexgen.layoutmap.controller;

import com.nexgen.layoutmap.dto.PlotDTO;
import com.nexgen.layoutmap.model.Layout;
import com.nexgen.layoutmap.repository.LayoutRepository;
import com.nexgen.layoutmap.service.PlotService;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/plots")
public class PlotController {

    @Autowired
    private PlotService plotService;
    @Autowired
    private LayoutRepository layoutRepository;

    @GetMapping("/{id}")
    public ResponseEntity<PlotDTO> getPlot(@PathVariable String id) {
        return plotService.getPlotById(id)
            .map(plot -> {
                PlotDTO dto = new PlotDTO();
                dto.setId((String) plot.getplotId());
                dto.setStatus((String) plot.getStatus());
                dto.setDimensions((String) plot.getDimension());
                dto.setMetadata((Map<String, String>) plot.getMetadata());
                return ResponseEntity.ok(dto);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/import")
    public ResponseEntity<String> importCSV(@RequestParam("file") MultipartFile file) {
        try {
            plotService.importPlotsFromCSV(file);
            return ResponseEntity.ok("CSV imported successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to import CSV: " + e.getMessage());
        }
    }

    @GetMapping("/layout/{id}")
    public ResponseEntity<Layout> getLayout(@PathVariable String id) {
        return layoutRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

}