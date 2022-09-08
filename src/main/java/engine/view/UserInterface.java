package engine.view;

import engine.controller.WeatherController;
import enums.LocationPropertiesNames;

import java.util.Map;
import java.util.Scanner;

public class UserInterface implements IUserInterface{

    Scanner scanner;

    @Override
    public void showUI() {

        scanner = new Scanner(System.in);
        WeatherController weatherController = new WeatherController();

        System.out.println("-------------");
        System.out.println("Welcome to the weather forecast service");
        System.out.println("Enter Exit to quit the program\n");

        while (true) {
            System.out.println(weatherController.onUserInput(inputCityLocation(scanner)));
        }

    }

    private Map<LocationPropertiesNames, String> inputCityLocation(Scanner scanner){
        System.out.println("Please input town name\n");
        String cityName = inputInterceptor(scanner);
        System.out.println("Enter country for this town\n");
        String countryName = inputInterceptor(scanner);
        return Map.of(
                LocationPropertiesNames.CITY_NAME, cityName,
                LocationPropertiesNames.CITY_COUNTRY, countryName);
    }

    private String inputInterceptor(Scanner scanner) {
        String command = scanner.nextLine();
        while (inputValidator(command)) {
            command = scanner.nextLine();
        }
        checkExitCommand(command);
        return command;
    }

    private void checkExitCommand(String string) {
        if(string.equalsIgnoreCase("exit") || string.equalsIgnoreCase("выход")) {
        System.out.println("Thanks for use our forecast system");
        scanner.close();
        System.exit(0);}
    }

    private boolean inputValidator(String command) {
        if (command == null || command.length() == 0) {
            System.out.println("Input mismatch, try again");
            return true;
        }
        return false;
    }

}
