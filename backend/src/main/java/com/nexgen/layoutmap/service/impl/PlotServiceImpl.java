package com.nexgen.layoutmap.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nexgen.layoutmap.model.Layout;
import com.nexgen.layoutmap.model.Plot;
import com.nexgen.layoutmap.repository.LayoutRepository;
import com.nexgen.layoutmap.repository.PlotRepository;
import com.nexgen.layoutmap.service.PlotService;

@Service
public class PlotServiceImpl implements PlotService {

    @Autowired
    private PlotRepository plotRepository;
   @Autowired
    private LayoutRepository layoutRepository;

//
//    public Optional<Plot> getPlotById(String id) {
//        return plotRepository.findById(id);
//     nb\
//   }
    
    public void importPlotsFromCSV(MultipartFile file) throws IOException {
        List<Plot> plots = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] tokens = line.split(",");
                Plot plot = new Plot();
                plot.setPlotId(tokens[0].trim());
                plot.setNumber(tokens[1].trim());
                plot.setArea(Integer.parseInt(tokens[2].trim()));
                plot.setDimension(tokens[3].trim());
                plot.setStatus(tokens[4].trim());
                plot.setDirection(tokens[5].trim());

                plots.add(plot);
            }
        }

        // Create layout document
        Layout layout = new Layout();
        layout.setId("fe74cc57-ae0e-444c-ac85-b21bd6142c4f"); // You can make this dynamic
        layout.setName("Test Layout");
        layout.setSvgPath("/svg/fe74cc57-ae0e-444c-ac85-b21bd6142c4f_TestLayout.svg");
        layout.setPlots(plots);

        layoutRepository.save(layout);
    }

	@Override
	public Optional<Plot> getPlotById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
    }
