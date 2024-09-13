package org.example;
import org.example.config.DatabaseConnection;
import org.example.config.PostgreSQLConnectionTest;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        PostgreSQLConnectionTest x = new PostgreSQLConnectionTest();
        x.main();
    }

}