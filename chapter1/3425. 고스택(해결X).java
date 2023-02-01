import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        /*
        1. 스택의 가장 위에 저장된 수를 첫 번째 수
        2. 숫자가 부족해서 연산을 수행할 수 없을 때, 0으로 나눴을 때 (DIV, MOD),
        연산 결과의 절댓값이 109를 넘어갈 때는 모두 프로그램 에러
        3-1. 나눗셈의 피연산자에 음수가 있을 때는, 그 수를 절댓값을 씌운 뒤 계산
        3-2. 몫과 나머지의 부호는 다음과 같이 결정한다. 피연산자중 음수가 한 개일때는 몫의 부호가 음수이다.
        이 경우를 제외하면 몫의 부호는 항상 양수
        3-3. 나머지의 부호는 피제수의 부호와 같다
         */

        /*
        1. 입력 받는 부분 -> 메서드
        2. 해당하는 프로그램 메서드
         */
        while (true) {

            // 입력 받고 명령어 저장
            ArrayList<String> command_list = new ArrayList<>();

            // false = "QUIT"인 경우 종료
            if(!input(br, command_list)) break;

            Stack<Long> stack = new Stack<>();
            long N = Long.parseLong(br.readLine());
            while(N-->0) {
                long num = Long.parseLong(br.readLine());
                stack.push(num);
                boolean isError = program(stack, command_list);
                // 프로그램 에러 발생하는 경우(true), 스택에 저장되어 있는 숫자가 1개가 아니라면 -> "ERROR"
                if(stack.size() != 1 || isError) {
                    sb.append("ERROR").append("\n");
                }
                else {
                    sb.append(stack.pop()).append("\n");
                }
            }
            br.readLine();
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static boolean input(BufferedReader br, ArrayList<String> list) throws IOException {
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            // QUIT -> 끝
            if (command.equals("QUIT")) return false;

            // 명령 끝
            if (command.equals("END")) break;

            list.add(command);
            // NUM인 경우 숫자까지 저장
            if(command.equals("NUM")) list.add(st.nextToken());
        }
        return true;
    }

    static boolean program(Stack<Long> stack, ArrayList<String> list) {

        for (int i=0; i<list.size(); i++) {

            String command = list.get(i);

            // X를 스택의 가장 위에 저장
            if (command.equals("NUM")) {
                long num = Long.parseLong(list.get(++i));
                stack.push(num);
            }
            // 스택 가장 위의 숫자를 제거
            else if (command.equals("POP")) {
                if(stack.isEmpty()) return true; // 에러
                stack.pop();
            }
            // 첫 번째 수의 부호를 바꾼다
            else if (command.equals("INV")) {
                if(stack.isEmpty()) return true; // 에러
                long n = stack.pop();
                stack.push(-n);
            }
            // 첫 번째 숫자를 하나 더 스택의 가장 위에 저장
            else if (command.equals("DUP")) {
                if(stack.isEmpty()) return true; // 에러
                stack.push(stack.peek());
            }
            // 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾼다.
            else if (command.equals("SWP")) {
                if(stack.size() < 2) return true;   // 에러
                long tmp_first = stack.pop();
                long tmp_second = stack.pop();
                stack.push(tmp_first);
                stack.push(tmp_second);
            }
            // 첫 번째 숫자와 두 번째 숫자를 더한다
            else if (command.equals("ADD")) {
                if(stack.size() < 2) return true;   // 에러
                long tmp_first = stack.pop();
                long tmp_second = stack.pop();
                long sum = tmp_first + tmp_second;
                stack.push(sum);
            }
            // 첫 번째 숫자와 두 번째 숫자를 뺀다 (두 번째 - 첫 번째)
            else if (command.equals("SUB")) {
                if(stack.size() < 2) return true;   // 에러
                long tmp_first = stack.pop();
                long tmp_second = stack.pop();
                long sub = tmp_second - tmp_first;
                stack.push(sub);
            }
            // 첫 번째 숫자와 두 번째 숫자를 곱한다
            else if (command.equals("MUL")) {
                if(stack.size() < 2) return true;   // 에러
                long tmp_first = stack.pop();
                long tmp_second = stack.pop();
                long mul = tmp_first * tmp_second;
                stack.push(mul);
            }
            //첫 번째 숫자로 두 번째 숫자를 나눈 몫을 저장
            else if (command.equals("DIV")) {
                if(stack.size() < 2) return true;   // 에러
                long tmp_first = stack.pop();
                long tmp_second = stack.pop();
                if(tmp_first == 0) return true;     // 에러

                // 피연산자중 음수가 한 개일때는 몫의 부호가 음수
                int minusCnt = 0;
                if(tmp_first < 0) {
                    tmp_first = Math.abs(tmp_first);
                    minusCnt++;
                }
                if(tmp_second < 0) {
                    tmp_second = Math.abs(tmp_second);
                    minusCnt++;
                }
                long div = tmp_second / tmp_first;
                if(minusCnt == 1) stack.push(-div);
                else stack.push(div);
            }
            //첫 번째 숫자로 두 번째 숫자를 나눈 나머지를 저장
            else if (command.equals("MOD")) {
                if(stack.size() < 2) return true;   // 에러
                long tmp_first = stack.pop();
                long tmp_second = stack.pop();
                if(tmp_first == 0) return true;     // 에러
                boolean isMinus = false;
                if(tmp_first < 0) tmp_first = Math.abs(tmp_first);
                if(tmp_second < 0) {
                    tmp_second = Math.abs(tmp_second);
                    isMinus = true;
                }
                long mod = tmp_second % tmp_first;
                if(isMinus) stack.push(-mod);
                else stack.push(mod);
            }
            if(!stack.isEmpty() && stack.peek() > 1e9) return true; // 에러
        }
        return false;
    }
}