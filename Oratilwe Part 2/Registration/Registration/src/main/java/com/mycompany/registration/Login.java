/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

/**
 *
 * @author RC_Student_lab
 */
public class Login {
  // Registration storage variables
    String registeredUsername;
    String registeredPassword;
    String registeredCellNumber;
    String Name;
    String Surname;
    
    // Login attempt variables
    String loginUsername;
    String loginPassword;
    String loginCellNumber;
    
    
    public boolean checkUsername() {
        boolean isValid = false;
        if (registeredUsername.length() <= 5) {
            for (int i = 0; i < registeredUsername.length(); i++) {
                if ((int)registeredUsername.charAt(i) == 95) { 
                    isValid = true;
                }
            }
        }
        return isValid;
    }

   
    public boolean checkPasswordComplexity() {
        boolean hasCapital = false;  
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        if (registeredPassword.length() >= 8) {
            for (int i = 0; i < registeredPassword.length(); i++) {
                char ch = registeredPassword.charAt(i);
                if (ch >= 'A' && ch <= 'Z') {
                    hasCapital = true;
                }  
                if (ch >= '0' && ch <= '9') {
                    hasNumber = true;
                }          
                if ((ch >= 33 && ch <= 47) || (ch >= 58 && ch <= 64) || 
                    (ch >= 91 && ch <= 96) || (ch >= 123 && ch <= 126)) {
                    hasSpecial = true;
                }
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber() {
        boolean isValid = false;
        if (registeredCellNumber.length() <= 14) {
            if (registeredCellNumber.charAt(0) == '+') {
                isValid = true;
            }
        }
        return isValid;
    }
    
  
    public String registerUser() {
        if (checkUsername()) {
            System.out.println("Username successfully captured");
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your Username contains an underscore and is no more than 5 characters in length");
        }   
        
        if (checkPasswordComplexity()) {
            System.out.println("Password successfully captured");
        } else {
            System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character");
        }
        
        if (checkCellPhoneNumber()) {
            System.out.println("Cell phone number successfully added");
        } else {
            System.out.println("Cell phone number is incorrectly formatted or does not contain international code");
        }
        
        if (checkUsername() && checkPasswordComplexity() && checkCellPhoneNumber()) {
            return "The user has been registered successfully";
        } else {
            return "Registration failed. Please check requirements.";
        }
    }
    public boolean loginUser() {
        boolean isValid=false;
        
        if( loginUsername.equals(registeredUsername) && 
               loginPassword.equals(registeredPassword) && 
               loginCellNumber.equals(registeredCellNumber)){
            
        isValid=true;
        }
        return isValid;
    }
    
    
  
    public String returnLoginStatus() {
        if (loginUser()==true) {
            return "Successful login\nWelcome " + Name + " " + Surname + ", it is great to see you again.";
        } else {
            return "A failed login\nUsername, Password or cellphone number incorrect, please try again.";
        }}
    
}
