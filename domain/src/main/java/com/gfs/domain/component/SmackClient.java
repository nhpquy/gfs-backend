package com.gfs.domain.component;

import com.gfs.domain.config.model.XmppConfig;
import com.gfs.domain.handler.AsyncHandler;
import com.gfs.domain.repository.inf.ConfigurationRepository;
import com.gfs.domain.utils.LoggerUtil;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SmackClient {
    private static final String TAG = SmackClient.class.getName();

    private XMPPConnection connection;
    private ChatManager chatManager;
    private HashMap<String, Chat> chatHashMap;
    private XmppConfig xmppConfig;

    @Value("${smack.client.enable:false}")
    private boolean enable;

    @Autowired
    ConfigurationRepository configurationRepository;

    public void init() {
        if (!enable) {
            LoggerUtil.w(TAG, "Smack Message Client was disabled");
            return;
        }
        try {
            System.out.println("Smack Message Client init");

            xmppConfig = configurationRepository.findByKey(XmppConfig.XMPP_CONFIG_KEY, XmppConfig.class);

            String host = xmppConfig.getHost();
            ConnectionConfiguration config = new ConnectionConfiguration(host, xmppConfig.getPort(), xmppConfig.getService());
            config.setSecurityMode(ConnectionConfiguration.SecurityMode.required);
            config.setSendPresence(true);
            config.setServiceName(xmppConfig.getService());
            config.setDebuggerEnabled(false);
            config.setSASLAuthenticationEnabled(true);

            connection = new XMPPConnection(config);
            connection.connect();

            if (connection.isConnected())
                System.out.println("Smack Message Client connected to server: " + host + ":" + xmppConfig.getPort());

            SASLAuthentication.supportSASLMechanism("CRAM-MD5", 0);

            String username = xmppConfig.getAdmin_account();
            String password = xmppConfig.getAdmin_password();
            connection.login(username, password, StringUtils.randomString(25));

            if (connection.isAuthenticated())
                System.out.println("Smack Message Client authenticated: username=" + username + "; password=XXXXXXXXXXXX");

            Presence presence = new Presence(Presence.Type.available, "Online, Update Priority", 24, Presence.Mode.available);
            connection.sendPacket(presence);

            chatManager = connection.getChatManager();

            if (chatHashMap != null)
                chatHashMap.clear();
            chatHashMap = new HashMap<>();

            System.out.println("Smack Message Client init successfully");

            connection.addConnectionListener(new ConnectionListener() {
                @Override
                public void connectionClosed() {
                    System.out.println("Smack message died");
                }

                @Override
                public void connectionClosedOnError(Exception e) {
                    System.out.println("Smack message died on error: " + e.getMessage());
                }

                @Override
                public void reconnectingIn(int i) {

                }

                @Override
                public void reconnectionSuccessful() {
                    System.out.println("Smack message reconnects successfully");
                }

                @Override
                public void reconnectionFailed(Exception e) {
                    System.out.println("Smack message reconnects failed cmnr");
                }
            });
        } catch (Exception e) {
            LoggerUtil.exception(TAG, e, true);
        }
    }

    public void refresh() {
        XmppConfig xmppConfig = configurationRepository.findByKey(XmppConfig.XMPP_CONFIG_KEY, XmppConfig.class);
        if (xmppConfig != null) {
            if (this.xmppConfig == null || !this.xmppConfig.hash().equals(xmppConfig.hash()))
                restart();
        }
    }

    public boolean isConnected() {
        return connection != null && connection.isConnected();
    }

    public boolean isAuthenticated() {
        return connection != null && connection.isAuthenticated();
    }

    private void disconnect() {
        if (connection != null && connection.isConnected())
            connection.disconnect();
        System.out.println("Smack Message Client disconnected");
    }

    private synchronized Chat getChat(String to) {
        to = to + "@" + xmppConfig.getService();
        Chat chat = null;
        if (chatHashMap.containsKey(to))
            chat = chatHashMap.get(to);
        if (chat == null) {
            chatHashMap.put(to, chatManager.createChat(to, null));
            chat = chatHashMap.get(to);
        }

        return chat;
    }

    public void sendMessageAsync(String to, String message) throws Exception {
        if (isAuthenticated()) {
            Chat chat = getChat(to);
            if (chat != null) {
                AsyncHandler.run(() -> {
                    chat.sendMessage(message);
                    return null;
                });
            }
        }
    }

    public void restart() {
        System.out.println("Smack Client restarting...");
        disconnect();
        init();
    }

    public void stop() {
        System.out.println("Smack Client stopping...");
        disconnect();
    }
}
