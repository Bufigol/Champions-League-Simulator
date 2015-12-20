package model;

public class Group implements INT_Group {
	private char group_code;
	private Team[] team_list;
	private Match[] match_list;
	private int[][] table_data;

	public Group(char letra, Team equipo1, Team equipo2, Team equipo3, Team equipo4) {
		this.group_code = letra;
		this.team_list = new Team[4];
		this.team_list[0] = equipo1;
		this.team_list[1] = equipo2;
		this.team_list[2] = equipo3;
		this.team_list[3] = equipo4;
		this.match_list = new Match[12];
		create_match_list();
	}

	@Override
	public void order_positions() {

	}

	private void create_match_list() {
		int contador = 0;
		for (int b = 0; b < this.team_list.length; b++) {
			for (int c = 0; c < this.team_list.length; c++) {
				if (c != b) {
					this.match_list[contador] = new Match(team_list[b], team_list[c]);
					contador++;
				}
			}
		}
	}

	@Override
	public void play_match(int cod_partido, int jornada, int goles_local, int goles_visita) {
		match_list[cod_partido].play_match(goles_local, goles_visita, jornada);
	}

	@Override
	public void show_standings() {

	}
}
