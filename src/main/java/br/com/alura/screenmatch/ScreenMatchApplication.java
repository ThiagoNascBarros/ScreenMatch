package br.com.alura.screenmatch;

import br.com.alura.screenmatch.Domain.Serie;
import br.com.alura.screenmatch.Service.ConsumerAPI;
import br.com.alura.screenmatch.Service.ConvertData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenMatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var address = "https://www.omdbapi.com/?t=gilmore+girls&apikey=abd3a916";
        ConsumerAPI api = new ConsumerAPI();
        ConvertData cvtData = new ConvertData();

        var response = ConsumerAPI.getDataOfAPI(address);
        var data = cvtData.convertData(response, Serie.class);

        System.out.println(data.toString());
    }

}
