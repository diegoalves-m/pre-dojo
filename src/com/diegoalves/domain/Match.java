package com.diegoalves.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Match implements MatchInterface {

	public Long idMatch;
	public Date dateStartMatch;
	public Date dateFinishMatch;
	public List<MurderInterface> murders = new ArrayList<MurderInterface>();
	public List<Player> players = new ArrayList<Player>();

	public Match(Long idMatch, Date dateStartMatch, Date dateFinishMatch) {
		this.idMatch = idMatch;
		this.dateStartMatch = dateStartMatch;
		this.dateFinishMatch = dateFinishMatch;
		murders = new ArrayList<>();
	}

	public Match(Long idMatch, Date dateStartMatch) {
		this.idMatch = idMatch;
		this.dateStartMatch = dateStartMatch;
		murders = new ArrayList<>();
	}

	public Match() {

	}

	public Long idMatch() {
		return idMatch;
	}

	public Date dateStartMatch() {
		return dateStartMatch;
	}

	public Date dateFinishMatch() {
		return dateFinishMatch;
	}

	public void addDateFinishMatch(Date dateFinishMatch) {
		this.dateFinishMatch = dateFinishMatch;
	}

	@Override
	public List<MurderInterface> getMurders() {
		return murders;
	}

	public void addMurder(MurderInterface murder) {
		if (!this.murders.contains(murder)) {
			this.murders.add(murder);
		}
	}

	public List<MurderInterface> murders() {
		return murders;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Player player) {
		if (!this.players.contains(player)) {
			this.players.add(player);
		}
	}

	public void addPlayerByName(String name) {
		boolean find = false;
		for (Player player : players) {
			if (name.equals(player.name())) {
				find = true;
			}
		}
		if (!find) {
			Player p = new Player(name);
			setPlayers(p);
		}
	}

	public Player getPlayerByName(String name) {
		Player p = null;
		for (Player player : players) {
			if (name.equals(player.name())) {
				p = player;
			}
		}
		return p;
	}

	public Player createPlayer(String name) {
		if (getPlayerByName(name) == null) {
			addPlayerByName(name);
		}
		return getPlayerByName(name);
	}

}
