package com.nexgen.layoutmap.utility;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.*;

import com.nexgen.layoutmap.model.SVGLayout;

import javax.xml.parsers.*;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SvgMetadataExtractor {

    public static class SvgMetadata {
        public String layoutId;
        public String owner;
        public int plotCount;
    }

    public static SvgMetadata extract(InputStream svgStream) throws Exception {
        SvgMetadata metadata = new SvgMetadata();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(svgStream);

        Element svgRoot = doc.getDocumentElement();

        // Extract layoutId and owner safely
        metadata.layoutId = svgRoot.getAttribute("id");
        metadata.owner = svgRoot.getAttribute("owner");

        // Count <path> and <polygon> elements
        int pathCount = doc.getElementsByTagName("path").getLength();
        int polygonCount = doc.getElementsByTagName("polygon").getLength();
        metadata.plotCount = pathCount + polygonCount;
        
        if (svgRoot.getAttribute("id").isEmpty()) {
            System.out.println("SVG missing layoutId attribute");
        }
      

        return metadata;
    }
}