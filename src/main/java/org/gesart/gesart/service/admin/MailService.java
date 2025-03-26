package org.gesart.gesart.service.admin;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.gesart.gesart.domain.admin.User;
import org.gesart.gesart.dto.admin.EmailDto;
import org.gesart.gesart.repository.admin.AdminMailRepository;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * @author : <a href="mohamskab@outlook.fr">KABORE Mohamadi</a>
 * @version : 1.0
 * @since : 10/20/22 - 16:30
 **/
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@SuppressWarnings("ALL")
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MessageSource messageSource;
    private final SpringTemplateEngine templateEngine;
    private static final String PATTERN_FORMAT = "dd/MM/yyyy à HH:mm:ss";
    private final AdminMailRepository adminMailRepository;

    private static final String USER = "user";
  //  @Value("${sicomm.mail.base-url}")
    private String url;
    private static final String BASE_URL = "baseUrl";

    /**
     * sendind Email service.
     *
     * @param email
     * @throws MessagingException
     */
    public void sendHtmlMessage(final EmailDto email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(email.getProperties());
        //helper.setFrom(email.getFrom());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        String html = templateEngine.process(email.getTemplate(), context);
        helper.setText(html, true);

        log.info("Sending email: {} with html body: {}", email, html);
        javaMailSender.send(message);
    }

    /**
     * sending contact Message.
     *
     * @param commentaireDto
     * @throws MessagingException
     */
  /*  @Async
    public void sendContactMessage(final CommentaireDto commentaireDto) {
        EmailDto email = new EmailDto();
        Map<String, Object> properties = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());
        Optional<AdminMail> adminMail = adminMailRepository.findFirstByActifTrue();

        if (adminMail.isPresent()) {
            email.setTo(adminMail.get().getAdress());
            properties.put("admin_name", adminMail.get().getNom());
        } else {
            email.setTo("koumbiar@gmail.com");
        }
        email.setSubject("contact");
        email.setTemplate(Constant.CONTACT_MAIL_TEMPLATE);
        email.setFrom(commentaireDto.getEmail() != null ? commentaireDto.getEmail() : "");

        properties.put("nom", commentaireDto.getNom());
        properties.put("email", commentaireDto.getEmail() != null ? commentaireDto.getEmail() : "");
        properties.put("telephone", commentaireDto.getTelephone() != null ? commentaireDto.getTelephone() : "");
        properties.put("date", formatter.format(commentaireDto.getDatePost()));
        properties.put("message", commentaireDto.getMessage());
        email.setProperties(properties);
        try {
            sendHtmlMessage(email);
        } catch (MailException | MessagingException e) {
            log.warn("Email could not be sent to user '{}'", commentaireDto.getEmail(), e);
        }
    }
*/
    /**
     * sending users Mail.
     *
     * @param user
     * @param templateName
     * @param titleKey
     * @throws MessagingException
     */
    @Async
    public void sendEmailFromTemplate(final User user, final String templateName, final String titleKey) {
        if (user.getEmail() == null) {
            log.debug("Email doesn't exist for user '{}'", user.getLogin());
            return;
        }
        EmailDto email = new EmailDto();
        Map<String, Object> properties = new HashMap<>();
        //Email d'envoie
        email.setTo(user.getEmail());
        email.setSubject(titleKey);
        email.setTemplate(templateName);
        email.setFrom("");
        properties.put(USER, user);
        properties.put(BASE_URL, url);
        email.setProperties(properties);
        try {
            sendHtmlMessage(email);
        } catch (MailException | MessagingException e) {
            log.warn("Email could not be sent to user '{}'", user.getEmail(), e);
        }
    }


    /**
     * sending creation mail.
     *
     * @param user
     */

   /* @Async
    public void sendCreationEmail(final User user) {
        log.debug("Sending creation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, Constant.CREATION_USER_MAIL_TEMPLATE, "Activation de compte");
    }
*/

/**
     * seding password resset mail.
     *
     * @param user
     *//*

    @Async
    public void sendPasswordResetMail(final User user) {
        log.debug("Sending creation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, Constant.PASSWORD_RESET_MAIL_TEMPLATE, "Réinitialisation de mot de passe");
    }

    */
/**
     * sending activation mail.
     *
     * @param user
     *//*

    @Async
    public void sendActivationEmail(final User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, Constant.USER_ACTVATE_MAIL_TEMPLATE, "Activation de compte");
    }
*/

    /**
     * Envoie de facture d'abonnement.
     *
     * @param sendEmail
     * @param fileName
     * @param dearName
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
   /* @Async
    public void sendEmailWithFile(final String sendEmail, final String fileName, final String dearName)
            throws MessagingException, UnsupportedEncodingException {
        EmailDto email = new EmailDto();
        Map<String, Object> properties = new HashMap<>();
        email.setTo(sendEmail);
        email.setSubject("Abonnement à Kossnaana");
        email.setTemplate(Constant.ABONNEMENT_MAIL_TEMPLATE);
        email.setFrom("");
        properties.put("NOM", dearName);
        email.setProperties(properties);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        message.setSentDate(new Date());
        Context context = new Context();
        context.setVariables(email.getProperties());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        String html = templateEngine.process(email.getTemplate(), context);
        helper.setText(html, true);
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        // messageBodyPart.set();
        messageBodyPart.setText(html, "UTF-8", "html");
        // messageBodyPart.setText("Merci de recevoir en pièce jointe votre reçu d'abonnement à kossnaana.");
        // Create a multipart message for attachment
        Multipart multipart = new MimeMultipart();
        // Set text message part
        multipart.addBodyPart(messageBodyPart);
        // Attacher un fichier au mail
        messageBodyPart = new MimeBodyPart();
        File file = new File(Constant.PATH.toString() + "/" + fileName);
        // Resource data = new ClassPathResource(CLASS_PATH + "reçu_10.pdf", getClass().getClassLoader());
        messageBodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
        messageBodyPart.setFileName(fileName);
        message.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        javaMailSender.send(message);
    }
*/
    /**
     * Mail de reabonnement.
     *
     * @param dto
     * @throws MessagingException
     */
    /*@Async
    public void sendReabonnementEmail(final EmailDto dto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        message.setSentDate(new Date());
        Context context = new Context();
        context.setVariables(dto.getProperties());
        helper.setTo(dto.getTo());
        helper.setSubject(dto.getSubject());
        String html = templateEngine.process(dto.getTemplate(), context);
        helper.setText(html, true);
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(html, "UTF-8", "html");
        javaMailSender.send(message);
    }*/
}
