package spittr.service;

import javax.mail.MessagingException;

import spittr.domain.Spittle;

public interface SpitterMailService {

    void sendSimpleSpittleEmail(String to, Spittle spittle);

    void sendSpittleEmailWithAttachment(String to, Spittle spittle)
            throws MessagingException;

}