package ru.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException ex) {
            ex.printStackTrace();
        }
    }

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

    public String getBotUsername() {
        return "PepparkakorBot";
    }

    public String getBotToken() {
        return "5307479270:AAHzyRFhACfFH_ek2SaaVwcjQiDfKmnBDxs";
    }
}
