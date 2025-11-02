package lotto.domain;

import static lotto.domain.Lotto.LOTTO_NUMBER_MAX;
import static lotto.domain.Lotto.LOTTO_NUMBER_MIN;

import java.util.List;

public class WinningNumbers {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        this.winningLotto = new Lotto(numbers);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonusNum) {
        if (bonusNum < LOTTO_NUMBER_MIN || bonusNum > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1에서 45 사이여야 합니다.");
        }
        if (winningLotto.getNumbers().contains(bonusNum)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호와 중복됩니다.");
        }
    }

    public Rank match(Lotto userLotto) {
        int matchCount = (int) userLotto.getNumbers().stream()
                .filter(winningLotto.getNumbers()::contains)
                .count();

        boolean hasBonus = userLotto.getNumbers().contains(bonusNumber);

        return Rank.valueOf(matchCount, hasBonus);
    }
}