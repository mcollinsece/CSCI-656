package com.voteapi.vote;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class Controller {

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }
    
    @Autowired
    private RedisService redisService;

    @GetMapping("/writeToRedis")
    public String writeToRedis(@RequestParam String key, @RequestParam String value) {
        redisService.writeToRedis(key, value);
        return "Value set in Redis";
    }
}