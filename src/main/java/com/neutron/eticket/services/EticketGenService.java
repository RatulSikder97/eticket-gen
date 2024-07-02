package com.neutron.eticket.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface EticketGenService {
    public ResponseEntity<InputStreamResource> genTicket(MultipartFile jsonFile);
}
