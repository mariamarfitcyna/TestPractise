import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.urfu.Core;
import ru.urfu.FakeBot;
import ru.urfu.User;

import java.util.List;

public class Testing {


    @Test
    public void T(){
        FakeBot fakeBot = new FakeBot();
        Core core = new Core(fakeBot);
        List<String> commands = List.of("/test", "неправильный ответ",
                "/stop", "/repeat");

        for (String command : commands){
            core.processCommand(user, command);
        }
        String guestion = "Вычислите степень: 10^2";
        Assert.assertEquals(guestion, fakeBot.getMessages().get(0));
        Assert.assertEquals(guestion, fakeBot.getMessages().get(3));
    }
}
