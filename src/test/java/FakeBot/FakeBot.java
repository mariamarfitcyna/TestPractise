package FakeBot;

import ru.urfu.IBot;

import java.util.ArrayList;
import java.util.List;


public class FakeBot implements IBot {



    private final List<String> messages = new ArrayList<>();

    public List<String> getMessages(){
        return messages;
    }

    @Override
    public void sendMessage(Long id, String message) {
        messages.add(message);
    }

    @Override
    public void sendMessageWithButtons(Long id, String message, String keyboardLayout) {

    }

    public String getLastMessage(){
        return messages.get(messages.size()-1);
    }
}
