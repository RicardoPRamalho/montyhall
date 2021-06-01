package com.trustly.montyhall.gameshow.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * It describes a single person with its chosen door.
 *
 * @author Ricardo Pereira Ramalho
 */
@Data
@AllArgsConstructor
public class Person {

    /**
     * door chosen by this person
     */
    private Door door;

}
