package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Countries[] countries = {
                new Countries("QATAR", 2),
                new Countries("EGYPT", 5),
                new Countries("INDONESIA", 4),
                new Countries("SINGAPORE", 1),
                new Countries("CANADA", 3),
                new Countries("ARGENTINA", 4)
        };

        PlacesToVisit[] placesToVisit = {
                new PlacesToVisit("Tesco", 1, "Malaysia", 5),
                new PlacesToVisit("Kajang Hospital", 2, "Malaysia", 3),
                new PlacesToVisit("Secret Recipe", 3, "Malaysia", 4),
                new PlacesToVisit("University Of Nottingham Malaysia", 4, "Malaysia", 2),
                new PlacesToVisit("Residential Compound", 5, "Malaysia", 1)
        };

        System.out.println("What is your full name?");
        String fullName = scanner.nextLine();

        System.out.println("What is your last name?");
        String lastName = scanner.next();

        System.out.println("What country did you come from?");
        String country = scanner.next().toUpperCase();

        System.out.println("What is the date of departure in this format dd/MM/yyyy?");
        String departureDate = scanner.next();
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(departureDate);

        IP incomingPassenger = new IP(fullName, lastName, country, departureDate);

        System.out.println("Incoming Passenger ");
        System.out.println("Full Name: " + incomingPassenger.fullName);
        System.out.println("Last Name: " + incomingPassenger.lastName);
        System.out.println("Arriving from: " + incomingPassenger.country);
        System.out.println("The date of arrival: " + departureDate + "\t" + d1);

        int countryRanking = getCountryRanking(countries, incomingPassenger);
        Quarantine(countryRanking);

        System.out.println("Choose the number of the location you want to visit (1-5)");
        displayPlacesToVisit(placesToVisit);
        int locationNumber = scanner.nextInt();
        int combinedRank = getCombinedRank(countryRanking, locationNumber, placesToVisit);
        RankCombined(combinedRank);
    }

    private static int getCountryRanking(Countries[] countries, IP incomingPassenger) {
        for (Countries country : countries) {
            if (country.country.equals(incomingPassenger.country)) {
                return country.ranking;
            }
        }
        return 0;
    }

    private static void Quarantine(int ranking) {
        System.out.println("Quarantine: " + ranking);
    }

    private static void displayPlacesToVisit(PlacesToVisit[] placesToVisit) {
        for (PlacesToVisit place : placesToVisit) {
            System.out.println(place.number + ". " + place.placeToVisit);
        }
    }

    private static int getCombinedRank(int countryRanking, int locationNumber, PlacesToVisit[] placesToVisit) {
        int placeRanking = placesToVisit[locationNumber - 1].ranking;
        return countryRanking + placeRanking;
    }

    private static void RankCombined(int sum) {
        if (sum <= 2) {
            System.out.println("Your overall danger rank is 1\nyou are allowed to travel.");
        } else if (sum >=3 && sum <= 4) {
            System.out.println("Your overall danger rank is 2\nyou are allowed to travel.");
        } else if (sum >= 5 && sum <= 6) {
            System.out.println("Your overall danger rank is 3\nyou are allowed to travel.");
        } else if (sum >= 7 && sum <= 8) {
            System.out.println("Your overall danger rank is 4\nyou are not allowed to travel.");
        } else if (sum >= 9 && sum <= 10) {
            System.out.println("Your overall danger rank is 5\nyou are not allowed to travel.");
        }
    }
}

class Countries {
    String country;
    int ranking;
    public Countries(String country, int ranking) {
        this.country = country;
        this.ranking = ranking;
    }
}
class PlacesToVisit {
    String placeToVisit;
    int number;
    String country;
    int ranking;
    public PlacesToVisit(String placeToVisit, int number, String country, int ranking) {
        this.placeToVisit = placeToVisit;
        this.number = number;
        this.country = country;
        this.ranking = ranking;
    }
}
class IP {
    String fullName;
    String lastName;
    String country;
    String departureDate;
    public IP(String fullName, String lastName, String country, String departureDate) {
        this.fullName = fullName;
        this.lastName = lastName;
        this.country = country;
        this.departureDate = departureDate;
    }
}

