package com.habibu.pdfferexplorer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;



import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.*;

import org.nekosoft.pdffer.PdfferProducerBean;

public class PdfferExplorerController {
    private static String htmlTemplateAsString(Resource resource) {
        try(Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)){
            return FileCopyUtils.copyToString(reader);
        }catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Value("classpath:com/")
    private Resource downloadHtmlTemplate;

    private ObjectMapper mapper;
    private PdfferProducerBean pdffer;

    public PdfferExplorerController(ObjectMapper mapper, PdfferProducerBean pdffer) {
        this.mapper = mapper;
        this.pdffer = pdffer;
    }

    @GetMapping(value="download", produces= TEXT_HTML_VALUE)
    @ResponseBody
    public String downloadForm(){
        return htmlTemplateAsString(downloadHtmlTemplate);
    }

    @PostMapping(value="download", produces = APPLICATION_PDF_VALUE)
    @ResponseBody
    public byte[] processDownloadForm(@RequestParam("pdfTemplate") String template,
                                      @RequestParam("pdfPaylod") String payload) throws JsonProcessingException {
        Map<String, Object> pdfData = mapper.readValue(payload, ((Class<Map<String, Object>>)(Class<?>)Map.class));
        return pdffer.generatePdfDocument(template, pdfData);
    }
}
