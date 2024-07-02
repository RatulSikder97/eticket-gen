package com.neutron.eticket.controllers;

import com.neutron.eticket.services.EticketGenServiceImpl;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class HomeController {

    public final EticketGenServiceImpl eticketGenService;

    public HomeController(EticketGenServiceImpl eticketGenService) {
        this.eticketGenService = eticketGenService;
    }


    @GetMapping("/download")
    public ResponseEntity<Resource> downloadPdf() {
        File file = new File("target/output/output.pdf");
        if (!file.exists()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        }

        FileSystemResource resource = new FileSystemResource(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=output.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @PostMapping("")
    public ResponseEntity<FileSystemResource> generateEticketPdf(@RequestParam("file") MultipartFile jsonFile) {
        this.eticketGenService.genTicket(jsonFile);

        File file = new File("target/output/output.pdf");
        if (!file.exists()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        }

        FileSystemResource resource = new FileSystemResource(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=output.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }


    private Path getPath(String resourcePath) throws IOException {
        return new ClassPathResource(resourcePath).getFile().toPath();
    }

}
