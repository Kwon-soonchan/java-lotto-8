package lotto.controller;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
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
        // 1. 구매
        int lottoPrice = Integer.parseInt(inputView.readLottoPrice());
        int lottoCnt = lottoPrice / 1000;
        outputView.printLottoCount(lottoCnt);

        // 2. 로또 발행
        List<Lotto> buyLottos = new ArrayList<>();
        for(int i=0;i<lottoCnt;i++) {
            List<Integer> lottoNumbers = pickUniqueNumbersInRange(1,45,6);
            Lotto newLotto = new Lotto(lottoNumbers);
            buyLottos.add(newLotto);
            outputView.printPurchasedLotto(lottoNumbers);
        }
        outputView.printEmptyLine();

        // 3. 당첨 번호 입력
        String[] winningNumbersStr = inputView.readWinningNumbers().split(",");
        outputView.printEmptyLine();

        List<Integer> winningNumbers = new ArrayList<>();
        for(String numberStr : winningNumbersStr) {
            winningNumbers.add(Integer.parseInt(numberStr.trim()));
        }

        // 4. 보너스 번호 입력
        int bonusNumber = Integer.parseInt(inputView.readBonusNumber());
        outputView.printEmptyLine();

        // 5. 통계 헤더 출력
        outputView.printStatisticsHeader();

        // 6. 통계 계산
        int[] matching = new int[5];
        long totalMoney = 0;
        for(Lotto myLotto : buyLottos) {
            boolean checkBonus = false;
            int sameCnt = 0;
            List<Integer> lottoNumbers = myLotto.getNumbers();

            for(int lottoNumber : lottoNumbers) {
                if(winningNumbers.contains(lottoNumber)) {
                    sameCnt++;
                }
            }
            if(lottoNumbers.contains(bonusNumber)) {
                checkBonus = true;
            }

            if(sameCnt == 3) {
                matching[0]++;
                totalMoney += 5000;
            }
            else if(sameCnt == 4) {
                matching[1]++;
                totalMoney += 50000;
            }
            else if(sameCnt == 5 && !checkBonus) {
                matching[2]++;
                totalMoney +=1500000;
            }
            else if(sameCnt == 5 && checkBonus) {
                matching[3]++;
                totalMoney += 30000000;
            }
            else if(sameCnt == 6) {
                matching[4]++;
                totalMoney += 2000000000;
            }

        }
        // 7. 통계 결과 출력
        outputView.printStatistics(matching);

        double profitRate = (double) totalMoney / lottoPrice * 100.0;
        outputView.printProfitRate(profitRate);
    }
}
