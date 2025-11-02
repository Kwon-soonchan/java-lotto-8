package lotto.controller;

import static lotto.domain.Lotto.LOTTO_NUMBER_MAX;
import static lotto.domain.Lotto.LOTTO_NUMBER_MIN;
import static lotto.domain.LottoMachine.LOTTO_PRICE;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.PrizeCalculator;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoMachine = new LottoMachine();
    }

    public void run() {
        List<Lotto> buyLottos = purchaseLottos();
        outputView.printLottoCount(buyLottos.size());
        printPurchasedLottos(buyLottos);

        List<Integer> winningNumbersList = getValidWinningNumbers();
        int bonusNumber = getValidBonusNumber(winningNumbersList);
        WinningNumbers winningNumbers = new WinningNumbers(winningNumbersList, bonusNumber);

        PrizeCalculator calculator = new PrizeCalculator();
        calculator.calculateStatistics(buyLottos, winningNumbers);

        outputView.printStatisticsHeader();
        outputView.printStatistics(calculator.getStatistics());

        int lottoPrice = buyLottos.size() * LOTTO_PRICE;
        double profitRate = calculator.calculateProfitRate(lottoPrice);
        outputView.printProfitRate(profitRate);
    }

    private List<Lotto> purchaseLottos() {
        while (true) {
            try {
                String priceInput = inputView.readLottoPrice();
                int price = Integer.parseInt(priceInput);
                return lottoMachine.purchase(price);
            } catch (NumberFormatException e) {
                outputView.printError("[ERROR] 유효하지 않은 숫자입니다.");
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private List<Integer> getValidWinningNumbers() {
        while (true) {
            try {
                String[] winningStr = inputView.readWinningNumbers().split(",");
                List<Integer> numbers = new ArrayList<>();
                for (String numStr : winningStr) {
                    numbers.add(Integer.parseInt(numStr.trim()));
                }
                new Lotto(numbers);
                outputView.printEmptyLine();
                return numbers;
            } catch (NumberFormatException e) {
                outputView.printError("[ERROR] 당첨 번호는 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private int getValidBonusNumber(List<Integer> winningNumbersList) {
        while (true) {
            try {
                int bonus = Integer.parseInt(inputView.readBonusNumber());
                validateBonusNumber(bonus, winningNumbersList);
                outputView.printEmptyLine();
                return bonus;
            } catch (NumberFormatException e) {
                outputView.printError("[ERROR] 보너스 번호는 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void validateBonusNumber(int bonusNum, List<Integer> winningNumbersList) {
        if (bonusNum < LOTTO_NUMBER_MIN || bonusNum > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1에서 45 사이여야 합니다.");
        }
        if (winningNumbersList.contains(bonusNum)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호와 중복됩니다.");
        }
    }

    private void printPurchasedLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            outputView.printPurchasedLotto(lotto.getNumbers());
        }
        outputView.printEmptyLine();
    }
}