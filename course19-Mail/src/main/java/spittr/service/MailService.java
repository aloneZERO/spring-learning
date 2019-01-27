package spittr.service;

import spittr.domain.Spittle;

public interface MailService {

    void sendSimpleSpittleEmail(String to, Spittle spittle)
            throws Exception;

    void sendSpittleEmailWithAttachment(String to, Spittle spittle)
            throws Exception;

    void sendSpittleEmailWithRichText(String to, Spittle spittle)
            throws Exception;
}