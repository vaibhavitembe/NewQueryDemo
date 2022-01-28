package com.zplus.controller;


import com.zplus.dto.SendMailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "mail")
public class MailController {
    @Autowired
    private com.zplus.BankAuction.configuration.SendMailComponent sendMailComponent;
    @PostMapping
    private ResponseEntity create(@RequestBody SendMailDto sendMailDto){
        String reply= "Dear "+ sendMailDto.getName()+",<br> We appreciate you for contacting us. Our Team will get back in touch with you soon!<br> Have a great day!";
        Boolean flag = false;
        String string = null;
        try {
            string = sendMailComponent.sendMail(sendMailDto.getTo(),sendMailDto.getMessage(),sendMailDto.getSubject());
            flag = true;
        } catch (MessagingException e) {
            e.printStackTrace();
            flag = false;
        }
        try {
            String res=sendMailComponent.sendMail(sendMailDto.getFrom(),reply,sendMailDto.getSubject());
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return new ResponseEntity(flag, HttpStatus.OK);
    }
}
