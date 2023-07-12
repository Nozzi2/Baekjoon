/*
입력받은대로 리스트에 넣어놓으면 되는데,
만약 이미 들어왔던 인인원이라면 이전에 넣었던거 취소시키고 리스트 가장 마지막에 넣어줘야함.

그러면 자료구조 2개를 사용하면 될 것 같은데,
입력받은데로 저장하는 리스트와
몇번자리에 저장되어있는지 확인할 Map이다.

입력을 받을 때 Map에 해당 학번이 있는지 확인하고,
    없다면
        리스트에 넣어주고
        리스트의 몇번 인덱스에 저장되어있는지 Map에 넣어준다.
    있다면
        리스트에 넣어주고
        Map에 저장된 인덱스의 학번을 비활성화 해준 다음
        방금 넣었던 인덱스 위치로 갱신시켜준다.
모든 입력이 끝난 다음 주어진 인원만큼 출력시켜주는데,
비활성화되어 있는 학번을 제외하고 계속 출력시켜 준다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    static class Student {
        String number;
        boolean isAvailable; //사용 가능한 학번인지 여부

        public Student(String number) {
            this.number = number;
            this.isAvailable = true;
        }
        
        void setFalse() {
            this.isAvailable = false;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "number='" + number + '\'' +
                    ", isAvailable=" + isAvailable +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int targetCount = Integer.parseInt(st.nextToken()); //출력해야할 학번수
        int size = Integer.parseInt(st.nextToken()); //입력받을 학번 갯수
        List<Student> students = new ArrayList<>();
        Map<String, Integer> numberMap = new HashMap<>();
        
        for (int i = 0; i < size; i++) {
            String curNumber = br.readLine();
            students.add(new Student(curNumber));
            
            if (numberMap.containsKey(curNumber)) { //이미 저장되어있는 학번이라면
                int prevIdx = numberMap.get(curNumber);
                students.get(prevIdx).setFalse(); //이전학번 비활성화
            }
            numberMap.put(curNumber, i); //숫자 목록에 갱신
        }

        //인원수만큼 학번 출력
        int count=0;
        for (Student s : students) {
            if (s.isAvailable) {
                sb.append(s.number).append("\n");
                count++;
            }
            if(count == targetCount) break;
        }

        System.out.print(sb.toString());
    }
}

/*
3 8
20103324
20133221 ----------삭제
20133221
20093778 ----------삭제
20140101
01234567
20093778
20103325
 */