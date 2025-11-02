package lotto.view;

import java.util.List;

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
    public void printStatistics(int[] matching) {
        System.out.println("3개 일치 (5,000원) - " + matching[0] + "개");
        System.out.println("4개 일치 (50,000원) - " + matching[1] + "개");
        System.out.println("5개 일치 (1,500,000원) - " + matching[2] + "개");
        System.out.println("5개 일치, 보너스  볼 일치 (30,000,000원) - " + matching[3] + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + matching[4] + "개");
    }

    // 수익률 출력
    public void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + String.format("%.1f", profitRate) + "%입니다.");
    }
}
