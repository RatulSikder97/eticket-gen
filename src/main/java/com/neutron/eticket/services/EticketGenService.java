package com.neutron.eticket.services;

import org.springframework.web.multipart.MultipartFile;

public interface EticketGenService {
    public String genTicket(MultipartFile jsonFile);
}
