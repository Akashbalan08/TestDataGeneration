package testdatagenerator;
import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Testdatamanagement {

    public static void main(String[] args) {
        String fileName = "testcases.csv";

        try (
            FileWriter outputfile = new FileWriter(fileName);
            CSVWriter writer = new CSVWriter(outputfile)
        ) 
        {
            String[] header = { "Scenario", "Email", "Password" };
            writer.writeNext(header);

            Faker faker = new Faker();
            writer.writeNext(new String[] {
                    "Valid login",
                    faker.internet().emailAddress(),
                    "Password123!"
            });

            writer.writeNext(new String[] {
                    "Invalid email",
                    "invalid.email.com",
                    "Password123!"
            });

            writer.writeNext(new String[] {
                    "Empty email",
                    "",
                    "Password123!"
            });

            writer.writeNext(new String[] {
                    "Empty password",
                    faker.internet().emailAddress(),
                    ""
            });

            writer.writeNext(new String[] {
                    "Short password",
                    faker.internet().emailAddress(),
                    "123"
            });

            System.out.println("Test data generated successfully and saved in " + fileName);

        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }
}
