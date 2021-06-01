package com.trustly.montyhall.gameshow.model;

import com.trustly.montyhall.gameshow.model.utils.DoorContentEnum;
import lombok.Data;

/**
 * It describes a single door with its number and content.
 *
 * @author Ricardo Pereira Ramalho
 */
@Data
public class Door {

    /**
     * Door number
     */
    private Integer doorNumber;
    /**
     * Door content
     */
    private DoorContentEnum doorContent = DoorContentEnum.NOTHING;

    /**
     * Constructor that states the door number
     *
     * @param doorNumber        {@link Integer} containing the door number
     */
    public Door(Integer doorNumber){
        this.doorNumber=doorNumber;
    }
}
