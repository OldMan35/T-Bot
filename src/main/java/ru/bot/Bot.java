package ru.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

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

    //метод для обработки сообщения из чата и ответа на него
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/start":
                    sendTextMessage(message, "Ну что же, начнём?");
                    break;
                case "/help":
                    sendTextMessage(message, "Чем могу помочь?");
                    break;
                case "/settings":
                    sendTextMessage(message, "Что будем настраивать?");
                    break;
                default:
                    sendTextMessage(message, "Сформулируй свой вопрос иначе");
                    break;
            }
        }
    }

    //метод для отправки ответа на сообщение
    private void sendTextMessage(Message message, String massageText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);//включение разметки
        sendMessage.setChatId(message.getChatId().toString()); //определяем в какой конкретно чат мы должны отправить ответ
        sendMessage.setReplyToMessageId(message.getMessageId()); //определяем на какое конкретно сообщение мы должны отправить ответ
        sendMessage.setText(massageText);//текст ответа
        //пытаемся отправить сообщение
        try {
            setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void setButtons(SendMessage sendMessage) {
        //создаем объект для клавиатуры
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        //установка параметров клавиатуры
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        //списко кнопок
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("/help"));
        keyboardRow.add(new KeyboardButton("/settings"));
        keyboardRowList.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    //получение имя бота
    public String getBotUsername() {
        return "PepparkakorBot";
    }

    //получения токена
    public String getBotToken() {
        return "5307479270:AAHzyRFhACfFH_ek2SaaVwcjQiDfKmnBDxs";
    }
}
