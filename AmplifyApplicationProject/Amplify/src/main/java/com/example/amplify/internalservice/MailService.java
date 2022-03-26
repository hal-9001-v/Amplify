package com.example.amplify.internalservice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class MailService {

    @Async
    public void sendRecommendationsMail(String username, String mailAddress, List<String> songRecommendationNames, List<URI> recommendationURIs) throws URISyntaxException, IOException {

        RestTemplate resttemp = new RestTemplate();
        URI url = new URI("http://localhost:8080/email/recommendations");

        List<String> mailContent = new ArrayList<String>();

        String body = composeMail(username, mailAddress, songRecommendationNames, recommendationURIs,
                "recommendationsHeader.txt", "recommendationsFooter.txt");
        postMail(username, mailAddress, resttemp, url, mailContent, body);

    }

    @Async
    public void sendStatisticsMail(String username, String mailAddress, List<String> statisticsList, List<URI> statisticsURIs )throws URISyntaxException, IOException{


        RestTemplate resttemp = new RestTemplate();
        URI url = new URI("http://localhost:8080/email/estadisticas");

        List<String> mailContent = new ArrayList<String>();

        String body = composeMail(username, mailAddress, statisticsList, statisticsURIs,
                "statisticsHeader.txt", "satisticsFooter.txt");

        postMail(username, mailAddress, resttemp, url, mailContent, body);

    }

    private void postMail(String username, String mailAddress, RestTemplate resttemp, URI url, List<String> mailContent, String body) {
        String subject = "Recommendations for " + username;
        String addressee =mailAddress;
        mailContent.add(body);
        mailContent.add(subject);
        mailContent.add(addressee);

        HttpHeaders head = new HttpHeaders();
        head.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List> reqEntity = new HttpEntity<>(mailContent, head);

        resttemp.postForEntity(url, reqEntity, String.class);
    }


    private String composeMail(String username, String mailAddress, List<String> stringList, List<URI> uriList, String headerName, String endingName) throws IOException {

        String body = "";
        //read header file
        File file1 = new File(
                String.valueOf(getClass().getClassLoader().getResource(headerName)));
        BufferedReader br1
                = new BufferedReader(new FileReader(file1));
        //Add header to body
        while ((body += br1.readLine()) != null);

        //Add recommendation names
        for (int i = 0; i < stringList.size(); i++) {
            body += "\n" + i + 1 + " " + stringList.get(i) + " - " + uriList.get(i);
        }

        //Read end file
        File file2 = new File(
                String.valueOf(getClass().getClassLoader().getResource(endingName)));
        BufferedReader br2
                = new BufferedReader(new FileReader(file2));
        //Add end to body
        while ((body += br2.readLine()) != null);


        return body;
    }
}
