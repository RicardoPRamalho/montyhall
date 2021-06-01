package com.trustly.montyhall;

import com.trustly.montyhall.gameshow.exception.InvalidGameParameterException;
import com.trustly.montyhall.gameshow.GameShow;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.LogLevel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class responsible to run the application
 *
 * @author Ricardo Pereira Ramalho
 */
@Log
@SpringBootApplication
public class MontyhallApplication implements CommandLineRunner {

    private GameShow show;

    public MontyhallApplication(GameShow show) {
        this.show = show;
    }


    /**
     * Spring boot method to run the application
     *
     * @param args three {@link Integer} positive numbers, respectively the interactions, doors quantity and the prize quantity.
     */
    public static void main(String[] args) {
        SpringApplication.run(MontyhallApplication.class, args);
    }

    /**
     * Main method which executes the command line application
     *
     * @param args three {@link Integer} positive numbers, respectively the interactions, doors quantity and the prize quantity.
     */
    @Override
    public void run(String... args) throws Exception {
        try {
            String[] gameParams = args[0].split(",");
            Integer interactions = Integer.parseInt(gameParams[0]);
            Integer doorQuantity = Integer.parseInt(gameParams[1]);
            Integer prizeQuantity = Integer.parseInt(gameParams[2]);
            show.executeInteractions(interactions, doorQuantity, prizeQuantity);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warning(String.
                    format("Please, provide Interactions, Doors Quantity and Prize Quantity: %s",
                            String.join(" ", args)));
        } catch (NumberFormatException | InvalidGameParameterException e) {
            log.warning(String.
                    format("Please, provide valid numbers. " +
                                    "Interactions, Doors Quantity and Prize Quantity should be positive integers. Doors Quantity must be higher than Prize Quantity. " +
                                    "Interactions, Doors Quantity and Prize Quantity numbers provided: %s",
                            String.join(" ", args)));
        }
    }
}
