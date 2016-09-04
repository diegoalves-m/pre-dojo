package com.diegoalves.app;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.diegoalves.domain.Player;

public class PrintResult {

	public List<MatchResult> results;
	
	public PrintResult(MatchResult result){
		printResult(result);
	}
	
	public PrintResult(){
		
	}
	
	public void printResult(MatchResult result){
		
		System.out.println("");
		System.out.println("--- Results for match " + result.getIdMatch() + " start ---");
		
		System.out.println("");
		System.out.println("--Player and amount murders--");
		for(Map.Entry<Player, Integer> amountMurderPlayer : result.getPlayerAmountMurder().entrySet()){
			 Player player = amountMurderPlayer.getKey();
			  Integer amount = amountMurderPlayer.getValue();
			
			System.out.println("- " + player.name() + " " + amount + " -");
		}
		
		System.out.println("");
		System.out.println("--Player and amount death--");
		for(Map.Entry<Player, Integer> amountDeathPlayer : result.getPlayerAmountDeath().entrySet()){
			 Player player = amountDeathPlayer.getKey();
			  Integer amount = amountDeathPlayer.getValue();
			
			System.out.println("- " + player.name() + " " + amount + " -");
		}
		
		System.out.println("");
		System.out.println("-- Player major sequence murder without die --");
		for(Map.Entry<Player, Integer> majorSeqWithoutDie : result.getPlayerMurderWithoutDieSeq().entrySet()){
			 Player player = majorSeqWithoutDie.getKey();
			 Integer amount = majorSeqWithoutDie.getValue();
			
			System.out.println("- " + player.name() + " " + amount + " -");
		}
		
		System.out.println("");
		System.out.println("-- Player winner and your best weapon --");
		System.out.println("- " + result.getPlayerWinner().name +
				" (best weapon " + result.getPlayerWinner().bestWeapon.name + ") -");
		
		
		System.out.println("");
		System.out.println("-- Player winner without die (Won Award) --");
		for(Player playerWinnerWithoutDie : result.getPlayerWinWithoutDie()){
			
			System.out.println("-" + playerWinnerWithoutDie.name() + " -");
		}
		
		System.out.println("");
		System.out.println("-- Player killed five in one minute (Won award) --");
		for(Map.Entry<Player, Date> playersKillFiveInMinute : result.getPlayersKillFiveInMinuteSeq().entrySet()){
			 Player player = playersKillFiveInMinute.getKey();
			
			System.out.println("- " + player.name() + " -");
		}
		
		System.out.println();
		System.out.println("--- Match " + result.getIdMatch() + " finished results ---");
		System.out.println("");
	}
	
}
