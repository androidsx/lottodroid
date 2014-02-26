package com.androidsx.lottodroid.communication;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.androidsx.lottodroid.model.Bonoloto;
import com.androidsx.lottodroid.model.CuponazoOnce;
import com.androidsx.lottodroid.model.Euromillon;
import com.androidsx.lottodroid.model.GordoPrimitiva;
import com.androidsx.lottodroid.model.Loteria7_39;
import com.androidsx.lottodroid.model.LoteriaNacional;
import com.androidsx.lottodroid.model.Lototurf;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.Lotto6_49;
import com.androidsx.lottodroid.model.Once;
import com.androidsx.lottodroid.model.OnceFinde;
import com.androidsx.lottodroid.model.Primitiva;
import com.androidsx.lottodroid.model.Quiniela;
import com.androidsx.lottodroid.model.Quinigol;
import com.androidsx.lottodroid.model.QuintuplePlus;
import com.androidsx.lottodroid.model.Trio;

/**
 * Implementation for {@link LotteryFetcher} that uses in-memory, hard-coded data.
 */
class MockLotteryFetcher implements LotteryFetcher {

  /**
   * It sets the amount of time <i>in seconds</i> that we will wait before returning the data, in
   * order to simulate some latency due to the network.
   */
  public static int MOCK_DELAY = 2;

