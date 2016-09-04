package com.diegoalves.app;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.diegoalves.domain.MatchInterface;
import com.diegoalves.util.ReadFile;


public class Run {

	public static void main(String[] args) throws NumberFormatException, ParseException {

		List<MatchInterface> matches;
		ReadFile file = new ReadFile();
		matches = file.getFile("log.txt");
		List<MatchResult> results = new ArrayList<MatchResult>();
		
		for (MatchInterface match : matches) {
			MatchResult result = new MatchResult(match);
			results.add(result);
		}
		
		for(MatchResult result : results){
			PrintResult print = new PrintResult(result);
		}
		
		System.out.println(matches.get(0).dateStartMatch());
		
	}

}
