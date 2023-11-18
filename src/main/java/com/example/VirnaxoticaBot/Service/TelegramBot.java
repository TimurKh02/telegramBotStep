package com.example.VirnaxoticaBot.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.example.VirnaxoticaBot.config.BotConfig;

@Component
public class TelegramBot extends TelegramLongPollingBot {

	final BotConfig config;

	public TelegramBot(BotConfig config) {
		this.config = config;
	}

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return config.getBotName();
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return config.getToken();
	}

	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub

		// ПРОВЕРКА ЕСТЬ ЛИ ТЕКСТ
		if (update.hasMessage() && update.getMessage().hasText()) {
			String messageText = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();

			switch (messageText) {
			case "/start":
				startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
				replyKeyboardMarkup();
				break;

			default:
				sendMessage(chatId, "Sorry, coomand wasn't  recognized");

			}
		}

	}

	public ReplyKeyboardMarkup replyKeyboardMarkup() {
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
		// следующие три строчки могут менять значение аргументов
		replyKeyboardMarkup.setSelective(true);
		replyKeyboardMarkup.setResizeKeyboard(true);
		replyKeyboardMarkup.setOneTimeKeyboard(false);
		// добавляем "клавиатуру"
		replyKeyboardMarkup.setKeyboard(keyboardRows());

		return replyKeyboardMarkup;
	}

	public List<KeyboardRow> keyboardRows() {

		List<KeyboardRow> rows = new ArrayList<>();
		rows.add(new KeyboardRow(keyboardButtons()));
		rows.add(new KeyboardRow(keyboardButtonsTwo()));
		// создаем список рядов кнопок из списка кнопок
		
		return rows;
	}

	public List<KeyboardButton> keyboardButtons() {
		List<KeyboardButton> buttons = new ArrayList<>();
		buttons.add(new KeyboardButton("Расcчитать риски"));
		buttons.add(new KeyboardButton("Об Авторе"));

		KeyboardButton specificButton = buttons.get(0);
		KeyboardButton specificButtonTwo = buttons.get(1);
		
			

		return buttons;
	}

	public List<KeyboardButton> keyboardButtonsTwo() {
		List<KeyboardButton> buttonsTwo = new ArrayList<>();
		buttonsTwo.add(new KeyboardButton("Как пользоваться"));
		buttonsTwo.add(new KeyboardButton("Обратная связь"));
		return buttonsTwo;
	}

	private void startCommandReceived(long chatId, String name) {
		String answer = "Здравствуй, " + name + ", рад приветствовать тебя!";
		
		

		sendMessage(chatId, answer);

	}

	private void sendMessage(long chatId, String textToSend) {
		SendMessage message = new SendMessage();
		message.setChatId(String.valueOf(chatId));
		message.setText(textToSend);
		message.setReplyMarkup(replyKeyboardMarkup());
		try {
			execute(message);
		} catch (TelegramApiException e) {

		}
	}
}
