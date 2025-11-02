package lotto.controller;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;
import static lotto.domain.Lotto.LOTTO_NUMBER_COUNT;
import static lotto.domain.Lotto.LOTTO_NUMBER_MAX;
import static lotto.domain.Lotto.LOTTO_NUMBER_MIN;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PrizeCalculator;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private static final int LOTTO_PRICE = 1000;

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        int lottoPrice = Integer.parseInt(inputView.readLottoPrice());
        int lottoCnt = lottoPrice / LOTTO_PRICE;
        outputView.printLottoCount(lottoCnt);

        List<Lotto> buyLottos = createLottos(lottoCnt);
        printPurchasedLottos(buyLottos);

        WinningNumbers winningNumbers = createWinningNumbers();

        PrizeCalculator calculator = new PrizeCalculator();
        calculator.calculateStatistics(buyLottos, winningNumbers);

        outputView.printStatisticsHeader();
        outputView.printStatistics(calculator.getStatistics());

        double profitRate = calculator.calculateProfitRate(lottoPrice);
        outputView.printProfitRate(profitRate);
    }

    private List<Lotto> createLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(pickUniqueNumbersInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBER_COUNT)));
        }
        return lottos;
    }

    private void printPurchasedLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            outputView.printPurchasedLotto(lotto.getNumbers());
        }
        outputView.printEmptyLine();
    }

    private WinningNumbers createWinningNumbers() {
        String[] winningStr = inputView.readWinningNumbers().split(",");
        outputView.printEmptyLine();

        List<Integer> numbers = new ArrayList<>();
        for (String numStr : winningStr) {
            numbers.add(Integer.parseInt(numStr.trim()));
        }

        int bonus = Integer.parseInt(inputView.readBonusNumber());
        outputView.printEmptyLine();

        return new WinningNumbers(numbers, bonus);
    }
}