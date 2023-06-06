package Tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.urfu.Core;
import ru.urfu.FakeBot;

import java.util.EnumMap;
import java.util.List;


public class Task2 {

    FakeBot fakeBot;

    Core core;
    static final String wrongAnswer = "Неправильный ответ";

    static final String rightAnswer = "Правильный ответ";


    @BeforeEach
    public void setUp(){
        fakeBot = new FakeBot();
        core = new Core(fakeBot);
    }

    /**
     * Тестирование повторения вопроса с ошибочным ответом после команды /next
     */

    @Test
    public void RepetitionAlgorithmTestEx1(){

        //допустим, в тесте 2 вопроса

        List<String> commands = List.of("/test", wrongAnswer, "/next", rightAnswer, "/next");
        String guestionWithWrongAnswer = "Вычислите степень: 10^2";
        Assertions.assertEquals(guestionWithWrongAnswer, fakeBot.getMessages().get(5));
    }

    /**
     * Тестирование списка вопросов для отработки, если все ответы были верны - тогда список должен быть пуст
     */

    @Test
    public void RepetitionAlgorithmTestEx2(){

        //допустим, в тесте 2 вопроса

        List<String> commands = List.of("/test", rightAnswer, "/next", rightAnswer, "/stop", "/repeat");

        String emptyWrongAnswers = "Список вопросов для отработки пуст. Пройдите тест!";

        int lastMessageIndex = fakeBot.getMessages().size();

        Assertions.assertEquals(emptyWrongAnswers, fakeBot.getMessages().get(lastMessageIndex-1));

    }

}
