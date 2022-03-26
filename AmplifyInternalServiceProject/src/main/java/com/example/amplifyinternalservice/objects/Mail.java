package com.example.amplifyinternalservice.objects;

public class Mail {
    private String body;
    private String subject;
    private String addressee;

    public Mail(String b, String s, String a){
        body = b;
        subject = s;
        addressee = a;
    }

    public String getBody(){return body;}
    public String getSubject(){return subject;}
    public String getAddressee(){return addressee;}

}
