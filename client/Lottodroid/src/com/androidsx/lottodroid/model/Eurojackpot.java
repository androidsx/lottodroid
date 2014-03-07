package com.androidsx.lottodroid.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Results for a Lototurf draw
 */
public class Eurojackpot  extends BaseLottery {
	private final Date date;
	private final int num1;
	private final int num2;
	private final int num3;
	private final int num4;
	private final int num5;
	private final int s1;
	private final int s2;
	private ArrayList<Premio> premios = new ArrayList<Premio>();

	public Eurojackpot(Date date, String htmlLink, int num1, int num2, int num3, int num4,
			int num5, int s1, int s2) {
		super(htmlLink);
		this.date = date;
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		this.num4 = num4;
		this.num5 = num5;
		this.s1 = s1;
		this.s2 = s2;
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

	public int getNum2() {
		return num2;
	}

	public int getNum3() {
		return num3;
	}

	public int getNum4() {
		return num4;
	}

	public int getNum5() {
		return num5;
	}

	public int getS1() {
		return s1;
	}

	public int getS2() {
		return s2;
	}

	@Override
	public LotteryId getId() {
		return LotteryId.EURO_JACKPOT;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public String getName() {
		return "Eurojackpot";
	}

}
