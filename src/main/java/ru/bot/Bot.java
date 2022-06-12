package ru.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        // инициализация контекста
        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException ex) {
            ex.printStackTrace();
        }
    }

    //метод для обновления сообщения из чата
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/help":
                    sendMessage(message, "чем могу помочь?");
                    break;
                case "/settings":
                    sendMessage(message, "что будем настраивать?");
                    break;
            }
        }
    }

    //отправить ответ на сообщение
    private void sendMessage(Message message, String massageText) {
        SendMessage sendMessage = new SendMessage();

    }

    //получение имя бота
    public String getBotUsername() {
        return "PepparkakorBot";
    }

    //получения токена
    public String getBotToken() {
        return "";
    }
}
