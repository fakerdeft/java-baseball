package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");
        playGame();
    }//public static void main

    public static List<Integer> getUserNumber() {
        List<Integer> user = new ArrayList<>();
        String userRandomNumber = "";
        int i = 0;

        System.out.print("숫자를 입력해주세요: ");
        userRandomNumber = exceptReadingLine(Console.readLine());
        while (user.size() < 3) {
            if (!user.contains(userRandomNumber.substring(i, i + 1))) {
                user.add(Integer.parseInt(userRandomNumber.substring(i, i + 1)));
                i++;
            }
        }
        return user;
    }//public static void userNumber

    public static String exceptReadingLine(String readLine) {
        if (readLine.length() != 3) {
            throw new IllegalArgumentException("잘못된 입력. 시스템 종료");
        }
        if (readLine.charAt(0) == readLine.charAt(1) || readLine.charAt(1) == readLine.charAt(2) || readLine.charAt(0) == readLine.charAt(2)) {
            throw new IllegalArgumentException("잘못된 입력. 시스템 종료");
        }
        if (!readLine.matches("-?\\d+")) {
            throw new IllegalArgumentException("잘못된 입력. 시스템 종료");
        }
        return readLine;
    }//public static String exceptReadingLine

    public static List<Integer> getEnemyNumber() {
        List<Integer> enemy = new ArrayList<>();

        while (enemy.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!enemy.contains(randomNumber)) {
                enemy.add(randomNumber);
            }
        }
        return enemy;
    }//public static void enemyNumber

    public static int countStrikeAndBall(List<Integer> user, List<Integer> enemy) {
        Map<String, Integer> result = new HashMap<>();
        int strike = 0;
        int ball = 0;

        for (int i = 0; i < 3; i++) {
            if ((int) user.get(i) == enemy.get(i)) {
                strike++;
            } else if (enemy.contains(user.get(i))) {
                ball++;
            }
        }
        result.put("strike", strike);
        result.put("ball", ball);
        return compareUserWithEnemy(result);
    }//public static void countStrikeAndBall

    public static int compareUserWithEnemy(Map<String, Integer> result) {
        if (result.get("ball") != 0) {
            System.out.print(result.get("ball") + "볼 ");
        }
        if (result.get("strike") != 0) {
            System.out.print(result.get("strike") + "스트라이크");
            if (result.get("strike") == 3) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                return Integer.parseInt(Console.readLine());
            }
        }
        if (result.get("ball") == 0 && result.get("strike") == 0) {
            System.out.print("낫싱");
        }
        return 0;
    }//public static int compareUserWithEnemy


}
