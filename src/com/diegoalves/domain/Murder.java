package com.diegoalves.domain;

import java.util.Date;

public class Murder implements MurderInterface {

	public Player kill;
	public Player die;
	public Weapon weapon;
	public Date date;

	public Murder(Player kill, Player die, Weapon weapon, Date date) {
		this.kill = kill;
		this.die = die;
		this.weapon = weapon;
		this.date = date;
	}

	public Murder() {

	}

	@Override
	public Player kill() {
		return kill;
	}

	@Override
	public Player die() {
		return die;
	}

	@Override
	public Weapon weapon() {
		return weapon;
	}

	@Override
	public Date date() {
		return date;
	}

}
