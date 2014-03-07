package com.androidsx.lottodroid.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Results for a Trio draw
 */
public class Trio  extends BaseLottery {
	private final Date date;
	private final int num1;
	private final int num2;
	private final int num3;
	private ArrayList<Premio> premios = new ArrayList<Premio>();

	public Trio(Date date, String htmlLink, int num1, int num2, int num3) {
		super(htmlLink);
		this.date = date;
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
	}

	/** Inner class that represents a Trio */
	public class Premio {

		private final String categoria;
		private final String importeEuros;

		private Premio(String categoria, String importeEuros) {
			this.categoria = categoria;
			this.importeEuros = importeEuros;
		}

		@Override
		public String toString() {
			return new StringBuilder().append("  Categoria: ")
					.append(categoria).append("  ImporteEuros: ")
					.append(importeEuros).toString();
		}
	}

	public void addPremio(String categoria, String importeEuros) {
		premios.add(new Premio(categoria, importeEuros));
	}

	public Premio getPremio(int index) {
		return premios.get(index);
	}

	public int getNumPremios() {
		return premios.size();
	}

	public String getCategoria(int index) {
		return premios.get(index).categoria;
	}

	public String getImporteEuros(int index) {
		return premios.get(index).importeEuros;
	}

	public int getNum1() {
		return num1;
	}

	public int getNum2() {
		return num2;
	}

	public int getNum3() {
		return num3;
	}

	@Override
	public LotteryId getId() {
		return LotteryId.TRIO;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public String getName() {
		return "Trio";
	}

}
