package com.diegoalves.util;

import java.util.regex.Pattern;

public enum Line {

	START("[\\w/]+\\s[\\w:]+ - New match [0-9]{5,10} has started"), 
	PLAYER_KILL("[\\w/]+\\s[\\w:]+ - [\\w]+ killed [\\w]+ using [\\w]+"),
	WORLD_KILL("[\\w/]+\\s[\\w:]+ - <WORLD> killed [\\w]+ by [\\w]+"), 
	FINAL("[\\w/]+\\s[\\w:]+ - Match [0-9]{5,10} has ended");

	private String regex;
	public Pattern pattern;

	private Line(String regex) {
		this.regex = regex;
		this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	}
}