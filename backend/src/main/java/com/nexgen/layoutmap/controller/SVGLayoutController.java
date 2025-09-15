package com.nexgen.layoutmap.controller;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.nexgen.layoutmap.model.LayoutListEntry;
import com.nexgen.layoutmap.model.SVGLayout;
import com.nexgen.layoutmap.repository.LayoutListRepository;
import com.nexgen.layoutmap.repository.SVGLayoutRepository;
import com.nexgen.layoutmap.utility.SvgMetadataExtractor;
import com.nexgen.layoutmap.utility.SvgMetadataExtractor.SvgMetadata;

@RestController
@RequestMapping("/api/svg")
public class SVGLayoutController {

    @Autowired
    private SVGLayoutRepository svgRepo;
    @Autowired
    private LayoutListRepository layoutListRepo;

//    private final String uploadDir = "uploads/";

    @PostMapping("/upload")
    public ResponseEntity<?> uploadSVG(@RequestParam("file") MultipartFile file) {
        try {
            // Save file locally
        	UUID id = UUID.randomUUID();
            String fileName = id + "_" + file.getOriginalFilename();
            Path uploadDir = Paths.get("uploads");
            Files.createDirectories(uploadDir);
            Path filePath = uploadDir.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Extract metadata
            SvgMetadata metadata = SvgMetadataExtractor.extract(new FileInputStream(filePath.toFile()));

            // Create layout object
//            String layoutId = metadata.layoutId != null && !metadata.layoutId.isEmpty()
//                ? metadata.layoutId
//                : id.toString();
            String layoutId = id.toString();
            
            SVGLayout layout = new SVGLayout();
            layout.setLayoutId(layoutId);
            layout.setName(file.getOriginalFilename());
            layout.setOwner(metadata.owner);
            layout.setplotCount(metadata.plotCount);
            layout.setSvgFilePath("http://localhost:8080/svg/" + fileName);
            layout.setUploadedAt(LocalDateTime.now());

            svgRepo.save(layout);

            // Update layout list
            LayoutListEntry entry = new LayoutListEntry();
            entry.setLayoutId(layoutId);
            entry.setName(file.getOriginalFilename());
            entry.setSvgFilePath("http://localhost:8080/svg/" + fileName);

            layoutListRepo.save(entry);

            return ResponseEntity.ok("SVG uploaded and layout list updated");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Upload failed: " + e.getMessage());
        }
    }
    
    @GetMapping("/layouts")
    public ResponseEntity<List<LayoutListEntry>> getLayouts() {
        return ResponseEntity.ok(layoutListRepo.findAll());
    }
    
}