package com.exceltogpx;

import java.util.Objects;

public class Address {
	private String citta;
	private String via;
	private String civico;
	
	/**
	 * @param citta
	 * @param via
	 * @param civico
	 */
	public Address(String citta, String via, String civico) {
		super();
		this.citta = citta;
		this.via = via;
		this.civico = civico;
	}

	/**
	 * 
	 */
	public Address() {
		super();
	}

	protected void setCitta(String citta) {
		this.citta = citta;
	}

	protected void setVia(String via) {
		this.via = via;
	}

	protected void setCivico(String civico) {
		this.civico = civico;
	}

	protected String getCitta() {
		return citta;
	}

	protected String getVia() {
		return via;
	}

	protected String getCivico() {
		return civico;
	}

	/**
	 * equals only checks for city and street names
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(citta, other.citta) && Objects.equals(via, other.via);
	}

	@Override
	public String toString() {
		return citta + ' ' + via + ' ' + civico;
	}

	
	
}
