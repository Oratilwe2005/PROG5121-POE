/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

/**
 *
 * @author RC_Student_lab
 */
import org.json.JSONArray;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MessageStorage {
    private ArrayList<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void saveMessagesToJson(String filename) {
        JSONArray array = new JSONArray();
        for (Message m : messages) {
            array.put(m.toJSON());
        }
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(array.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTotalMessages() {
        return messages.size();
    }
}