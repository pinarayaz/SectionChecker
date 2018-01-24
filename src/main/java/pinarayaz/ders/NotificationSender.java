package pinarayaz.ders;

import com.sun.tools.corba.se.idl.constExpr.Not;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class that sends notifications
 * Created by PINAR on 24.1.2018.
 */
public class NotificationSender extends TelegramLongPollingBot {
    private final String botToken;
    private final Set<Long> chatIds = new HashSet<>();

    // singleton methods
    private static NotificationSender _SINGLETON;
    public static NotificationSender init(String botToken) {
        if (_SINGLETON == null) {
            _SINGLETON = new NotificationSender(botToken);
        }
        return _SINGLETON;
    }
    public static NotificationSender get() {
        if (_SINGLETON == null) {
            throw new RuntimeException("Bot not initialized..!");
        }
        return _SINGLETON;
    }
    // end singleton methods

    private NotificationSender(String botToken) {
        super();
        this.botToken = botToken;
    }

    void sendNotification(String message) {
        for (Long chatId : chatIds) {
            sendMessage(chatId, message);
        }
    }

    private void sendMessage(Long chatId, String text) {
        try {
            execute(new SendMessage().setChatId(chatId).setText(text));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    public void onUpdateReceived(Update update) {
        if (chatIds.add(update.getMessage().getChatId())) {
            sendMessage(update.getMessage().getChatId(), "You are now added to the notification-list");
        }
    }

    public void onUpdatesReceived(List<Update> updates) {
        updates.forEach(this::onUpdateReceived);
    }

    public String getBotUsername() {
        return "PinarIsSoCoolLikeBot";
    }

    public String getBotToken() {
        return botToken;
    }

    public void onClosing() {
        System.out.println("closing");
    }

}
