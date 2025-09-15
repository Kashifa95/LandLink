package com.nexgen.layoutmap.service;

import com.nexgen.layoutmap.service.impl.PlotServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.context.annotation.ComponentScan.Filter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PlotServiceImplTest.TestConfig.class)
class PlotServiceImplTest {

    @Autowired
    private PlotServiceImpl plotService;

    @Test
    void testImportPlotsFromCSV() throws Exception {
        String csvContent = "plotId,number,area,dimension,status,direction\r\n"
        		+ "L001,18,1200,A-101,30X40,sold,east-facing";
        MockMultipartFile file = new MockMultipartFile(
            "file",
            "test.csv",
            "text/csv",
            csvContent.getBytes()
        );

        assertDoesNotThrow(() -> plotService.importPlotsFromCSV(file));
    }

    @Configuration
    @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
    @ComponentScan(
        basePackages = "com.nexgen.layoutmap",
        excludeFilters = @Filter(
            type = FilterType.REGEX,
            pattern = "com\\.nexgen\\.layoutmap\\.config\\..*"
        )
    )
    static class TestConfig {
    }
}