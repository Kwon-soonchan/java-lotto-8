package lotto.domain;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;
import static lotto.domain.Lotto.LOTTO_NUMBER_COUNT;
import static lotto.domain.Lotto.LOTTO_NUMBER_MAX;
import static lotto.domain.Lotto.LOTTO_NUMBER_MIN;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public static final int LOTTO_PRICE = 1000;

    public List<Lotto> purchase(int price) {
        validateLottoPrice(price);
        int count = calculateLottoCount(price);
        return createLottos(count);
    }

    private void validateLottoPrice(int price) {
        if (price <= 0 || price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위의 양수여야 합니다.");
        }
    }

    private int calculateLottoCount(int price) {
        return price / LOTTO_PRICE;
    }

    private List<Lotto> createLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(pickUniqueNumbersInRange(
                    LOTTO_NUMBER_MIN,
                    LOTTO_NUMBER_MAX,
                    LOTTO_NUMBER_COUNT
            )));
        }
        return lottos;
    }
}