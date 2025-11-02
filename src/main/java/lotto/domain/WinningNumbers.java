package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private final Lotto winningLotto; // 6개 당첨 번호 (Lotto 객체 재활용)
    private final int bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        // 당첨 번호도 'Lotto'의 유효성 검사(6개, 중복, 범위)를 통과해야 함
        this.winningLotto = new Lotto(numbers);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    // 보너스 번호 유효성 검사
    private void validateBonusNumber(int bonusNum) {
        if (bonusNum < 1 || bonusNum > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1에서 45 사이여야 합니다.");
        }
        if (winningLotto.getNumbers().contains(bonusNum)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호와 중복됩니다.");
        }
    }

    // (핵심) '내 로또'와 '당첨 번호'를 비교하여 '등수'를 반환
    public Rank match(Lotto userLotto) {
        int matchCount = (int) userLotto.getNumbers().stream()
                .filter(winningLotto.getNumbers()::contains)
                .count();

        boolean hasBonus = userLotto.getNumbers().contains(bonusNumber);

        // 'else if'가 사라지고 Rank Enum이 로직을 처리
        return Rank.valueOf(matchCount, hasBonus);
    }
}