  @Override
  public List<Lottery> retrieveLastAllLotteries() {
    List<Lottery> listLottery = new LinkedList<Lottery>();
    listLottery.add(new Bonoloto(new Date(), "www.lotoluck.com", 1, 2, 3, 4, 5, 6, 4, 3));
    listLottery.add(new Primitiva(new Date(), "www.lotoluck.com", 1, 2, 3, 4, 5, 6, 4, 3));
    listLottery.add(new Lototurf(new Date(), "www.lotoluck.com", 1, 2, 3, 4, 5, 6, 3, 4));
    listLottery.add(new LoteriaNacional(new Date(), "www.lotoluck.com", 1, 2, 3, "4", 5, 6, 3));
    listLottery.add(new Euromillon(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2, 1));
    listLottery.add(new GordoPrimitiva(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2));
    
    Quinigol quinigol2 = new Quinigol(new Date(), "www.lotoluck.com");
    quinigol2.setMatch(0, "Barcelona", "Villareal", "2", "M");
    quinigol2.setMatch(1, "Betis", "Villareal", "1", "0");
    quinigol2.setMatch(2, "R. Madrid", "Villareal", "2", "0");
    quinigol2.setMatch(3, "R. Madrid", "Villareal", "2", "0");
    quinigol2.setMatch(4, "R. Madrid", "Villareal", "2", "0");
    quinigol2.setMatch(5, "R. Madrid", "Villareal", "2", "0");
    listLottery.add(quinigol2);
    
    Quiniela quiniela = new Quiniela(new Date(), "www.lotoluck.com");
    quiniela.setMatch(0, "Barcelona", "Villareal", "X");
    quiniela.setMatch(1, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(2, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(3, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(4, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(5, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(6, "R. Madrid", "Villareal", "1");
    quiniela.setMatch(7, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(8, "R. Madrid", "Villareal", "1");
    quiniela.setMatch(9, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(10, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(11, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(12, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(13, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(14, "R. Madrid", "Villareal", "2");
    listLottery.add(quiniela);

    simulateLatency();
    
    return listLottery;
  }

  @Override
  public List<Bonoloto> retrieveLastBonolotos(int start, int limit) {
    List<Bonoloto> listBonoloto = new LinkedList<Bonoloto>();
    listBonoloto.add(new Bonoloto(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2, 1, 1));
    listBonoloto.add(new Bonoloto(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2, 1, 1));
    listBonoloto.add(new Bonoloto(new Date(), "www.lotoluck.com", 1, 2, 3, 4, 5, 6, 3, 2));

    simulateLatency();
    
    return listBonoloto;
  }
  
  @Override
  public List<GordoPrimitiva> retrieveLastGordoPrimitivas(int start, int limit) {
    List<GordoPrimitiva> listGordoPrimitiva = new LinkedList<GordoPrimitiva>();
    listGordoPrimitiva.add(new GordoPrimitiva(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 1));
    listGordoPrimitiva.add(new GordoPrimitiva(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 1));
    listGordoPrimitiva.add(new GordoPrimitiva(new Date(), "www.lotoluck.com", 1, 2, 3, 4, 3, 2));

    simulateLatency();
    
    return listGordoPrimitiva;
  }

  @Override
  public List<Quiniela> retrieveLastQuinielas(int start, int limit) {
    List<Quiniela> listQuiniela = new LinkedList<Quiniela>();

    Quiniela quiniela = new Quiniela(new Date(), "www.lotoluck.com");
    quiniela.setMatch(0, "Barcelona", "Villareal", "X");
    quiniela.setMatch(1, "Betis", "Villareal", "1");
    quiniela.setMatch(2, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(3, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(4, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(5, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(6, "R. Madrid", "Villareal", "1");
    quiniela.setMatch(7, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(8, "R. Madrid", "Villareal", "1");
    quiniela.setMatch(9, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(10, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(11, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(12, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(13, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(14, "R. Madrid", "Villareal", "2");

    listQuiniela.add(quiniela);

    Quiniela quiniela2 = new Quiniela(new Date(), "www.lotoluck.com");
    quiniela2.setMatch(0, "Barcelona", "Villareal", "X");
    quiniela2.setMatch(1, "Betis", "Villareal", "1");
    quiniela2.setMatch(2, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(3, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(4, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(5, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(6, "R. Madrid", "Villareal", "1");
    quiniela2.setMatch(7, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(8, "R. Madrid", "Villareal", "1");
    quiniela2.setMatch(9, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(10, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(11, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(12, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(13, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(14, "R. Madrid", "Villareal", "2");

    listQuiniela.add(quiniela2);

    Quiniela quiniela3 = new Quiniela(new Date(), "www.lotoluck.com");
    quiniela3.setMatch(0, "Barcelona", "Villareal", "X");
    quiniela3.setMatch(1, "Betis", "Villareal", "1");
    quiniela3.setMatch(2, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(0, "Barcelona", "Villareal", "X");
    quiniela3.setMatch(1, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(2, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(3, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(4, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(5, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(6, "R. Madrid", "Villareal", "1");
    quiniela3.setMatch(7, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(8, "R. Madrid", "Villareal", "1");
    quiniela3.setMatch(9, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(10, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(11, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(12, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(13, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(14, "R. Madrid", "Villareal", "2");

    listQuiniela.add(quiniela3);

    simulateLatency();

    return listQuiniela;
  }
  
  @Override
  public List<Primitiva> retrieveLastPrimitivas(int start, int limit) {
    List<Primitiva> listPrimitiva = new LinkedList<Primitiva>();
    listPrimitiva.add(new Primitiva(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2, 1, 1));
    listPrimitiva.add(new Primitiva(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2, 1, 1));
    listPrimitiva.add(new Primitiva(new Date(), "www.lotoluck.com", 1, 2, 3, 4, 5, 6, 3, 2));

    simulateLatency();
    
    return listPrimitiva;
  }

  @Override
  public List<Euromillon> retrieveLastEuromillones(int start, int limit) {
      List<Euromillon> listPrimitiva = new LinkedList<Euromillon>();
      listPrimitiva.add(new Euromillon(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2, 1));
      listPrimitiva.add(new Euromillon(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2, 1));
      listPrimitiva.add(new Euromillon(new Date(), "www.lotoluck.com", 1, 2, 3, 4, 5, 6, 3));
      listPrimitiva.add(new Euromillon(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2, 1));
      listPrimitiva.add(new Euromillon(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2, 1));
      listPrimitiva.add(new Euromillon(new Date(), "www.lotoluck.com", 1, 2, 3, 4, 5, 6, 3));
      listPrimitiva.add(new Euromillon(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2, 1));
      listPrimitiva.add(new Euromillon(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2, 1));
      listPrimitiva.add(new Euromillon(new Date(), "www.lotoluck.com", 1, 2, 3, 4, 5, 6, 3));

      simulateLatency();
      
      return listPrimitiva;
  }

  @Override
  public List<LoteriaNacional> retrieveLastLoteriasNacionales(int start, int limit) {
    List<LoteriaNacional> listPrimitiva = new LinkedList<LoteriaNacional>();
    listPrimitiva.add(new LoteriaNacional(new Date(), "www.lotoluck.com", 1, 2, 3, "", 5, 6, 3));
  
    simulateLatency();
    
    return listPrimitiva;
  }

  @Override
  public List<Lototurf> retrieveLastLototurfs(int start, int limit) {
    List<Lototurf> listPrimitiva = new LinkedList<Lototurf>();
    listPrimitiva.add(new Lototurf(new Date(), "www.lotoluck.com", 6, 5, 2, 3, 1, 2, 1, 4));
    listPrimitiva.add(new Lototurf(new Date(), "www.lotoluck.com", 1, 2, 3, 4, 5, 6, 3, 4));
  
    simulateLatency();
    
    return listPrimitiva;
  }

  @Override
  public List<Quinigol> retrieveLastQuinigoles(int start, int limit) {
    List<Quinigol> listQuiniela = new LinkedList<Quinigol>();

    Quinigol quiniela = new Quinigol(new Date(), "www.lotoluck.com");
    quiniela.setMatch(0, "Barcelona", "Villareal", "4", "4");
    quiniela.setMatch(1, "Betis", "Villareal", "1", "3");
    quiniela.setMatch(2, "R. Madrid", "Villareal", "2", "3");
    quiniela.setMatch(3, "R. Madrid", "Villareal", "2", "M");
    quiniela.setMatch(4, "R. Madrid", "Villareal", "2", "3");
    quiniela.setMatch(5, "R. Madrid", "Villareal", "2", "3");

    listQuiniela.add(quiniela);

    Quinigol quiniela2 = new Quinigol(new Date(), "www.lotoluck.com");
    quiniela2.setMatch(0, "Barcelona", "Villareal", "2", "M");
    quiniela2.setMatch(1, "Betis", "Villareal", "1", "0");
    quiniela2.setMatch(2, "R. Madrid", "Villareal", "2", "0");
    quiniela2.setMatch(3, "R. Madrid", "Villareal", "2", "0");
    quiniela2.setMatch(4, "R. Madrid", "Villareal", "2", "0");
    quiniela2.setMatch(5, "R. Madrid", "Villareal", "2", "0");

    simulateLatency();

    return listQuiniela;
  }
  
  private void simulateLatency() {
    try {
      Thread.sleep(1000 * MOCK_DELAY);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  	@Override
	public List<Bonoloto> retrieveBonolotos(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}
	
	@Override
	public List<Quiniela> retrieveQuinielas(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}
	
	@Override
	public List<GordoPrimitiva> retrieveGordoPrimitivas(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}
	
	@Override
	public List<Primitiva> retrievePrimitivas(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}
	
	@Override
	public List<Lototurf> retrieveLototurfs(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}
	
	@Override
	public List<LoteriaNacional> retrieveLoteriasNacionales(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}
	
	@Override
	public List<Quinigol> retrieveQuinigoles(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}
	
	@Override
	public List<Euromillon> retrieveEuromillones(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}

	@Override
	public List<CuponazoOnce> retrieveCuponazoOnce(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}

	@Override
	public List<Loteria7_39> retrieveLoteria7_39(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}

	@Override
	public List<Lotto6_49> retrieveLotto6_49(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}

	@Override
	public List<Once> retrieveOnce(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}

	@Override
	public List<OnceFinde> retrieveOnceFinde(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}

	@Override
	public List<QuintuplePlus> retrieveQuintuplePlus(Long date)
			throws LotteryInfoUnavailableException {
		// This method is just used when retrieve data from Lotoluck
		return null;
	}

}
