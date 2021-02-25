package by.kasyan.util;

import by.kasyan.processor.Handler;
import by.kasyan.processor.JpaUserRepository;
import by.kasyan.processor.State;
import by.kasyan.processor.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Component
public class StartHandler implements Handler {
    @Value("${bot.name}")
    private String botUsername;

    private final JpaUserRepository userRepository;

    public StartHandler(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<PartialBotApiMethod<? extends Serializable>> handle(User user, String message) {
        // Приветствуем пользователя
        SendMessage welcomeMessage = TelegramUtil.createMessageTemplate(user)
                .setText(String.format(
                        "Привет! Я бот, который поможет повторить пройденный материал по Java", botUsername
                ));
        // Просим назваться
        SendMessage registrationMessage = TelegramUtil.createMessageTemplate(user)
                .setText("Для начала представься");
        // Меняем пользователю статус на - "ожидание ввода имени"
        user.setBotState(State.ENTER_NAME);
        userRepository.save(user);

        return List.of(welcomeMessage, registrationMessage);
    }

    @Override
    public State operatedBotState() {
        return State.START;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return Collections.emptyList();
    }
}