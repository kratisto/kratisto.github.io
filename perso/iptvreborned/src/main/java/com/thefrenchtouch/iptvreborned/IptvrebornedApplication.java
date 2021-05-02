package com.thefrenchtouch.iptvreborned;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thefrenchtouch.iptvreborned.database.Channel;
import com.thefrenchtouch.iptvreborned.database.TemporaryDatabase;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class IptvrebornedApplication {

    public static void main(String[] args) {
        //SpringApplication.run(IptvrebornedApplication.class, args);
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON file to map
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL url = classloader.getResource("chaine.json");
            URL channels = classloader.getResource("channels.json");
            Map<String, Integer> map = mapper.readValue(url, Map.class);
            List<Channel> channelListLoaded = Arrays.asList(mapper.readValue(channels, Channel[].class));
            channelListLoaded = new ArrayList<>(channelListLoaded);
            List<Channel> channelList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(entry.getValue());
                if(channelListLoaded.stream().filter(channel -> channel.getTvgId().equals(entry.getKey())).collect(Collectors.toList()).size() > 0 ) {
                    channelListLoaded.stream().filter(channel -> channel.getTvgId().equals(entry.getKey())).forEach(channel -> channel.setTeleramaId(buffer.toString()));
                } else {

                    Channel currentChannel = new Channel();
                    currentChannel.setTvgId(entry.getKey());

                    currentChannel.setTeleramaId(buffer.toString());
                    channelListLoaded.add(currentChannel);

                }
            }
            mapper.writeValue(new File("channels.json"),channelListLoaded);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Bean
    @Scope("singleton")
    public TemporaryDatabase temporaryDatabase(){
        return new TemporaryDatabase();
    }
}
