package model;

public class Match implements INT_Match {
	private Team home_team;
	private Team away_team;
	private int round;
	private int local_goals;
	private int away_goals;

	/**
	 * @param local
	 * @param visita
	 */
	public Match(Team local, Team visita) {
		this.home_team = local;
		this.away_team = visita;
	}

	@Override
	public void play_match(int home_goals, int away_goals, int round) {
		this.local_goals = home_goals;
		this.away_goals = away_goals;
		this.round = round;
		this.home_team.play_match(home_goals, away_goals);
		this.away_team.play_match(away_goals, home_goals);
	}

	@Override
	public String toString() {
		return home_team.getCodigo_equipo() + " " + local_goals + " - " + away_goals + " "
				+ away_team.getCodigo_equipo();
	}

	public Team getLocal() {
		return home_team;
	}

	public Team getVisita() {
		return away_team;
	}

	public int getJornada() {
		return round;
	}

	public int getGoles_local() {
		return local_goals;
	}

	public int getGoles_visita() {
		return away_goals;
	}

	public void setLocal(Team local) {
		this.home_team = local;
	}

	public void setVisita(Team visita) {
		this.away_team = visita;
	}

	public void setJornada(int jornada) {
		this.round = jornada;
	}

	public void setGoles_local(int goles_local) {
		this.local_goals = goles_local;
	}

	public void setGoles_visita(int goles_visita) {
		this.away_goals = goles_visita;
	}

}
