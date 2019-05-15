/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject;

/**
 * Launcher class responsible for running the game
 * @author Deep
 */
public class Launcher {
    
   /***
    * main class for the game
    * @param args 
    */
   public static void main(String[] args) {
       Game game = new Game("TermProject", 700, 700);
       game.start(); 
   } 
   
}
