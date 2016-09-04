package test.java.maintest;

import com.diegoalves.domain.MurderInterface;
import com.diegoalves.domain.Player;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Diego Alves
 */
public class MatchTest {

    public List<MurderInterface> murders;
    public List<Player> players;

    @Test
    public void TestListInitNull() {
        assertNull(murders);
        assertNull(players);
        murders = new ArrayList<>();
        players = new ArrayList<>();
        assertNotNull(murders);
        assertNotNull(players);
    }

    @Test
    public void addPlayerByName() {
        boolean find = false;
        String name = "test";
        Player p = null;
        players = new ArrayList<>();
        for (Player player : players) {
            if (name.equals(player.name())) {
                find = true;
            }
        }
        assertFalse(find);
        if (!find) {
            p = new Player(name);
            setPlayers(p);
        }
        assertNotEquals("test1", p.name);//Equals("K7 added?", "test", p.name);
    }
    
    public void setPlayers(Player player) {
		if (!this.players.contains(player)) {
			this.players.add(player);
		}
	}

}
