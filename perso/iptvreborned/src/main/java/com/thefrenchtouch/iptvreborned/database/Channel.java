package com.thefrenchtouch.iptvreborned.database;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Channel {
    private String tvgId;
    private String name;
    private String orangeId;
    private String teleramaId;

    public void setTeleramaId(Integer teleramaId){
        this.teleramaId = teleramaId.toString();
    }

    public void setTeleramaId(String teleramaId){
        this.teleramaId = teleramaId;
    }
}
