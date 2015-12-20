package model;

import util.ToolBox;

public class Team implements INT_Team {
	private Integer key;
	private String team_name;
	private String team_code;
	private String country_origin;
	private Integer Pos;
	private Integer Ptos;
	private Integer PJ;
	private Integer PG;
	private Integer PE;
	private Integer PP;
	private Integer GF;
	private Integer GC;
	private Integer Dif;
	private Float EFE;
	private final ToolBox herramientas;

	// Constructores
	public Team() {
		this.herramientas = new ToolBox();
	}

	/**
	 * @param key
	 * @param nombre_equipo
	 * @param codigo_equipo
	 * @param pais_Origen
	 */
	public Team(Integer key, String nombre_equipo, String codigo_equipo, String pais_Origen) {
		this.key = key;
		team_name = nombre_equipo;
		team_code = codigo_equipo;
		country_origin = pais_Origen;
		this.herramientas = new ToolBox();
	}

	@Override
	public void update_team_data() {
		this.Ptos = herramientas.points(this.PG, this.PE);
		this.PJ = herramientas.games_played(this.PG, this.PE, this.PP);
		this.EFE = herramientas.team_efficiency(this.Ptos, this.PJ);
		this.Dif = herramientas.goal_difference(this.GF, this.GC);
	}

	@Override
	public void play_match(int GF, int GC) {
		this.GF += GF;
		this.GC += GC;
		match_result(GF, GC);
		update_team_data();
	}

	private void match_result(int goles_equipo, int goles_contrario) {
		if (goles_equipo == goles_contrario) {
			this.PE += 1;
		} else {
			if (goles_equipo > goles_contrario) {
				this.PG += 1;
			} else {
				this.PP += 1;
			}
		}
	}

	public Integer getKey() {
		return key;
	}

	public String getNombre_equipo() {
		return team_name;
	}

	public String getCodigo_equipo() {
		return team_code;
	}

	public String getPais_Origen() {
		return country_origin;
	}

	public Integer getPos() {
		return Pos;
	}

	public Integer getPtos() {
		return Ptos;
	}

	public Integer getPJ() {
		return PJ;
	}

	public Integer getPG() {
		return PG;
	}

	public Integer getPE() {
		return PE;
	}

	public Integer getPP() {
		return PP;
	}

	public Integer getGF() {
		return GF;
	}

	public Integer getGC() {
		return GC;
	}

	public Integer getDif() {
		return Dif;
	}

	public Float getEFE() {
		return EFE;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public void setNombre_equipo(String nombre_equipo) {
		team_name = nombre_equipo;
	}

	public void setCodigo_equipo(String codigo_equipo) {
		team_code = codigo_equipo;
	}

	public void setPais_Origen(String pais_Origen) {
		country_origin = pais_Origen;
	}

	public void setPos(Integer pos) {
		Pos = pos;
	}

	public void setPtos(Integer ptos) {
		Ptos = ptos;
	}

	public void setPJ(Integer pJ) {
		PJ = pJ;
	}

	public void setPG(Integer pG) {
		PG = pG;
	}

	public void setPE(Integer pE) {
		PE = pE;
	}

	public void setPP(Integer pP) {
		PP = pP;
	}

	public void setGF(Integer gF) {
		GF = gF;
	}

	public void setGC(Integer gC) {
		GC = gC;
	}

	public void setDif(Integer dif) {
		Dif = dif;
	}

	public void setEFE(Float eFE) {
		EFE = eFE;
	}

}
