import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Stack_sequence_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int addNum = 0;      // 다음에 추가해야할 숫자입니다.
        while (n-- > 0) {      // 입력받은 n 만큼 반복합니다.
            int inputNum = Integer.parseInt(br.readLine());   // inputNum은 입력받은 수열의 숫자입니다.
            if (addNum < inputNum) {        // 만약, 추가해야할 수보다 입력받은 수가 더 크다면, 입력받은 수까지 push를 해줘야 합니다.
                while (addNum != inputNum) {     // addNum == inputNum 일 때까지 반복문을 실행합니다.
                    st.push(++addNum);
                    sb.append("+\n");
                }
            }
            else if (st.peek() != inputNum) {     // addNum > inputNum 인 상황에서는, 스택의 맨 위 숫자가 무조건 꺼내져야 합니다.
                System.out.println("NO");          // 즉, 맨 위 숫자가 아닌 그 아래에 있는 다른 숫자를 꺼내려하면, NO를 출력하고 종료합니다.
                return;
            }
            st.pop();        // 만약, addNum > inputNum 인 상황에서, 스택의 top 요소와 inputNum 이 같다면, 그냥 pop 해주면 됩니다.
            sb.append("-\n");

        }
        System.out.println(sb);
    }
}
