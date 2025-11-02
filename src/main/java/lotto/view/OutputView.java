package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.Rank;

public class OutputView {
    public void printLottoCount(int count) {
        System.out.println("\n" + count + "개를 구매했습니다.");
    }

    // Controller의 for문 안에서 로또 1개씩 출력
    public void printPurchasedLotto(List<Integer> numbers) {
        System.out.println(numbers);
    }

    // Controller에 있던 빈 줄 출력을 담당
    public void printEmptyLine() {
        System.out.println();
    }

    // 당첨 통계 헤더 출력
    public void printStatisticsHeader() {
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    // 당첨 통계 결과 출력
    public void printStatistics(Map<Rank, Integer> statistics) {
        // 요구사항 출력 순서에 맞게 FIFTH부터 FIRST까지 출력
        System.out.println("3개 일치 (5,000원) - " + statistics.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + statistics.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + statistics.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + statistics.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + statistics.get(Rank.FIRST) + "개");
    }

    // 수익률 출력
    public void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + String.format("%.1f", profitRate) + "%입니다.");
    }
}
