package syberry.api.banking.external.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import syberry.api.banking.controller.BankController;
import syberry.api.banking.external.UserStep;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@Component
public class ExchangeRatesBot extends TelegramLongPollingBot {


    private static BankController bankController;

    private static final HashMap<String, UserStep> userSteps = new HashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeRatesBot.class);

    private static final int USDNUM = 431;
    private static final int GBPNUM = 429;
    private static final int EURNUM = 451;
    private static final int JPYNUM = 508;

    private static final String START = "/start";
    private static final String BELARUS = "/belarus";
    private static final String NATIONAL = "/national";
    private static final String ALPHA = "/alpha";

    private static final String USD = "/usd";
    private static final String EUR = "/eur";
    private static final String JPY = "/jpy";
    private static final String GBP = "/gbp";
    private static final String STATE = "/state";

    private static final String HELP = "/help";
    private static final String NOW = "/now";
    private static final String DATE = "date";

    @Autowired
    public ExchangeRatesBot(@Value("${bot.token}") String botToken, BankController bankController) {
        super(botToken);
        this.bankController = bankController;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }


        var message = update.getMessage().getText();
        var chatId = update.getMessage().getChatId();

        String uid = String.valueOf(update.getMessage().getFrom().getId());
        if(message.split(" ")[0].equals(DATE)){
            try {
                onDate(message.split(" ")[1], chatId, uid);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        switch (message) {
            case START -> {
                String userName = update.getMessage().getChat().getUserName();
                startCommand(chatId, userName);
            }
            case NATIONAL -> national(chatId, uid);
            case BELARUS -> belarus(chatId, uid);
            case ALPHA -> alpha(chatId, uid);
            case USD -> usd(chatId, uid);
            case EUR -> eur(chatId, uid);
            case JPY -> jpy(chatId, uid);
            case GBP -> gbp(chatId, uid);
            case HELP -> helpCommand(chatId);
            case STATE -> state(chatId, uid);
            case NOW -> now(chatId, uid);
            default -> unknownCommand(chatId);
        }
    }

    @Override
    public String getBotUsername() {
        return "hackatonTeam2Bot";
    }

    private void startCommand(Long chatId, String userName) {
        var text = """
                Добро пожаловать в бот, %s!
                
                Справочная информация по боту
                                            
                Для получения текущих курсов валют воспользуйтесь командами:
                /usd - курс доллара
                /eur - курс евро
                /jpy - курс йены
                /gbp - курс фунта
                                            
                Для выбора банка воспользуйтесь командами:
                /national - Нацбанк
                /belarus - Беларусбанк
                /alpha - Альфа банк
                /now- получить курс на сегодня
                
                введи data дни-месяцы-годы для получения курса по дате
                
                Дополнительные команды:
                /help - получение справки
                """;
        var formattedText = String.format(text, userName);
        sendMessage(chatId, formattedText);
    }

    private void national(Long chatId, String uid) {
        String formattedText = "";
        if(!userSteps.containsKey(uid))
            userSteps.put(uid, new UserStep());
        userSteps.get(uid).setBank(UserStep.NATIONAL);
        System.out.println("National");
        formattedText = "Вы выбрали Нацбанк";

        sendMessage(chatId, formattedText);
    }

    private void alpha(Long chatId, String uid) {
        String formattedText;
        if(!userSteps.containsKey(uid))
            userSteps.put(uid, new UserStep());
        userSteps.get(uid).setBank(UserStep.ALPHA);
        System.out.println("Alpha");
        formattedText = "Вы выбрали Альфа банк";

        sendMessage(chatId, formattedText);
    }

    private void onDate(String input, Long chatId, String uid) throws ParseException {
        if(input == null){
            sendMessage(chatId, "Ошибка даты");
            return;
        }
        String formattedText = "";
        if(!userSteps.containsKey(uid))
            userSteps.put(uid, new UserStep());
        if(userSteps.get(uid).getBank() == UserStep.NOT_SET || userSteps.get(uid).getCurr() == UserStep.NOT_SET) {
            sendMessage(chatId, "Сначала заполни поля!");
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(input);
        } catch (ParseException e) {
            sendMessage(chatId, "Ошибка даты");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        formattedText += "Валюта: " + UserStep.currencyMap.get(userSteps.get(uid).getCurr());
        formattedText += ", банк " + UserStep.bankMap.get(userSteps.get(uid).getBank());
        formattedText += ", дата " + simpleDateFormat.format(date);
        formattedText += ", курс: " + calc(userSteps.get(uid).getCurr());
        sendMessage(chatId, formattedText);
    }

    private void belarus(Long chatId, String uid) {
        String formattedText = "";
        if(!userSteps.containsKey(uid))
            userSteps.put(uid, new UserStep());
        userSteps.get(uid).setBank(UserStep.BELARUS);
        System.out.println("Belarus");
        formattedText = "Вы выбрали Беларусбанк";

        sendMessage(chatId, formattedText);
    }

    private void usd(Long chatId, String uid){
        String formattedText = "";
        if(!userSteps.containsKey(uid))
            userSteps.put(uid, new UserStep());
        if(userSteps.get(uid).getBank() == UserStep.NOT_SET) {
            sendMessage(chatId, "Сначала выбери банк!");
            return;
        }
        userSteps.get(uid).setCurr(UserStep.USD);
        System.out.println("usd");
        formattedText = "Вы выбрали доллар";

        sendMessage(chatId, formattedText);
    }
    private void eur(Long chatId, String uid) {
        String formattedText = "";
        if(!userSteps.containsKey(uid))
            userSteps.put(uid, new UserStep());
        if(userSteps.get(uid).getBank() == UserStep.NOT_SET) {
            sendMessage(chatId, "Сначала выбери банк!");
            return;
        }
        userSteps.get(uid).setCurr(UserStep.EUR);
        System.out.println("eur");
        formattedText = "Вы выбрали евро";

        sendMessage(chatId, formattedText);
    }

    private void jpy(Long chatId, String uid) {
        String formattedText = "";
        if(!userSteps.containsKey(uid))
            userSteps.put(uid, new UserStep());
        if(userSteps.get(uid).getBank() == UserStep.NOT_SET) {
            sendMessage(chatId, "Сначала выбери банк!");
            return;
        }
        userSteps.get(uid).setCurr(UserStep.JPY);
        System.out.println("jpy");
        formattedText = "Вы выбрали йену";

        sendMessage(chatId, formattedText);
    }

    private void now(Long chatId, String uid){
        String formattedText = "";
        if(!userSteps.containsKey(uid))
            userSteps.put(uid, new UserStep());
        if(userSteps.get(uid).getBank() == UserStep.NOT_SET || userSteps.get(uid).getCurr() == UserStep.NOT_SET) {
            sendMessage(chatId, "Сначала заполни поля!");
            return;
        }
        formattedText += "Валюта: " + UserStep.currencyMap.get(userSteps.get(uid).getCurr());
        formattedText += ", банк " + UserStep.bankMap.get(userSteps.get(uid).getBank());
        formattedText += ", курс: " + calc(userSteps.get(uid).getCurr());
        sendMessage(chatId, formattedText);
    }

    private String calc(int name_cur){
        Random random = new Random();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        switch (name_cur) {
            case 0 -> {
                return "" + df.format(random.nextDouble() + 3.2);
            }
            case 1 -> {
                return "" + df.format(random.nextDouble() + 3.4);
            }
            case 2 -> {
                return "" + df.format(Math.abs(random.nextDouble() * 0.1));
            }
            case 3 -> {
                return "" + df.format(random.nextDouble() + 3.5);
            }
        }
        return "";
    }

    private void gbp(Long chatId, String uid) {
        String formattedText = "";
        if(!userSteps.containsKey(uid))
            userSteps.put(uid, new UserStep());
        userSteps.get(uid).setCurr(UserStep.GBP);
        System.out.println("gbp");
        formattedText = "Вы выбрали фунт";

        sendMessage(chatId, formattedText);
    }

    private void state(Long chatId, String uid){
        UserStep us = userSteps.get(uid);
        String s;
        if(us == null)
            s = "No data";
        else
            s = us.toString();
        sendMessage(chatId, s);
    }


    private void helpCommand(Long chatId) {
        var text = """
                Справочная информация по боту
                                
                Для получения текущих курсов валют воспользуйтесь командами:
                /usd - курс доллара
                /eur - курс евро
                /jpy - курс йены
                /gbp - курс фунта
                                
                Для выбора банка воспользуйтесь командами:
                /national - Нацбанк
                /belarus - Беларусбанк
                /alpha - Альфа банк
                /now - курс
                По умолчанию выбран доллар и Нацбанк.               
                """;
        sendMessage(chatId, text);
    }

    private void unknownCommand(Long chatId) {
        var text = "Не удалось распознать команду!";
        sendMessage(chatId, text);
    }

    private void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка отправки сообщения", e);
        }
    }
}
