package test.java.maintest;

import com.diegoalves.domain.Weapon;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Diego Alves
 */
public class PlayerTest {

    private List<Weapon> weaponsPlayer = new ArrayList<>();

    @Test
    public void testList() {
        assertNotNull("List null?", weaponsPlayer);
    }

    @Test
    public void addWeaponByName() {
        String name = "k7";
        Weapon w = null;
        boolean find = false;
        for (Weapon weapon : weaponsPlayer) {
            if (name.equals(weapon.name())) {
                find = true;
            }
        }
        assertFalse("wait for false", find);
        if (!find) {
            w = new Weapon(name);
            setWeaponPlayer(w);
        }
        assertEquals("K7 added?", "k7", w.name);
    }

    public void setWeaponPlayer(Weapon weapon) {
        if (!this.weaponsPlayer.contains(weapon)) {
            this.weaponsPlayer.add(weapon);
        }
    }

}
