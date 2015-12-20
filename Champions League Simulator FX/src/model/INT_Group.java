package model;

public interface INT_Group {
	public void order_positions();

	public void play_match(int cod_partido, int jonada, int goles_local, int goles_visita);

	public void show_standings();
}
