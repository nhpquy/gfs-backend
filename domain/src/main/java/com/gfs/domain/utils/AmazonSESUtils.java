package com.gfs.domain.utils;

import com.amazonaws.services.simpleemail.model.Destination;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.gfs.domain.config.ApplicationProperties;
import com.gfs.domain.constant.EmailConstant;
import com.gfs.domain.handler.AmazonSESHandler;
import com.gfs.domain.handler.AsyncHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AmazonSESUtils {
    private static final String TAG = AmazonSESUtils.class.getName();

    private static boolean sendEmailFromContent(JsonObject jsonObject, String templateContent) {
        try {
            if (StringUtils.isEmpty(templateContent)) {
                LoggerUtil.e(TAG, "Send email error because template name does not exist");
                return false;
            }

            String receiver = jsonObject.get("receiver").getAsString();
            String title = jsonObject.get("title").getAsString();
            LoggerUtil.i(TAG, "Send email via Amazon SES to  : " + receiver);

            Destination destination = new Destination();
            destination.withToAddresses(receiver);
            if (jsonObject.get("bcc") != null) {
                destination.withBccAddresses(jsonObject.get("bcc").getAsString());
            }
            if (jsonObject.has("cc")) {
                JsonElement jsonCc = jsonObject.get("cc");
                if (jsonCc instanceof JsonArray) {
                    List<String> cc = GsonSingleton.getInstance().fromJson(jsonCc.getAsJsonArray(), new TypeToken<List<String>>() {
                    }.getType());
                    destination.withCcAddresses(cc);
                } else {
                    destination.withCcAddresses(jsonCc.getAsString());
                }
            }

            JsonObject parameterList = jsonObject.get("parameterList").getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entrySet = parameterList.entrySet();
            for (Map.Entry<String, JsonElement> entry : entrySet) {
                templateContent = templateContent.replaceAll(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().getAsString());
            }
            AsyncHandler.run(new AmazonSESHandler(templateContent, title, destination));
            return true;
        } catch (Exception e) {
            LoggerUtil.exception(TAG, e, true);
        }
        return false;
    }

    private static boolean sendEmailFromTemplateFile(JsonObject jsonObject, String templateName) {

        try {
            String templateContent = FileUtils.getTemplate(templateName);

            if (StringUtils.isEmpty(templateContent)) {
                LoggerUtil.e(TAG, "Send email error because template file does not exist: " + templateName);
                return false;
            }
            return sendEmailFromContent(jsonObject, templateContent);
        } catch (Exception e) {
            LoggerUtil.exception(TAG, e, true);
        }
        return false;
    }

    public static boolean sendEmailVerifyEmailAddress(String title, String email, String time, String code) {
        try {
            JsonObject data = new JsonObject();
            data.addProperty("receiver", email);
            data.addProperty("title", title);
            JsonObject params = new JsonObject();
            params.addProperty("@link_image@", ApplicationProperties.getSesLogo());
            params.addProperty("@expire_at@", time);
            params.addProperty("@code@", code);
            data.add("parameterList", params);
            return sendEmailFromTemplateFile(data, "email_templates/" + EmailConstant.LANGUAGE + "/email_verify_email_address.html");
        } catch (Exception e) {
            LoggerUtil.exception(TAG, e, true);
        }
        return false;
    }

    public static boolean sendEmailVerifyLogin(String title, String email, String time, String code, String date, String device) {
        try {
            JsonObject data = new JsonObject();
            data.addProperty("receiver", email);
            data.addProperty("title", title);
            JsonObject params = new JsonObject();
            params.addProperty("@link_image@", ApplicationProperties.getSesLogo());
            params.addProperty("@expire_at@", time);
            params.addProperty("@code@", code);
            params.addProperty("@date@", date);
            params.addProperty("@device@", device);
            data.add("parameterList", params);
            return sendEmailFromTemplateFile(data, "email_templates/" + EmailConstant.LANGUAGE + "/email_verify_login.html");
        } catch (Exception e) {
            LoggerUtil.exception(TAG, e, true);
        }
        return false;
    }

    public static boolean sendEmailActivateAccount(String title, String email, String code, String expireDate) {
        try {
            JsonObject data = new JsonObject();
            data.addProperty("receiver", email);
            data.addProperty("title", title);
            JsonObject params = new JsonObject();
            params.addProperty("@link_image@", ApplicationProperties.getSesLogo());
            params.addProperty("@expire_at@", expireDate);
            params.addProperty("@code@", code);
            data.add("parameterList", params);
            return sendEmailFromTemplateFile(data, "email_templates/" + EmailConstant.LANGUAGE + "/email_verify_merchant.html");
        } catch (Exception e) {
            LoggerUtil.exception(TAG, e, true);
        }
        return false;
    }
}
