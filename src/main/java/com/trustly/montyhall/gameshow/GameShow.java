package com.trustly.montyhall.gameshow;

import com.trustly.montyhall.gameshow.exception.InvalidGameParameterException;
import com.trustly.montyhall.gameshow.model.Door;
import com.trustly.montyhall.gameshow.model.utils.DoorContentEnum;
import com.trustly.montyhall.gameshow.model.Person;
import com.trustly.montyhall.gameshow.model.utils.VictoryConditionEnum;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents the game show with the possible interactions according to the Monty Hall problem
 *
 * @author Ricardo Pereira Ramalho
 */
@Service
@Log
public class GameShow {


    /**
     * Sets up the game, stating how many doors, prizes and its locations in every interaction
     * @param doorQuantity {@link Integer} how many doors in this game
     * @param prizeQuantity  {@link Integer} how many prizes in this game
     * @return {@link List<Door>} with the doors containing prizes or nothing
     */
    private List<Door> gameSetup(Integer doorQuantity, Integer prizeQuantity) {
        List<Door> doors = new ArrayList<>();
        IntStream.range(0, doorQuantity).forEach(count -> doors.add(new Door(count)));
        Random gen = new Random();
        Set<Integer> chosenNumbers = new HashSet<>();
        IntStream.range(0, prizeQuantity).forEach(count -> {
            Integer number;
            do {
                number = gen.nextInt(doorQuantity);
            } while (chosenNumbers.contains(number));
            chosenNumbers.add(number);
            doors.get(number).setDoorContent(DoorContentEnum.MONEY);
        });
        return doors;
    }

    /**
     * Execute the game interactions according to the routine stated by the user and print its results
     * @param interactions {@link Integer} how many games will happen
     * @param doorQuantity {@link Integer} how many doors in this game
     * @param prizeQuantity  {@link Integer} how many prizes in this game
     * @throws InvalidGameParameterException if any invalid parameter is provided
     */
    public void executeInteractions(Integer interactions, Integer doorQuantity, Integer prizeQuantity) throws InvalidGameParameterException {
        if(interactions < 1 || doorQuantity < 3 || prizeQuantity < 1 || doorQuantity <= prizeQuantity){
            throw new InvalidGameParameterException("Invalid game parameter provided");
        }

        int switchWins = 0;
        int stayWins = 0;
        List<Door> doors;
        for (int plays = 0; plays < interactions; plays++) {
            doors = gameSetup(doorQuantity, prizeQuantity);
            VictoryConditionEnum victoryCondition = startGame(doors);
            if (victoryCondition.equals(VictoryConditionEnum.WIN_KEEPING_DOOR)) {
                stayWins += 1;
            } else if (victoryCondition.equals(VictoryConditionEnum.WIN_SWITCHING_DOOR)) {
                switchWins += 1;
            }

        }
        log.info(String.
                format("Switching wins %s times.", switchWins));
        log.info(String.
                format("Staying wins %s times.", stayWins));
    }

    /**
     * Run one interaction of the Monty Hall problem:
     * 1- The player chooses one door
     * 2- The host chooses another empty door
     * 3- Without opening its door, the player must decided if keeps their current door or get one that was not selected by the host
     * 4- It states if the player won if keep the door or get another random unselected door
     *
     * @param doors {@link List<Door>} the doors of the game
     * @return  {@link VictoryConditionEnum} states in which condition the player would won the game
     */
    private VictoryConditionEnum startGame(List<Door> doors) {
        Random gen = new Random();
        Person player = new Person(doors.get(gen.nextInt(doors.size())));
        List<Door> unselectedDoors = doors.stream().filter
                (getUnselectedEmptyDoors(player))
                .collect(Collectors.toList());
        Person host = new Person(unselectedDoors.get(gen.nextInt(unselectedDoors.size())));
        unselectedDoors = doors.stream()
                .filter(getUnselectedDoors(player, host)).collect(Collectors.toList());
        Door switchDoor = unselectedDoors.get(gen.nextInt(unselectedDoors.size()));
        if (player.getDoor().getDoorContent().equals(DoorContentEnum.MONEY)) {
            return VictoryConditionEnum.WIN_KEEPING_DOOR;
        } else if (switchDoor.getDoorContent().equals(DoorContentEnum.MONEY)) {
            return VictoryConditionEnum.WIN_SWITCHING_DOOR;
        }
        return VictoryConditionEnum.NOT_WINNING;

    }

    /**
     * Filter the doors which weren't chosen by the player
     * @param player
     * @return  {@link Predicate<Door>} states the filter for unselected doors by the player
     */
    private Predicate<Door> getUnselectedEmptyDoors(Person player) {
        return door -> door.getDoorContent().equals(DoorContentEnum.NOTHING)
                && !door.getDoorNumber().equals(player.getDoor().getDoorNumber());
    }

    /**
     * Filter the doors which weren't chosen by the player and the host
     * @param player {@link Person} states the player with its chosen door
     * @param host {@link Person} states the host with its chosen door
     * @return  {@link Predicate<Door>} states the filter for unselected doors
     */
    private Predicate<Door> getUnselectedDoors(Person player, Person host) {
        return door -> !door.getDoorNumber().equals(player.getDoor().getDoorNumber()) &&
                !door.getDoorNumber().equals(host.getDoor().getDoorNumber());
    }
}
