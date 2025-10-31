package lotto;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("구임금액을 입력해 주세요.");
        int lottoPrice = Integer.parseInt(readLine());
        System.out.println();

        int lottoCnt = lottoPrice / 1000;
        System.out.println(lottoCnt + "개를 구매했습니다.");

        List<Lotto> buyLottos = new ArrayList<Lotto>();
        for(int i=0;i<lottoCnt;i++) {
            List<Integer> lottoNumbers = pickUniqueNumbersInRange(1,45,6);
            Lotto newLotto = new Lotto(lottoNumbers);
            buyLottos.add(newLotto);
            System.out.println(lottoNumbers);
        }
        System.out.println();

        System.out.println("당첨 번호를 입력해 주세요.");
        String[] winningNumbersStr = readLine().split(",");
        System.out.println();

        List<Integer> winningNumbers = new ArrayList<>();
        for(String numberStr : winningNumbersStr) {
            winningNumbers.add(Integer.parseInt(numberStr.trim()));
        }

        System.out.println("보너스 번호를 입력해 주세요.");
        int bonusNumber = Integer.parseInt(readLine());
        System.out.println();

        System.out.println("당첨 통계");
        System.out.println("---");

        int[] matching = new int[5];
        long totalMoney = 0;
        for(Lotto myLotto : buyLottos) {
            boolean checkBonus = false;
            int sameCnt = 0;
            List<Integer> lottoNumbers = myLotto.getNumbers();

            for(int lottoNumber : lottoNumbers) {
                if(winningNumbers.contains(lottoNumber)) {
                    sameCnt++;
                }
            }
            if(lottoNumbers.contains(bonusNumber)) {
                checkBonus = true;
            }

            if(sameCnt == 3) {
                matching[0]++;
                totalMoney += 5000;
            }
            else if(sameCnt == 4) {
                matching[1]++;
                totalMoney += 50000;
            }
            else if(sameCnt == 5 && !checkBonus) {
                matching[2]++;
                totalMoney +=1500000;
            }
            else if(sameCnt == 5 && checkBonus) {
                matching[3]++;
                totalMoney += 30000000;
            }
            else if(sameCnt == 6) {
                matching[4]++;
                totalMoney += 2000000000;
            }

        }
        System.out.println("3개 일치 (5,000원) - " + matching[0] + "개");
        System.out.println("4개 일치 (50,000원) - " + matching[1] + "개");
        System.out.println("5개 일치 (1,500,000원) - " + matching[2] + "개");
        System.out.println("5개 일치, 보너스  볼 일치 (30,000,000원) - " + matching[3] + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + matching[4] + "개");

        double profitRate = (double) totalMoney / lottoPrice * 100.0;
        System.out.println("총 수익률은 " + String.format("%.1f", profitRate) + "%입니다.");
    }
}
