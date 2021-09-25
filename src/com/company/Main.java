package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "4167004";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/phonebook";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            System.out.println("Телефонная книга:\nДоступные функции:");
            while(true){
                System.out.println("1.Ввести все контакты");
                System.out.println("2.Добавить контакт");
                System.out.println("3.Удалить контакт");
                System.out.println("4.Изменить контакт контакт");
                System.out.println("5.Выйти из приложения");
                if(in.hasNextInt()) {
                    int functionNumber = in.nextInt();

                    if (functionNumber == 1) {
                        System.out.println("Контакты:");
                        Statement statement = connection.createStatement();
                        ResultSet result = statement.executeQuery("SELECT * FROM contacts");
                        while (result.next()) {
                            System.out.println(result.getInt("id") + " " + result.getString("name") + " "
                                    + result.getString("number"));
                        }
                        System.out.println();
                    }
                    if(functionNumber == 2){
                        System.out.println("Добавление контакта:");
                        PreparedStatement preparedStatement = connection.prepareStatement("insert into contacts (name, number) values (?,?);");
                        System.out.println("Введите имя");
                        in.nextLine();
                        String contactName = in.nextLine();
                        System.out.println("Введите номер телефона");
                        String contactNumber = in.nextLine();
                        preparedStatement.setString(1,contactName);
                        preparedStatement.setString(2,contactNumber);
                        preparedStatement.executeUpdate();
                    }
                    if (functionNumber == 5) {
                        System.exit(0);
                    }
                }
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        in.close();
    }
}
