package com.neutron.eticket.services;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.neutron.eticket.models.domains.Employee;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class EticketGenServiceImpl implements EticketGenService{
    public static final String[] htmlTemplates = {
            "templates/eticket-1.html",
            "templates/eticket-2.html",
            "templates/eticket-3.html"
    };
    @Override
    public String genTicket(MultipartFile jsonFile) {

        Gson gson = new Gson();
        String pdfDest = "target/output/output.pdf";  // Output PDF path

        try (InputStreamReader reader = new InputStreamReader(jsonFile.getInputStream(), StandardCharsets.UTF_8)) {
            // 1. Parse Json data to Object
            Employee employee = gson.fromJson(reader, Employee.class);

            Path outputPath = Paths.get("target/output");
            if (!Files.exists(outputPath)) {
                Files.createDirectories(outputPath);
            }

            // 2. Read All Html Templates
            // 3. Map Values to htmls
            FileOutputStream pdfStream = new FileOutputStream(pdfDest);
            PdfWriter writer = new PdfWriter(pdfStream);
            PdfDocument pdf = new PdfDocument(writer);
            PdfMerger merger = new PdfMerger(pdf);
            for (int i = 0; i < 3; i++) {
                String html = htmlTemplates[i];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfDocument temp = new PdfDocument(new PdfWriter(baos));
                String htmlTemplate = new String(Files.readAllBytes(getPath(html)), StandardCharsets.UTF_8);

                // Data Mapping
                String renderedHtml = dataMapperByIdx(i, htmlTemplate);
                HtmlConverter.convertToPdf(new ByteArrayInputStream(renderedHtml.getBytes(StandardCharsets.UTF_8)), temp);
                temp = new PdfDocument(
                        new PdfReader(new ByteArrayInputStream(baos.toByteArray())));
                merger.merge(temp, 1, temp.getNumberOfPages());
                temp.close();
            }
            pdf.close();

            // 4. Create Html Document and Merge

            // 5. Render pdf
            return "sss";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Path getPath(String resourcePath) throws IOException {
        return new ClassPathResource(resourcePath).getFile().toPath();
    }

    private String dataMapperByIdx(int index, String htmlTemplate) {
        return switch (index) {
            case 0 -> fillDataOne(htmlTemplate);
            case 1 -> fillDataTwo(htmlTemplate);
            case 2 -> fillDataThree(htmlTemplate);
            default -> throw new IllegalArgumentException("Invalid index: " + index);
        };
    }


    private String fillDataOne(String htmlTemplate) {
        htmlTemplate = htmlTemplate.replace("{{data.value}}", String.valueOf(100));

        return htmlTemplate;
    }

    private String fillDataTwo(String htmlTemplate) {
        htmlTemplate = htmlTemplate.replace("{{data.value}}", String.valueOf(200));

        return htmlTemplate;
    }

    private String fillDataThree(String htmlTemplate) {
        htmlTemplate = htmlTemplate.replace("{{data.value}}", String.valueOf(1000));
        return htmlTemplate;
    }
}
