package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PrizeCalculator {
    // 'int[] matching' 배열 대신 'EnumMap'을 사용 (요구사항: Enum 활용)
    private final Map<Rank, Integer> statistics;

    public PrizeCalculator() {
        this.statistics = new EnumMap<>(Rank.class);
        // Map을 0으로 초기화
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }
    }

    // Controller의 통계 계산 for문을 이 메서드로 이동
    public void calculateStatistics(List<Lotto> lottos, WinningNumbers winningNumbers) {
        for (Lotto lotto : lottos) {
            Rank rank = winningNumbers.match(lotto); // 'WinningNumbers'가 비교
            statistics.put(rank, statistics.get(rank) + 1); // 결과 집계
        }
    }

    // Controller의 수익률 계산 로직을 이 메서드로 이동
    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = 0;
        for (Rank rank : statistics.keySet()) {
            totalPrize += rank.getPrizeMoney() * statistics.get(rank);
        }
        return (double) totalPrize / purchaseAmount * 100.0;
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }
}