package com.email.springEmail.emailSending;

public interface EmailSender {
 void emailSending(String to , String email ) throws Exception;
}
