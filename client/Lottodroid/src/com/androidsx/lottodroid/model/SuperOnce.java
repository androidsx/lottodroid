package com.androidsx.lottodroid.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Results for a Lototurf draw
 */
public class SuperOnce implements Lottery {
	private final Date date;
	private final String result;
	private ArrayList<Premio> premios = new ArrayList<Premio>();

	public SuperOnce(Date date, String result) {
		this.date = date;
		this.result = result;
	}

	/** Inner class that represents a Lototurf Premio */
	public class Premio {

		private final int acertantes;
		private final String categoria;
		private final float importeEuros;
		private final long importePesetas;

		private Premio(int acertantes, String categoria, float importeEuros,
				long importePesetas) {
			this.acertantes = acertantes;
			this.categoria = categoria;
			this.importeEuros = importeEuros;
			this.importePesetas = importePesetas;
		}

		@Override
		public String toString() {
			return new StringBuilder().append("Acertantes: ")
					.append(acertantes).append("  Categoria: ")
					.append(categoria).append("  ImporteEuros: ")
					.append(importeEuros).append("  ImportePesetas: ")
					.append(importePesetas).toString();
		}
	}

	public void addPremio(int acertantes, String categoria, float importeEuros,
			long importePesetas) {
		premios.add(new Premio(acertantes, categoria, importeEuros,
				importePesetas));
	}

	public Premio getPremio(int index) {
		return premios.get(index);
	}

	public int getNumPremios() {
		return premios.size();
	}

	public int getAcetantes(int index) {
		return premios.get(index).acertantes;
	}

	public String getCategoria(int index) {
		return premios.get(index).categoria;
	}

	public float getImporteEuros(int index) {
		return premios.get(index).importeEuros;
	}

	public long getImportePesetas(int index) {
		return premios.get(index).importePesetas;
	}

	public String getResult() {
		return result;
	}

	@Override
	public LotteryId getId() {
		return LotteryId.SUPER_ONCE;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public String getName() {
		return "Super Once";
	}

}
