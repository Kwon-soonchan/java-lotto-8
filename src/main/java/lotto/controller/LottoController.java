package lotto.controller;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // Collectors 임포트
import lotto.domain.Lotto;
import lotto.domain.PrizeCalculator; // Domain 임포트
import lotto.domain.WinningNumbers; // Domain 임포트
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        // 1. 로또 구매 (메서드 분리)
        int lottoPrice = Integer.parseInt(inputView.readLottoPrice());
        int lottoCnt = lottoPrice / 1000;
        outputView.printLottoCount(lottoCnt);

        List<Lotto> buyLottos = createLottos(lottoCnt);
        printPurchasedLottos(buyLottos);

        // 2. 당첨/보너스 번호 생성 (메서드 분리)
        WinningNumbers winningNumbers = createWinningNumbers();

        // 3. 통계 계산 (Domain 객체에 위임)
        PrizeCalculator calculator = new PrizeCalculator();
        calculator.calculateStatistics(buyLottos, winningNumbers);

        // 4. 결과 출력
        outputView.printStatisticsHeader();
        outputView.printStatistics(calculator.getStatistics());

        double profitRate = calculator.calculateProfitRate(lottoPrice);
        outputView.printProfitRate(profitRate);
    }

    // (요구사항: indent 2, 메서드 15라인 제한)
    // 로또 생성 로직 분리
    private List<Lotto> createLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(pickUniqueNumbersInRange(1, 45, 6)));
        }
        return lottos;
    }

    // 로또 출력 로직 분리
    private void printPurchasedLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            outputView.printPurchasedLotto(lotto.getNumbers());
        }
        outputView.printEmptyLine();
    }

    // 당첨 번호 생성 로직 분리
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