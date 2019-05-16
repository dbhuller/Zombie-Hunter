/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject;

/**
 * Main class responsible for starting the game
 * @author Deep
 */
public class Main {
    
   /***
    * main class for the game
    * @param args 
    */
   public static void main(String[] args) {
       Game game = new Game("Adventure Game", 700, 700);
       game.start(); 
   } 
   
}
