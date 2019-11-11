/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ypolanco.bowlingpal.ui.cli.displayer;

/**
 *
 * @author ypolanco
 */
public class ErrorMessageDisplayer implements CLIDisplayer<Exception>{

    @Override
    public void display(Exception information) {
        System.out.println("An error occurred during application execurion: " + information.getMessage());
    }
    
}
