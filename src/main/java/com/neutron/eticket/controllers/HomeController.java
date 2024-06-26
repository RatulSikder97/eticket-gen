package com.neutron.eticket.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itextpdf.html2pdf.HtmlConverter;
import com.neutron.eticket.models.domains.Employee;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class HomeController {

    @PostMapping("/parseEmployee")
    public String parseEmployee(@RequestParam("file") MultipartFile jsonFile) {
        Gson gson = new Gson();
        String templatePath = "templates/eticket.html";
        String pdfDest = "target/output/output.pdf";  // Output PDF path

        try (InputStreamReader reader = new InputStreamReader(jsonFile.getInputStream(), StandardCharsets.UTF_8)) {
            // Read HTML template into a string
            String htmlTemplate = new String(Files.readAllBytes(getPath(templatePath)), StandardCharsets.UTF_8);

            // Parse JSON file into Employee object
            Employee employee = gson.fromJson(reader, Employee.class);

            // Fill template with data and convert HTML to PDF
            String renderedHtml = fillTemplateWithData(htmlTemplate, employee);

            // Ensure the output directory exists
            Path outputPath = Paths.get("target/output");
            if (!Files.exists(outputPath)) {
                Files.createDirectories(outputPath);
            }

            // Convert HTML to PDF and save it to output path
            try (FileOutputStream pdfStream = new FileOutputStream(pdfDest)) {
                HtmlConverter.convertToPdf(renderedHtml, pdfStream);
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
                .replace("{{name}}", employee.getPersonalDetails().getName())
                .replace("{{age}}", String.valueOf(employee.getPersonalDetails().getAge()))
                .replace("{{bloodGroup}}", employee.getPersonalDetails().getBloodGroup())
                .replace("{{address}}", employee.getPersonalDetails().getAddress());

        return htmlTemplate;
    }
}
