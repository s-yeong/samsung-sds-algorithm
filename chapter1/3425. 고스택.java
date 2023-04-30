import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        /**
         * 1. X 범위 10^9 => 10억, 곱하는 경우 int 볌위 벗어남 => long
         * 2. 두번째 숫자에 접근할 일이 많으므로 스택 보다는 배열로 저장 (스택 구현 느낌)
         * 3. 프로그램 영역(명령을 처리하는 영역)과 입력 영역으로 분리
         * 4. 프로그램 에러가 발생했을 경우, 수행 멈추고 어떠한 명령도 수행X
         */
        ArrayList<String> comm_list = new ArrayList<>();
        while (true) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            String comm = st.nextToken();

            // QUIT인 경우, break
            if ("QUIT".equals(comm)) {
                break;
            }

            // END인 경우, 프로그램 끝
            else if ("END".equals(comm)) {

                // -> 입력 영역
                input(br, comm_list, sb);
                // 명령어 초기화
                comm_list.clear();
                continue;
            }

            // 명령어 저장
            comm_list.add(comm);

            // NUM인 경우, 숫자까지 받기
            if ("NUM".equals(comm)) {
                comm_list.add(st.nextToken());
            }
        }

        /**
         * 출력
         */
        System.out.print(sb);
    }

    static void input(BufferedReader br, ArrayList<String> comm_list, StringBuilder sb) throws IOException {

        // 각 input에 대해 프로그램이 돌아가는 것.
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int num = Integer.parseInt(br.readLine());
            String result = program(num, comm_list);
            sb.append(result).append("\n");
        }
        // 빈 줄 출력 및 빈 줄 입력받기
        sb.append("\n");
        br.readLine();
    }

    static String program(int num, ArrayList<String> comm_list) {

        long[] stack = new long[1001];
        // 저장
        stack[0] = num;
        int top = 0;
        boolean is_error = false;

        for(int i=0; i<comm_list.size(); i++) {

            if(comm_list.get(i).equals("NUM")) {
                int x = Integer.parseInt(comm_list.get(++i));
                // x를 스택의 가장 위에 저장
                stack[++top] = x;
            }
            else if(comm_list.get(i).equals("POP")) {
                if(top < 0) {
                    is_error = true;
                    break;
                }
                // 스택 가장 위의 숫자를 제거
                top--;
            }
            else if(comm_list.get(i).equals("INV")) {
                if(top < 0) {
                    is_error = true;
                    break;
                }
                // 첫 번째 수의 부호를 바꾸기
                stack[top] = stack[top] * -1;
            }
            else if(comm_list.get(i).equals("DUP")) {
                if(top < 0) {
                    is_error = true;
                    break;
                }
                // 첫 번째 숫자를 하나 더 스택의 가장 위에 저장
                stack[top+1] = stack[top];
                top++;  // stack[++top] = stack[top]인 경우, ++top 부터 수행되기 때문에 안됨
            }
            else if(comm_list.get(i).equals("SWP")) {
                if(top-1 < 0) {
                    is_error = true;
                    break;
                }
                // 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾸기
                long tmp = stack[top];
                stack[top] = stack[top-1];
                stack[top-1] = tmp;
            }
            else if(comm_list.get(i).equals("ADD")) {
                if(top-1 < 0) {
                    is_error = true;
                    break;
                }
                // 첫 번째 숫자와 두 번째 숫자를 더하기
                long add = stack[top-1] + stack[top];

                if(Math.abs(add) > 1000000000L) {
                    is_error = true;
                    break;
                }
                stack[--top] = add;
            }
            else if(comm_list.get(i).equals("SUB")) {
                if(top-1 < 0) {
                    is_error = true;
                    break;
                }
                // 첫 번째 숫자와 두 번째 숫자를 빼기
                long sub = stack[top-1] - stack[top];
                if(Math.abs(sub) > 1000000000L) {
                    is_error = true;
                    break;
                }
                stack[--top] = sub;
            }
            else if(comm_list.get(i).equals("MUL")) {
                if(top-1 < 0) {
                    is_error = true;
                    break;
                }
                // 첫 번째 숫자와 두 번째 숫자를 곱하기
                long mul = stack[top-1] * stack[top];
                if(Math.abs(mul) > 1000000000L) {
                    is_error = true;
                    break;
                }
                stack[--top] = mul;
            }
            else if(comm_list.get(i).equals("DIV")) {
                if(top-1 < 0 || stack[top] == 0) {
                    is_error = true;
                    break;
                }

                // 첫 번째 숫자와 두 번째 숫자를 나눈 몫 저장하기
                // 음수인 경우, 절대값을 씌운 뒤 계산
                int count = 0;
                if(stack[top] < 0) {
                    count++;
                    stack[top] = stack[top] * -1;
                }
                if(stack[top-1] < 0) {
                    count++;
                    stack[top-1] = stack[top-1] * -1;
                }

                long div = stack[top-1] / stack[top];
                // 피연산자중 음수가 한 개 -> 몫의 부호 음수
                if(count == 1) div = div * -1;
                stack[--top] = div;
            }
            else if(comm_list.get(i).equals("MOD")) {
                if(top-1 < 0 || stack[top] == 0) {
                    is_error = true;
                    break;
                }
                // 첫 번째 숫자와 두 번째 숫자를 나눈 나머지 저장하기
                // 음수인 경우, 절대값을 씌운 뒤 계산
                boolean is_negative = false;
                if(stack[top] < 0) {
                    stack[top] = stack[top] * -1;
                }
                if(stack[top-1] < 0) {
                    stack[top-1] = stack[top-1] * -1;
                    is_negative = true;
                }

                long mod = stack[top-1] % stack[top];
                // 부호는 피제수의 부호
                if(is_negative) mod = mod * -1;

                stack[--top] = mod;
            }
        }

        if(is_error  || top != 0) return "ERROR";
        else return String.valueOf(stack[top]);
    }
}
