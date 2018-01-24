package pinarayaz.ders;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramWebhookBot;

/**
 * Class that sends notifications
 * Created by PINAR on 24.1.2018.
 */
public class NotificationSender extends TelegramWebhookBot {
    private final String botToken;

    NotificationSender(String botToken) {
        this.botToken = botToken;
    }
    public void sendNotification(String n) {
        System.out.println(n);
    }

    public BotApiMethod onWebhookUpdateReceived(Update update) {
        return null;
    }

    public String getBotUsername() {
        return "PinarIsSoCoolLikeBot";
    }

    public String getBotToken() {
        return botToken;
    }

    public String getBotPath() {
        return null;
    }
}
