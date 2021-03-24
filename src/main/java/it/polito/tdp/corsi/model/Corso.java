package it.polito.tdp.corsi.model;

public class Corso {
	
	//JAVA BEAN PER COME E COSTRUITA !!!! e quindi vado a definire tutte le implementazioni base per queste classi 
	// (attributi della tabella relativa , costruttore , get e set , heshcode e equals , toString )

	//inserisco gli attributi che ha la mia tabella corso (sono quelli del diagramma ER o su Sequel Pro
	
	private String codins;
	private Integer crediti;
	private String nome;
	private Integer pd;
	
	public Corso(String codins, Integer crediti, String nome, Integer pd) {
		super();
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}
	/*
	// i java Beans necessitano anche di getter e setter oltre al costruttore
	*/
	public String getCodins() {
		return codins;
	}
	public void setCodins(String codins) {
		this.codins = codins;
	}
	public Integer getCrediti() {
		return crediti;
	}
	public void setCrediti(Integer crediti) {
		this.crediti = crediti;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPd() {
		return pd;
	}
	public void setPd(Integer pd) {
		this.pd = pd;
	}
	
	//ridefinisco anche Hash code e equals facendolo sulla chiave primaria cosi sono tranquillo che funzioni (ho selezionato solo codins in questo caso
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}
	
	//ho creato anche il toString per essere tranquillo
	@Override
	public String toString() {
		return "Corso [codins=" + codins + ", crediti=" + crediti + ", nome=" + nome + ", pd=" + pd + "]";
	}
	
	
	 
	
	
	
}
