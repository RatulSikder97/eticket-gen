package com.neutron.eticket.services;

import com.google.gson.Gson;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.neutron.eticket.models.domains.ETicket;
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
            ETicket eticket = gson.fromJson(reader, ETicket.class);

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
                PageSize pageSize = new PageSize(1148.0F, 1625.0F);
                temp.setDefaultPageSize(pageSize);
                String htmlTemplate = new String(Files.readAllBytes(getPath(html)), StandardCharsets.UTF_8);

                // Data Mapping
                String renderedHtml = dataMapperByIdx(i, htmlTemplate, eticket);
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

    private String dataMapperByIdx(int index, String htmlTemplate, ETicket eticket) {
        return switch (index) {
            case 0 -> fillDataOne(htmlTemplate, eticket);
            case 1 -> fillDataTwo(htmlTemplate, eticket);
            case 2 -> fillDataThree(htmlTemplate, eticket);
            default -> throw new IllegalArgumentException("Invalid index: " + index);
        };
    }


    private String fillDataOne(String htmlTemplate, ETicket eticket) {
        htmlTemplate = htmlTemplate.replace("{{eticket.body.id}}", String.valueOf(eticket.getBody().getId()))
                .replace("{{eticket.dateTs}}", String.valueOf(eticket.getBody().getDatetTs()))
                .replace("{{eticket.timeTs}}", String.valueOf(eticket.getBody().getTimetTs()))
                .replace("isAm", eticket.getBody().getTimeTsAm())
                .replace("isPm", eticket.getBody().getTimeTsPm())
                .replace("{{eticket.driver.name}}", eticket.getBody().getDriver().getFullName())
                .replace("{{eticket.driver.address}}", String.valueOf(eticket.getBody().getDriver().getAddress().getAddress()))
                .replace("{{eticket.driver.lic}}", String.valueOf(eticket.getBody().getDriver().getDlNumber()))
                .replace("{{eticket.driver.city}}", String.valueOf(eticket.getBody().getDriver().getAddress().getCity()))
                .replace("{{eticket.driver.state}}", String.valueOf(eticket.getBody().getDriver().getAddress().getState()))
                .replace("{{eticket.driver.zipcode}}", String.valueOf(eticket.getBody().getDriver().getAddress().getZipCode()))
                .replace("{{eticket.driver.licNo}}", String.valueOf(eticket.getBody().getDriver().getDlNumber()))
                .replace("{{eticket.vehicle.lic}}", String.valueOf(eticket.getBody().getVehicle().getVin()))
                .replace("{{eticket.driver.stateDl}}", String.valueOf(eticket.getBody().getDriver().getStateDL()))
                .replace("{{eticket.driver.class}}", String.valueOf(eticket.getBody().getDriver().getDlClass()))
                .replace("isDriverComY", String.valueOf(eticket.getBody().getDriver().isComY()))
                .replace("isDriverComN", String.valueOf(eticket.getBody().getDriver().isComN()))
                .replace("{{eticket.driver.age}}", String.valueOf(eticket.getBody().getDriver().getAge()))
                .replace("{{eticket.driver.brithdates}}", String.valueOf(eticket.getBody().getDriver().getBirthString()))
                .replace("{{eticket.driver.sex}}", String.valueOf(eticket.getBody().getDriver().getSex()))
                .replace("{{eticket.driver.hair}}", String.valueOf(eticket.getBody().getDriver().getHairColor()))
                .replace("{{eticket.driver.eyes}}", String.valueOf(eticket.getBody().getDriver().getEyeColor()))
                .replace("{{eticket.driver.height}}", String.valueOf(eticket.getBody().getDriver().getHeight()))
                .replace("{{eticket.driver.weight}}", String.valueOf(eticket.getBody().getDriver().getWeight()))
                .replace("{{eticket.driver.race}}", String.valueOf(eticket.getBody().getDriver().getRace()))
                .replace("isCVM", String.valueOf(eticket.getBody().getVehicle().isCVM()))
                .replace("isHAZ", String.valueOf(eticket.getBody().getVehicle().isHAZ()))
                .replace("{{eticket.vehicle.vin}}", String.valueOf(eticket.getBody().getVehicle().getVin()))
                .replace("{{eticket.vehicle.state}}", String.valueOf(eticket.getBody().getVehicle().getState()))
                .replace("{{eticket.vehicle.exp}}", String.valueOf(eticket.getBody().getVehicle().getExpire()))
                .replace("{{eticket.vehicle.year}}", String.valueOf(eticket.getBody().getVehicle().getYear()))
                .replace("{{eticket.vehicle.make}}", String.valueOf(eticket.getBody().getVehicle().getMake()))
                .replace("{{eticket.vehicle.model}}", String.valueOf(eticket.getBody().getVehicle().getModel()))
                .replace("{{eticket.vehicle.type}}", String.valueOf(eticket.getBody().getVehicle().getType()))
                .replace("{{eticket.vehicle.color}}", String.valueOf(eticket.getBody().getVehicle().getColor()))
                .replace("{{eticket.incident.address}}", String.valueOf(eticket.getBody().getIncidentLocation().getAddress()))
                .replace("{{eticket.incident.city}}", String.valueOf(eticket.getBody().getIncidentLocation().getCity()))
                .replace("{{eticket.officer.name}}", String.valueOf(eticket.getBody().getOfficer().getFullName()))
                .replace("{{eticket.officer.id}}", String.valueOf(eticket.getBody().getOfficer().getId()))
                .replace("{{eticket.court}}", String.valueOf(eticket.getBody().getCourtName()))
        ;

        return htmlTemplate;
    }

    private String fillDataTwo(String htmlTemplate, ETicket eticket) {
        htmlTemplate = htmlTemplate.replace("{{data.value}}", String.valueOf(200));

        return htmlTemplate;
    }

    private String fillDataThree(String htmlTemplate, ETicket eticket) {
        htmlTemplate = htmlTemplate.replace("{{data.value}}", String.valueOf(1000));
        return htmlTemplate;
    }
}
