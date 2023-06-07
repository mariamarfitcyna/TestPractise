package Tasks;

import FakeBot.FakeBot;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.urfu.Core;
import ru.urfu.TimerBehavior;
import ru.urfu.User;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Task2 {

   FakeBot fakeBot = new FakeBot();
    Core core;

    User user;
    static final String wrongAnswer = "Неправильный ответ";

    static final String rightAnswer = "Правильный ответ";

    private void processCommands(List<String> commands){
        for (String command : commands){
            core.processCommand(user, command);
        }
    }



    @BeforeEach
    public void setUp(){
        fakeBot = new FakeBot();
        core = new Core(fakeBot);
        user = new User(1l);
    }

    /**
     * Тестирование повторения вопроса с ошибочным ответом после команды /next
     */

    @Test
    public void Task2RepetitionAlgorithmTest(){

        //допустим, в тесте 2 вопроса

        List<String> commands = List.of("/test", wrongAnswer, "/next", rightAnswer, "/next");

        processCommands(commands);

        String guestionWithWrongAnswer = "Вычислите степень: 10^2";

        Assertions.assertEquals(guestionWithWrongAnswer, fakeBot.getLastMessage());
    }

    /**
     * Тестирование случая, когда все ответы верны - тогда список вопросов для отработки должен быть пуст
     */

    @Test
    public void Task2EmptyQuestionListTest(){

        //допустим, в тесте 2 вопроса

        List<String> commands = List.of("/test", rightAnswer, "/next", rightAnswer, "/stop", "/repeat");

        processCommands(commands);

        String emptyWrongAnswers = "Список вопросов для отработки пуст. Пройдите тест!";


        Assertions.assertEquals(emptyWrongAnswers, fakeBot.getLastMessage());

    }

    /**
     * Тестирование того, что кнопки работают верно
     */

    @Test
    public void Task3ButtonsTestTest(){
        List<String> commands = List.of("/start", "Математика");
        processCommands(commands);
        String expected = "Вы выбрали предмет математика, хотите начать тест (/test)?";
        Assertions.assertEquals(expected, fakeBot.getLastMessage());
    }

    /**
     * Тестирование функционала смены предмета
     */
    @Test
    public void Task3ChangeSubjectTest(){
        List<String> commands = List.of("/start", "Математика", "/back", "Английский язык");
        processCommands(commands);
        String message = "Вы выбрали предмет английский язык, хотите начать тест (/test)?";
        Assertions.assertEquals(message, fakeBot.getLastMessage());
    }

    /**
     * Тестирование функционала подключения уведомлений
     */

    @Test
    public void Task4Test(){
        List<String> commands = List.of("/start", "настройки", "да");
        processCommands(commands);
        String message = "Уведомления успешно включены";
        Assertions.assertEquals(message, fakeBot.getLastMessage());
    }

    /**
     * Тестирование функционала меню
     */
    @Test
    public void Task4Test2(){
        List<String> commands = List.of("/start", "выбор предмета");
        processCommands(commands);
        String message = "Выберите предмет";
        Assertions.assertEquals(message, fakeBot.getLastMessage());
    }

    /**
     * Тестирование функционала отправки уведомления, если пользователь не был активен заданное кол-во времени
     */
    @Test
    public void Task4Test3(){
        TimerBehavior.setStandardDispatchTime(50);
        List<String> commands = List.of("/start", "настройки", "да");
        processCommands(commands);

        Timer timer = new Timer();
        timer.schedule(new Task(), 51);
        String message = "Вас давно не было видно. Хотите пройти тест?";
        Assertions.assertEquals(message, fakeBot.getLastMessage());
    }

    class Task extends TimerTask{

        @Override
        public void run() {

        }
    }


}
