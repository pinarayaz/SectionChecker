package pinarayaz.ders;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class that sends notifications over telegram
 *
 * @author ozgunawesome
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

    // private constructor --this class should not be instantiated more than once
    private NotificationSender(String botToken) {
        super();
        this.botToken = botToken;
    }

    /**
     * method for sending Telegram message to all currently subscribed clients
     *
     * @param message String to send over Telegram
     */
    public void sendNotification(String message) {
        for (Long chatId : chatIds) {
            this.sendMessage(chatId, message);
        }
    }

    // private method for sending a message to an individual client (identified by chat-ID)
    private void sendMessage(Long chatId, String text) {
        try {
            execute(new SendMessage().setChatId(chatId).setText(text));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    // abstract superclass methods

    /**
     * method that is executed when an update is received over Telegram
     * Currently does nothing except add the client to the notification list.
     *
     * @param update update object
     */
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();

        if (chatIds.add(chatId)) {
            sendMessage(chatId, "You are now added to the notification-list");
        } else {
            sendMessage(chatId, "You were already in the list");
        }
    }

    /**
     * Method that is executed when a bunch of updates are received all at once.
     * I think its used when long polling shits the bed for a while
     *
     * @param updates list of updates
     */
    public void onUpdatesReceived(List<Update> updates) {
        updates.forEach(this::onUpdateReceived);
    }

    /**
     * Method that returns the bot username as set when created.
     * Telegram API calls this method to identify the bot
     * @return bot username
     */
    public String getBotUsername() {
        return "PinarIsSoCoolLikeBot";
    }

    /**
     * Method that returns the private token for the bot.
     * Telegram API calls this method to authenticate the bot.
     * @return
     */
    public String getBotToken() {
        return botToken;
    }

    /**
     * Method that is executed by Telegram API when the bot is shutting down
     */
    public void onClosing() {
        sendNotification("The bot is now shutting down");
    }

}
