package com.neutron.eticket.configs;

import com.google.gson.Gson;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {
    public Gson gson() {
        return new Gson();
    }
}
