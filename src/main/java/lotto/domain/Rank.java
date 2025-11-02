package lotto.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Rank {
    // Enum(상수)이 스스로 상금, 일치 개수를 알도록 설정
    FIRST(6, 2_000_000_000, (match, bonus) -> match == 6),
    SECOND(5, 30_000_000, (match, bonus) -> match == 5 && bonus),
    THIRD(5, 1_500_000, (match, bonus) -> match == 5 && !bonus),
    FOURTH(4, 50_000, (match, bonus) -> match == 4),
    FIFTH(3, 5_000, (match, bonus) -> match == 3),
    MISS(0, 0, (match, bonus) -> match < 3);

    private final int matchCount;
    private final long prizeMoney;
    // 'else if'를 대체하는 함수형 인터페이스
    private final BiPredicate<Integer, Boolean> isMatch;

    Rank(int matchCount, long prizeMoney, BiPredicate<Integer, Boolean> isMatch) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.isMatch = isMatch;
    }

    // (요구사항: 'else' 금지)
    // 'else if' 로직을 'if'와 'return' 구조로 변경
    public static Rank valueOf(int matchCount, boolean hasBonus) {
        // Enum의 'values()'를 순회하며 일치하는 등수를 찾음
        return Arrays.stream(values())
                .filter(rank -> rank.isMatch.test(matchCount, hasBonus))
                .findFirst()
                .orElse(MISS);
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }
}