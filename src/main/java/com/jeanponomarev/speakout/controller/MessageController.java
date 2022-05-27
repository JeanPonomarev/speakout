package com.jeanponomarev.speakout.controller;

import com.jeanponomarev.speakout.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    private int counter = 4;

    private final List<Map<String, String>> messages = new ArrayList<>() {{
        add(new HashMap<>() {
            {
                put("id", "1");
                put("text", "First message");
            }
        });
        add(new HashMap<>() {
            {
                put("id", "2");
                put("text", "Second message");
            }
        });
        add(new HashMap<>() {
            {
                put("id", "3");
                put("text", "Third message");
            }
        });
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getMessage(@PathVariable String id) {
        return getMessageFromDb(id);
    }

    @PostMapping
    public Map<String, String> createMessage(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);

        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> updateMessage(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> targetMessage = getMessageFromDb(id);

        targetMessage.putAll(message);
        targetMessage.put("id", id);

        return targetMessage;
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable String id) {
        Map<String, String> targetMessage = getMessageFromDb(id);
        messages.remove(targetMessage);
    }

    private Map<String, String> getMessageFromDb(String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
