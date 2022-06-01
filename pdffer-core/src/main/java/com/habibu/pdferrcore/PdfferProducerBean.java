package org.habibu.abdullahi;

import PDfferTemplate.DefaultPdfTemplate;
import org.springframework.stereotype.Component;
import template.PdfTemplate;

import java.util.Map;

@Component
public class PdfferProducerBean {
    public byte[] generatePdfDocument(String templateNae, Map<String, Object> data){
        PdfTemplate template = findTemplate(templateNae);
        if(!template.validate()) {
            throw new IllegalArgumentException("PDF template payload is not valid");
        }
        template.generate();
        return template.getPdfContent();
    }
    PdfTemplate findTemplate(String templateName){
        return new DefaultPdfTemplate();
    }
}
