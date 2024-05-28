package t1.home.starter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class LogController {

    @GetMapping("test")
    @ResponseStatus(HttpStatus.OK)
    public Object testMethod(@RequestParam String name) {
        return Map.of("name", name, "test", "true");
    }
}
