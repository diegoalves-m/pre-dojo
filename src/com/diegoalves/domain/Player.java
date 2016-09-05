package com.diegoalves.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public String name;
    public int majorMurderWithoutDieSeq;
    public int awardWinWithoutDie;
    public int awardKillFiveInOneMinute;
    public Weapon bestWeapon;
    public List<Weapon> weaponsPlayer = new ArrayList<Weapon>();

    public Player(String name) {
        this.name = name;
    }

    public Player() {

    }

    public String name() {
        return name;
    }

    public int getMajorMurderWithoutDieSeq() {
        return majorMurderWithoutDieSeq;
    }

    public void setMajorMurderWithoutDiegSeq(int majorMurderWithoutDieSeq) {
        this.majorMurderWithoutDieSeq = majorMurderWithoutDieSeq;
    }

    public int getAwardWindWithoutDie() {
        return awardWinWithoutDie;
    }

    public void addAwardWinWithoutDie() {
        this.awardWinWithoutDie++;
    }

    public int getAwardKillFiveInOneMinute() {
        return awardKillFiveInOneMinute;
    }

    public void addAwardKillFiveInOneMinute() {
        this.awardKillFiveInOneMinute++;
    }

    public List<Weapon> getWeaponsPlayer() {
        return weaponsPlayer;
    }

    public void setWeaponPlayer(Weapon weapon) {
        if (!this.weaponsPlayer.contains(weapon)) {
            this.weaponsPlayer.add(weapon);
        }
    }

    public void addWeaponByName(String name) {
        boolean find = false;
        for (Weapon weapon : weaponsPlayer) {
            if (name.equals(weapon.name())) {
                find = true;
            }
        }
        if (!find) {
            Weapon w = new Weapon(name);
            setWeaponPlayer(w);
        }
    }

    public Weapon getweaponByName(String name) {
        Weapon w = null;
        for (Weapon weapon : weaponsPlayer) {
            if (name.equals(weapon.name())) {
                w = weapon;
            }
        }
        return w;
    }

    public Weapon createWeapon(String name) {
        if (getweaponByName(name) == null) {
            addWeaponByName(name);
        }
        return getweaponByName(name);
    }

    public Weapon getBestWeapon() {
        return bestWeapon;
    }

    public void setBestWeapon(Weapon weapon) {
        this.bestWeapon = weapon;
    }

}
