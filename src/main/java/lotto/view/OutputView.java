package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.Rank;

public class OutputView {
    public void printLottoCount(int count) {
        System.out.println("\n" + count + "개를 구매했습니다.");
    }

    public void printPurchasedLotto(List<Integer> numbers) {
        System.out.println(numbers);
    }

    public void printEmptyLine() {
        System.out.println();
    }

    public void printStatisticsHeader() {
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    public void printStatistics(Map<Rank, Integer> statistics) {
        System.out.println("3개 일치 (5,000원) - " + statistics.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + statistics.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + statistics.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + statistics.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + statistics.get(Rank.FIRST) + "개");
    }

    public void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + String.format("%.1f", profitRate) + "%입니다.");
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
