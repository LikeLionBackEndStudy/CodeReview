package algorithm.programmers;

import java.util.*;

public class ParkingFee {
    /* 문제 이해 *
     * 주차요금 공식 = 기본요금 + [(누적주차시간 - 기본시간) / 단위시간(분)] * 단위요금
     * 단, (누적주차시간 - 기본시간) / 단위시간(분) 과정이 소수점이 생긴다면 올림(+1)처리
     * 차량의 누적 주차 시간을 구해서 주차 요금 계산
     * 만약 출차 내역이 없다면 23:59에 출차된 것으로 간주!
     * 차량 번호가 작은 자동차부터 배열에 저장!
     * */
    static Map<String, String> parkingMap = new HashMap<>();
    // K : 차량 번호, V : 입차 시간
    static Map<String, Integer> answerMap = new HashMap<>();
    // K : 차량 번호, V : timeSum(누적 주차 시간)

    public static void addData(String carNumber, String parkingTime, String parkingStatus) {
        // @Param 정리 : carNumber = 차량번호, parkingTime = 입/출차 시간, parkingStatus : 입/출차 상태
        switch (parkingStatus) {
            case "IN":
                // 입차일 때는 parkingMap에 데이터 넣기
                parkingMap.put(carNumber, parkingTime);
                break;
            case "OUT":
                // 출차일 때는 parkingMap에 있는 입차기록과 parkingTime을 사용해서 누적 주차 시간 구하기
                String inTime = parkingMap.get(carNumber);
                int timeSum = getTimeSum(inTime, parkingTime);
                answerMap.put(carNumber, answerMap.getOrDefault(carNumber, 0) + timeSum);
                parkingMap.remove(carNumber);
                break;
        }
    }
    public static int getTimeSum(String inTime, String outTime) {
        // 출차 시간 - 입차 시간을 분단위로 환산
        // 05:34, 07:59
        String[] inTimeArr = inTime.split(":");
        String[] outTimeArr = outTime.split(":");
        int hourSum = (Integer.parseInt(outTimeArr[0]) - Integer.parseInt(inTimeArr[0])) * 60;
        int minuteSum = Integer.parseInt(outTimeArr[1]) - Integer.parseInt(inTimeArr[1]);
        return (hourSum + minuteSum);
    }
    public static int getParkingFees(int[] fees, int timeSum) {
        int defaultTime = fees[0], defaultCost = fees[1], perMinute = fees[2], perCost = fees[3];
        // @Param 정리 : defaultTime = 기본시간, defaultCost = 기본요금, perMinute : 단위시간, perCost : 단위요금

        if (timeSum <= defaultTime) {
            // 누적 주차 시간이 기본 시간 이하라면 기본요금만 청구
            return defaultCost;
        } else {
            int timeDiffPerMinute = (timeSum - defaultTime) / perMinute;
            if ((timeSum - defaultTime) % perMinute == 0) {
                // 문제 중 [a] 과정이 단위 시간으로 나누어 떨어지지 않으면 올림 처리
                return defaultCost + (timeDiffPerMinute * perCost);
            } else {
                return defaultCost + ((timeDiffPerMinute + 1) * perCost);
            }
        }
    }
    public static int[] solution(int[] fees, String[] records) {
        for (String record : records) {
            // 주차 기록을 돌면서 parkingMap 데이터 추가!
            StringTokenizer st = new StringTokenizer(record, " ");
            String parkingTime = st.nextToken();
            String carNum = st.nextToken();
            String parkingStatus = st.nextToken();
            addData(carNum, parkingTime, parkingStatus);
        }
        // 만약 입차 기록만 있고, 출차 기록이 없는게 존재한다면 아래 로직 진행
        if (parkingMap.size() != 0) {
            // addData OUT 부분만 다시 실행
            for (String carNum : parkingMap.keySet()) {
                String parkingTime = parkingMap.get(carNum);
                int timeSum = getTimeSum(parkingTime, "23:59");
                answerMap.put(carNum, answerMap.getOrDefault(carNum, 0) + timeSum);
            }
            parkingMap.clear();
        }
        System.out.println("answerMap(누적 주차 시간) = " + answerMap);

        for (String carNum : answerMap.keySet()) {
            int parkingFees = getParkingFees(fees, answerMap.get(carNum));
            answerMap.put(carNum, parkingFees);
        }
        System.out.println("answerMap(누적 주차 요금) = " + answerMap);

        List<String> keySet = new ArrayList<>(answerMap.keySet());
        System.out.println("keySet(차량 번호 정렬 전) = " + keySet);
        Collections.sort(keySet);
        System.out.println("keySet(차량 번호 정렬 후) = " + keySet);

        int[] answer = new int[keySet.size()];
        int idx = 0;

        for (String carNum : keySet) {
            answer[idx] = answerMap.get(carNum);
            idx++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
//        int[] fees = {120, 0, 60, 591};
//        int[] fees = {1, 461, 1, 10};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
//        String[] records = {"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"};
//        String[] records = {"00:00 1234 IN"};
        System.out.println(Arrays.toString(solution(fees, records)));
    }
}
