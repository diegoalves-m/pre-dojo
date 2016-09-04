package com.diegoalves.domain;

import java.util.Date;
import java.util.List;

public interface MatchInterface {

	public Long idMatch();

	public Date dateStartMatch();

	public Date dateFinishMatch();

	public List<MurderInterface> getMurders();

	public List<Player> getPlayers();

}
