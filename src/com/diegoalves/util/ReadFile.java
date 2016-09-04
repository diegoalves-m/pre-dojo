package com.diegoalves.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

import com.diegoalves.domain.MurderInterface;
import com.diegoalves.domain.Murder;
import com.diegoalves.domain.MatchInterface;
import com.diegoalves.domain.Match;

public class ReadFile {

	final List<MatchInterface> matches = new ArrayList<>();

	public List<MatchInterface> getFile(String fileName) throws NumberFormatException, ParseException {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		Match newMatch = null;

		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				newMatch = processLine(scanner.nextLine(), newMatch);
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return matches;
	}

	private Match processLine(String line, Match newMatch) throws NumberFormatException, ParseException {

		Line typeLine = identifyLine(line);
		String[] parts = line.split(" ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		if (line != null) {
			if (typeLine == Line.START) {
				newMatch = new Match(Long.parseLong(parts[5]), sdf.parse(parts[0] + " " + parts[1]));
				matches.add(newMatch);

			} else if (typeLine == Line.FINAL && newMatch != null) {
				newMatch.addDateFinishMatch(sdf.parse(parts[0] + " " + parts[1]));

			} else if (typeLine == Line.PLAYER_KILL || typeLine == Line.WORLD_KILL && newMatch != null) {
				MurderInterface murder = new Murder(newMatch.createPlayer(parts[3]), newMatch.createPlayer(parts[5]),
						newMatch.getPlayerByName(parts[3]).createWeapon(parts[7]),
						sdf.parse(parts[0] + " " + parts[1]));
				newMatch.addMurder(murder);
			}
		}

		return newMatch;
	}

	private Line identifyLine(String line) {
		for (Line typeLine : Line.values()) {
			Matcher matcher = typeLine.pattern.matcher(line);
			if (matcher.matches()) {
				return typeLine;
			}
		}
		return null;
	}
}
