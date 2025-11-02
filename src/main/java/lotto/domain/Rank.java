package lotto.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Rank {
    FIRST(6, 2_000_000_000, (match, bonus) -> match == 6),
    SECOND(5, 30_000_000, (match, bonus) -> match == 5 && bonus),
    THIRD(5, 1_500_000, (match, bonus) -> match == 5 && !bonus),
    FOURTH(4, 50_000, (match, bonus) -> match == 4),
    FIFTH(3, 5_000, (match, bonus) -> match == 3),
    MISS(0, 0, (match, bonus) -> match < 3);

    private final int matchCount;
    private final long prizeMoney;
    private final BiPredicate<Integer, Boolean> isMatch;

    Rank(int matchCount, long prizeMoney, BiPredicate<Integer, Boolean> isMatch) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.isMatch = isMatch;
    }

    public static Rank valueOf(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.isMatch.test(matchCount, hasBonus))
                .findFirst()
                .orElse(MISS);
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }
}