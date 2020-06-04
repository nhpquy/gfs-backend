package com.gfs.domain.handler;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClient;
import com.amazonaws.services.simpleemail.model.*;
import com.google.gson.Gson;
import com.gfs.domain.config.ApplicationProperties;
import com.gfs.domain.utils.LoggerUtil;

public class AmazonSESHandler implements Runnable {
    private String templateContent;
    private String title;
    private Destination destination;

    public AmazonSESHandler() {
    }

    public AmazonSESHandler(String templateContent, String title, Destination destination) {
        this.templateContent = templateContent;
        this.title = title;
        this.destination = destination;
    }

    @Override
    public void run() {
        try {
            AWSCredentials awsCredentials = new BasicAWSCredentials(
                    ApplicationProperties.getSesAccessKey(),
                    ApplicationProperties.getSesSecret());
            AmazonSimpleEmailService client = new AmazonSimpleEmailServiceAsyncClient(awsCredentials);
            client.setRegion(Region.getRegion(Regions.US_WEST_2));
            Message message = new Message();
            message.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(templateContent)));
            message.withSubject(new Content().withCharset("UTF-8").withData(title));
            String source = "\"" + ApplicationProperties.getSesCompanyName() + "\"<"
                    + ApplicationProperties.getSesCompanyEmail() + ">";
            SendEmailRequest request = new SendEmailRequest().withDestination(destination).withMessage(message)
                    .withSource(source);
            LoggerUtil.i(toString(), "Send email via Amazon ses result : " + new Gson().toJson(client.sendEmail(request)));
        } catch (Exception e) {
            LoggerUtil.exception(toString(), e, true);
        }
    }

    @Override
    public String toString() {
        return "Send email: " + title + " to " + destination.getToAddresses().get(0);
    }
}
