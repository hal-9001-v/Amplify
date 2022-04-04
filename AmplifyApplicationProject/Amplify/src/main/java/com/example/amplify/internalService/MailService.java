package com.example.amplify.internalService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Service
@EnableAsync
public class MailService {

    @Async
    public void sendRecommendationsMail(String username, String mailAddress, List<String> songRecommendationNames, List<URI> recommendationURIs) throws URISyntaxException, IOException {

        RestTemplate resttemp = new RestTemplate();
        URI url = new URI("http://localhost:8080/email/recomendaciones");

        List<String> mailContent = new ArrayList<String>();

        String header =
                "Desde Amplify nos encanta cuidar de nuestros usuarios, así que nos hemos propuesto que descubráis música nueva cada semana :)\n" +
                "\n" +
                "¡Estas son tus recomendaciones semanales basadas en tus gustos!\n" +
                "\n";
        String footer =
                "\n" +
                "\n" +
                "¡Eso es todo por esta semana!\n" +
                "\n" +
                "¡La próxima más y mejor! :P\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "AmplifyDB desarrollada por:\n" +
                "    https://twitter.com/dexaxitu\n" +
                "    https://twitter.com/VicenteMAguile1\n" +
                "    https://twitter.com/Javiex73\n" +
                "\n" +
                "Todos los derechos reservados.";


       String body = composeMail(songRecommendationNames, recommendationURIs,
             header,  footer);
        String subject = "Recomendaciones para ";
        postMail(username, mailAddress,subject, resttemp, url, mailContent, body);

    }

    @Async
    public void sendStatisticsMail(String username, String mailAddress, List<String> statisticsList, List<URI> statisticsURIs )throws URISyntaxException, IOException{

        RestTemplate resttemp = new RestTemplate();
        URI url = new URI("http://localhost:8080/email/estadisticas");

        List<String> mailContent = new ArrayList<String>(3);

        String header = "¡Wow! ¡Qué gustazo tienes, fiera!\n" +
                "\n" +
                "¡Estas son tus estadísticas de uso de Amplify! ¡Esperamos que nos sigas visitando frecuentemente!\n" +
                "\n";
        String footer =
                "\n" +
                "\n" +
                "¡Madre mía, menudos hitazos!\n" +
                "\n" +
                "\n" +
                "\n" +
                "AmplifyDB desarrollada por:\n" +
                "    https://twitter.com/dexaxitu\n" +
                "    https://twitter.com/VicenteMAguile1\n" +
                "    https://twitter.com/Javiex73\n" +
                "\n" +
                "Todos los derechos reservados.";


        String body = composeMail(statisticsList, statisticsURIs,
                header,  footer);
        String subject = "Estadísticas de ";
        postMail(username, mailAddress,subject, resttemp, url, mailContent, body);

    }


    private void postMail(String username, String mailAddress, String subject, RestTemplate resttemp, URI url, List<String> mailContent, String body) {
        String sub = subject + username;
        String addressee =mailAddress;
        mailContent.add(body);
        mailContent.add(sub);
        mailContent.add(addressee);

        HttpHeaders head = new HttpHeaders();
        head.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List> reqEntity = new HttpEntity<>(mailContent, head);

        resttemp.postForEntity(url, reqEntity, String.class);
    }


    private String composeMail(List<String> stringList, List<URI> uriList, String headerText, String footerText) throws IOException {

        String body = "";
        //add header

        body+= headerText + "\n";

        //Add recommendation names
        for (int i = 0; i < stringList.size(); i++) {
            body += "\n" + (i + 1) + ". " + stringList.get(i) + " " + uriList.get(i);
        }

        //add footer
        body+= footerText+ "\n";

        return body;
    }
}
