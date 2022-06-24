package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class Registration {
        Faker faker = new Faker(new Locale("ru"));

        public static UserInfo generateInfo() {
            return new UserInfo(generateCity(), generateName(), generatePhone());
        }

        public static String generateDate(int days) {
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String generateCity() {
            String[] cities = {"Москва", "Тула", "Рязань", "Белгород", "Липецк", "Тамбов", "Курск", "Воронеж", "Тверь"};
            return cities[new Random().nextInt(cities.length)];
        }

        public static String generateName() {
            return faker.name().lastName() + " " + faker.name().firstName();
        }

        public static String generatePhone() {
            return faker.phoneNumber().phoneNumber();
        }
    }
}