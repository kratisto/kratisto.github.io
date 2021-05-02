package com.thefrenchtouch.iptvreborned.dto.request;

import lombok.Data;

@Data
public class ConfigurationRequest {
    private String m3uURL;
    private String m3uContent;
}
