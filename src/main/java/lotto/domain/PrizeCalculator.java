package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PrizeCalculator {
    private static final double PERCENTAGE_MULTIPLIER = 100.0;
    private final Map<Rank, Integer> statistics;

    public PrizeCalculator() {
        this.statistics = new EnumMap<>(Rank.class);
        // Map을 0으로 초기화
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }
    }

    public void calculateStatistics(List<Lotto> lottos, WinningNumbers winningNumbers) {
        for (Lotto lotto : lottos) {
            Rank rank = winningNumbers.match(lotto);
            statistics.put(rank, statistics.get(rank) + 1);
        }
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        return (double) totalPrize / purchaseAmount * PERCENTAGE_MULTIPLIER;
    }

    private long calculateTotalPrize() {
        long totalPrize = 0;
        for (Rank rank : statistics.keySet()) {
            totalPrize += rank.getPrizeMoney() * statistics.get(rank);
        }
        return totalPrize;
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }
}