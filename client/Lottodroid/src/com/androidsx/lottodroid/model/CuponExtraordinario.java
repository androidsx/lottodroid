package com.androidsx.lottodroid.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Results for a Lototurf draw
 */
public class CuponExtraordinario implements Lottery {
	private final Date date;
	private final int num1;
	private final String num2;
	private ArrayList<Premio> premios = new ArrayList<Premio>();

	public CuponExtraordinario(Date date, int num1, String num2) {
		this.date = date;
		this.num1 = num1;
		this.num2 = num2;
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

	public int getNum1() {
		return num1;
	}

	public String getNum2() {
		return num2;
	}
	

	@Override
	public LotteryId getId() {
		return LotteryId.CUPON_EXTRAORDINARIO;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public String getName() {
		return "Cupon Extraordinario";
	}

}
