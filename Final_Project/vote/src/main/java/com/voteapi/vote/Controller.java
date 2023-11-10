package com.voteapi.vote;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
public class Controller {

    ConcurrentMap<String, Contact> contacts = new ConcurrentHashMap<>();

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }


    // @GetMapping("/{id}")
    // public Contact getContact(@PathVariable String id) {
    //     return contacts.get(id);
    // }

    // @GetMapping("/")
    // public List<Contact> getAllContacts() {
    //     return new ArrayList<Contact>(contacts.values());
    // }

    // @PostMapping("/")
    // public Contact addContact(@RequestBody Contact contact) {
    //     contacts.put(contact.getId(), contact);
    //     return contact;
    // }
    
    @Autowired
    private RedisService redisService;

    @GetMapping("/writeToRedis")
    public String writeToRedis(@RequestParam String key, @RequestParam String value) {
        redisService.writeToRedis(key, value);
        return "Value set in Redis";
    }
}