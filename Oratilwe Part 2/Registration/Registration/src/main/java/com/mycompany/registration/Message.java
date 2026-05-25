package com.mycompany.registration;

import org.json.JSONObject; // Corrected: Added this import

public class Message {
    private static int messageCounter = 0;
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private boolean sent;
    private boolean stored;

    public Message(String recipient, String messageText) {
        messageCounter++;
        this.messageID = "m" + String.format("%09d", messageCounter);
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash(messageCounter, messageText);
        this.sent = false;
        this.stored = false;
    }

    public static boolean isValidRecipient(String recipient) {
        // According to the test assertion, a valid recipient is a "cell phone number successfully captured."
        // And "international code." and "length <= 10" from the code.
        // Assuming "international code" means starting with '+'.
        // Also assuming "cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again." means it must start with '+' and be <= 10 chars.
        return recipient != null && recipient.startsWith("+") && recipient.length() <= 13;
    }

    public static boolean isValidMessage(String message) {
        // According to the test assertion, "The message should not be more than 250 Characters"
        return message != null && message.length() <= 250;
    }

    private String createMessageHash(int msgNumber, String message) {
        String idPart = this.messageID.substring(1, 3);
        String[] words = message.trim().split("\\s+");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length - 1] : first;
        return (idPart + ":" + msgNumber + first + last).toUpperCase();
    }

    public void send() {
        this.sent = true;
        this.stored = false;
    }

    public void store() {
        this.stored = true;
        this.sent = false;
    }

    public void disregard() {
        this.sent = false;
        this.stored = false;
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("MessageID", messageID);
        obj.put("Recipient", recipient);
        obj.put("Message", messageText);
        obj.put("Hash", messageHash);
        obj.put("Sent", sent);
        obj.put("Stored", stored);
        return obj;
    }

    public String getMessageDetails() {
        return "MessageID: " + messageID + "\n" +
                "Message Hash: " + messageHash + "\n" +
                "Recipient: " + recipient + "\n" +
                "Message: " + messageText;
    }

    // Getters for testing purposes
    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public boolean isSent() {
        return sent;
    }

    public boolean isStored() {
        return stored;
    }

    // Reset messageCounter for repeatable tests if necessary - Corrected: Added this method
    public static void resetMessageCounter() {
        messageCounter = 0;
    }
}