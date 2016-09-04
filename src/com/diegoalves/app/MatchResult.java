package com.diegoalves.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.diegoalves.domain.MatchInterface;
import com.diegoalves.domain.MurderInterface;
import com.diegoalves.domain.Player;
import com.diegoalves.domain.Weapon;

public class MatchResult {

	MatchInterface match;

	Map<Player, Integer> amountMurderPlayer = new HashMap<Player, Integer>();
	HashMap<Player, Integer> amountDeathPlayer = new HashMap<Player, Integer>();
	List<Player> majorMordersWithoutDieSeq = new ArrayList<Player>();
	List<Player> matchPlayers = new ArrayList<Player>();
	List<Player> playersKillFiveInMinute = new ArrayList<Player>();
	Map<Player, Integer> murdersPlayerWithoutDie = new HashMap<Player, Integer>();
	Map<Player, Date> playersKillFiveInMinuteSeq = new HashMap<Player, Date>();
	List<Player> playerWinWithoutDie = new ArrayList<Player>();
	Map<Player, Integer> orderClassification = new HashMap<Player, Integer>();
	static final long ONE_MINUTE_IN_MILIS = 60000;
	Player playerWinner;

	public MatchResult(MatchInterface match) {
		this.match = match;
		buildResult();

	}

	public void buildResult() {

		for (MurderInterface murder : match.getMurders()) {

			if (murder.kill().name() != "<WORLD>") {
				addMurderToPlayer(murder.kill());
			}

			addDeathToPlayer(murder.die());
		}
		playerMajorSeqWithoutDie();
		playerKillFiveInMinute();
		playerWinWithoutDie();
		playerWinner();
		playerWinnerBestWeapon();
	}

	public void playerWinWithoutDie() {
		Player playerNotDie;
		for (Player playerKill : amountMurderPlayer.keySet()) {
			playerNotDie = playerKill;
			boolean find = true;
			for (Player playerDied : amountDeathPlayer.keySet()) {
				if (playerKill.equals(playerDied)) {
					find = false;
				}
			}
			if (find) {
				playerNotDie.addAwardWinWithoutDie();
				playerWinWithoutDie.add(playerNotDie);
			}
		}
	}

	public void playerWinner() {
		int major = 0;
		for (Map.Entry<Player, Integer> player : amountMurderPlayer.entrySet()) {
			Integer amount = player.getValue();
			if (amount > major) {
				major = amount;
				playerWinner = player.getKey();
			}
		}
		setPlayerWinner(playerWinner);
	}

	public void addMurderToPlayer(Player p) {
		Integer amountM = 0;
		if (!p.name().equals("<WORLD>")) {
			if (this.amountMurderPlayer.containsKey(p))
				amountM = amountMurderPlayer.get(p);

			amountMurderPlayer.put(p, ++amountM);
		}

	}

	public void addDeathToPlayer(Player p) {
		Integer amountD = 0;

		if (this.amountDeathPlayer.containsKey(p))
			amountD = amountDeathPlayer.get(p);

		amountDeathPlayer.put(p, ++amountD);
	}

	public void playerOfMatch(Player p) {
		if (!this.matchPlayers.contains(p)) {
			matchPlayers.add(p);
		}
	}

	public void playerWinnerBestWeapon() {
		int weaponMostUsed = 0;
		Weapon bestWeapon = null;
		for (Weapon weapon : playerWinner.weaponsPlayer) {
			int usedWeapon = 0;
			for (MurderInterface murder : match.getMurders()) {
				if (murder.kill().equals(playerWinner) && weapon.name.equals(murder.weapon().name)) {
					usedWeapon++;
					if (usedWeapon > weaponMostUsed) {
						weaponMostUsed = usedWeapon;
						bestWeapon = weapon;
					}
				}
			}
		}
		playerWinner.setBestWeapon(bestWeapon);
	}

	public void playerKillFiveInMinute() {

		for (MurderInterface murder : match.getMurders()) {
			Player player = murder.kill();

			int murders = 0;
			Date date = murder.date();
			long time = date.getTime();
			Date addOneMinute = new Date(time + (ONE_MINUTE_IN_MILIS));

			Date occurrence = playersKillFiveInMinuteSeq.get(player);
			if (occurrence == null) {
				occurrence = new Date(time - (ONE_MINUTE_IN_MILIS));
			}

			if (occurrence.before(murder.date())) {
				for (MurderInterface murderM : match.getMurders()) {
					if (murder.kill().equals(murderM.kill()) && murderM.date().before(addOneMinute)
							&& (murderM.date().after(murder.date()) || murderM.date().equals(murder.date()))) {
						murders++;
						if (murders == 5) {
							System.out.println(
									"Sequence " + murders + " name " + murder.kill().name + " before " + addOneMinute);
							playersKillFiveInMinuteSeq.put(murder.kill(), addOneMinute);
							murders = 0;
						}

					} else
						murders = 0;
				}
			}

		}
	}

	public void playerMajorSeqWithoutDie() {
		List<Player> players = new ArrayList<Player>();
		Player jogadorKill;
		Integer majorSeq = 0;
		for (MurderInterface murder : match.getMurders()) {
			jogadorKill = murder.kill();
			if (!players.contains(jogadorKill) && !jogadorKill.name.equals("<WORLD>")) {
				players.add(jogadorKill);

				majorSeq = 0;
				Integer sequence = 0;

				for (MurderInterface murderM : match.getMurders()) {
					if (jogadorKill.equals(murderM.kill())) {
						sequence++;
						if (sequence > majorSeq) {
							majorSeq = sequence;
							jogadorKill.setMajorMurderWithoutDiegSeq(majorSeq);
						}
					}
					if (jogadorKill.equals(murderM.die())) {
						sequence = 0;
					}
				}

				murdersPlayerWithoutDie.put(jogadorKill, majorSeq);
			}
		}

	}

	public void setPlayerWinner(Player p) {
		playerWinner = p;
	}

	public Player getPlayerWinner() {
		return playerWinner;
	}

	public String getIdMatch() {
		return String.valueOf(match.idMatch());
	}

	public List<Player> getplayerKillFiveInMinute() {
		return this.playersKillFiveInMinute;
	}

	public Map<Player, Integer> getPlayerAmountMurder() {
		return amountMurderPlayer;
	}

	public Map<Player, Integer> getPlayerAmountDeath() {
		return amountDeathPlayer;
	}

	public Map<Player, Date> getPlayersKillFiveInMinuteSeq() {
		return playersKillFiveInMinuteSeq;
	}

	public List<Player> getPlayerWinWithoutDie() {
		return playerWinWithoutDie;
	}

	public Map<Player, Integer> getPlayerMurderWithoutDieSeq() {
		return murdersPlayerWithoutDie;
	}

	public Map<Player, Integer> getClassificationOrder() {
		return orderClassification;
	}

}
