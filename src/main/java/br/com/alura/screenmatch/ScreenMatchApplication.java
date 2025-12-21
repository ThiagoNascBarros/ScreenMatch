package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenMatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ScreenMatchApplication started");
        var address = "https://www.omdbapi.com/?t=gilmore+girls&apikey=abd3a916";
        ConsumerAPI api = new ConsumerAPI();
        var response = ConsumerAPI.getDataOfAPI(address);
    }

}
