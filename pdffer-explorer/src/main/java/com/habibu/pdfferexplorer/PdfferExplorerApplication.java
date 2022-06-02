package com.habibu.pdfferexplorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@SpringBootApplication
public class PdfferExplorerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdfferExplorerApplication.class, args);
    }

}
