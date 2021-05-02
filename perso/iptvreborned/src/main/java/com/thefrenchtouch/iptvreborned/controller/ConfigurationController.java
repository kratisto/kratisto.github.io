package com.thefrenchtouch.iptvreborned.controller;

import com.thefrenchtouch.iptvreborned.database.TemporaryDatabase;
import com.thefrenchtouch.iptvreborned.dto.request.ConfigurationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/configuration")
@AllArgsConstructor
public class ConfigurationController {
    private TemporaryDatabase temporaryDatabase;

    @PostMapping("/{mac}")
    public ResponseEntity submitM3UConfig(@PathVariable String mac, @RequestBody ConfigurationRequest configurationRequest){
        if(
             (configurationRequest.getM3uContent() == null || configurationRequest.getM3uContent().isBlank()) &&
             (configurationRequest.getM3uURL()==null || configurationRequest.getM3uURL().isBlank())) {
                    throw new InvalidM3U();
        }
        temporaryDatabase.save("config:"+mac,configurationRequest);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{mac}")
    public ConfigurationRequest submitM3UConfig(@PathVariable String mac){
        return temporaryDatabase.get("config:"+mac, ConfigurationRequest.class);

    }

}
