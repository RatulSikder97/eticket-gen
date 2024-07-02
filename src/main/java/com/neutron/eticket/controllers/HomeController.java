package com.neutron.eticket.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.neutron.eticket.models.domains.Employee;
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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



    @PostMapping("/parseEmployee")
    public String parseEmployee(@RequestParam("file") MultipartFile jsonFile) {
        Gson gson = new Gson();
        String templatePath = "templates/eticket.html";
        String pdfDest = "target/output/output.pdf";  // Output PDF path

        try (InputStreamReader reader = new InputStreamReader(jsonFile.getInputStream(), StandardCharsets.UTF_8)) {
            String htmlTemplate = new String(Files.readAllBytes(getPath(templatePath)), StandardCharsets.UTF_8);
            Employee employee = gson.fromJson(reader, Employee.class);
            String renderedHtml = fillTemplateWithData(htmlTemplate, employee);

            Path outputPath = Paths.get("target/output");
            if (!Files.exists(outputPath)) {
                Files.createDirectories(outputPath);
            }

            // Convert HTML to PDF and save it to output path
            try (FileOutputStream pdfStream = new FileOutputStream(pdfDest)) {
                PdfWriter pdfWriter = new PdfWriter(pdfStream);
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);

                PageSize pageSize = new PageSize(711.0F, 1850.0F);
                pdfDocument.setDefaultPageSize(pageSize);
                HtmlConverter.convertToPdf(new ByteArrayInputStream(renderedHtml.getBytes(StandardCharsets.UTF_8)), pdfDocument);

                System.out.println("Dynamic HTML template converted to PDF successfully!");
                return "PDF generated successfully!";
            } catch (IOException e) {
                System.out.println("Conversion failed: " + e.getMessage());
                return "Conversion failed: " + e.getMessage();
            }
        } catch (JsonSyntaxException | IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
            return "Error processing file: " + e.getMessage();
        }
    }


    private Path getPath(String resourcePath) throws IOException {
        return new ClassPathResource(resourcePath).getFile().toPath();
    }

    private String fillTemplateWithData(String htmlTemplate, Employee employee) {
        // Replace placeholders in HTML template with Employee data
        htmlTemplate = htmlTemplate.replace("{{id}}", String.valueOf(employee.getID()))
                .replace("{{personalDetails.name}}", employee.getPersonalDetails().getName())
                .replace("{{personalDetails.age}}", String.valueOf(employee.getPersonalDetails().getAge()))
                .replace("{{personalDetails.bloodGroup}}", employee.getPersonalDetails().getBloodGroup())
                .replace("{{personalDetails.address}}", employee.getPersonalDetails().getAddress());

        return htmlTemplate;
    }
}
