package dev.iury.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

public class Hello {

    private final long id;
    private final String content;

    public Hello(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
