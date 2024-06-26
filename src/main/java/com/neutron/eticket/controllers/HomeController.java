package com.neutron.eticket.controllers;


import com.google.gson.Gson;
import com.neutron.eticket.models.domains.Employee;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
public class HomeController {

    @GetMapping("/")
    public String  welcome() {
        return "Welcome To E-ticket Gen Project";
    }

    @PostMapping("/")
    public Employee purseEmployee(@RequestPart("file") MultipartFile jsonFile) {
        Gson gson = new Gson();

        try (InputStreamReader reader = new InputStreamReader(jsonFile.getInputStream())) {
            return gson.fromJson(reader, Employee.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle error properly in a real application
        }
    }

}
