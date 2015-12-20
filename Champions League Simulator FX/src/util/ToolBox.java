package util;

public class ToolBox implements INT_ToolBox {
	@Override
	public Integer goal_difference(int GF, int GC) {
		return (GF - GC);
	}

	@Override
	public Integer points(int PG, int PE) {
		int salida = (PG * 3) + PE;
		return salida;
	}

	@Override
	public Float team_efficiency(int Ptos, int PJ) {
		float salida = (float) Ptos / (PJ * 3);
		return salida;
	}

	@Override
	public Integer games_played(int PG, int PE, int PP) {
		return (PG + PE + PP);
	}
}
