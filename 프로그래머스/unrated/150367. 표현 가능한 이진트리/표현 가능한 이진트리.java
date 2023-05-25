class Solution {
    static int result;
    static boolean[] binary;
	static int treeLen;
    
    public int[] solution(long[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            //1. 숫자 > 2진수로 변경
            String binaryString = Long.toBinaryString(numbers[i]);

            //2. 포화 이진트리 길이 구하기
            int length = binaryString.length();
            int exp = 1; //제곱해줄 값
            do {
                treeLen = (int) Math.pow(2, exp++) - 1;
            } while (treeLen < length); //2-1, 2^2-1, 2^3-1, ...

            //3. 이진트리문자열을 포화 이진트리 사이즈의 boolean 배열로 변경
            binary = new boolean[treeLen]; //포화이진트리를 저장할 boolean 배열
            int idx = treeLen - length; //앞부분은 더미이므로 실제 값이 저장되기 시작할 인덱스 계산
            for(int j=0; j<length; j++) { //시작할 인덱스부터 마지막 인덱스까지 이진트리값 저장
                binary[idx++] = binaryString.charAt(j) == '1';
            }

            //4. 부모노드가 더미일때 자녀노드가 true인 경우 탐색
            //위와 같은 경우라면 표현이 불가능한 이진트리임! <<<<<<<<<<핵심
            result = 1; //초기값 1이지만 possible메소드를 호출하면서 조건에 따라 0이 저장될 수 있음.
            possible(0, treeLen-1, false); // s, e, 해당 루트가 더미인지
            answer[i] = result;
        }
        return answer;

    }

    /**
    * s : 시작 인덱스
    * e : 끝 인덱스
    * isDummy : 루트가 더미인지
    */
    public static void possible(int s, int e, boolean isDummy) {
        if(result==0) return;
        
        int mid = (s+e)/2; //루트 인덱스 계산 (만약 s=e라면 해당 인덱스가 루트가 됨)
        //루트가 더미(false)일때 자식이 더미인지 검사
        //부모가 더미이고, 자식이 true라면 표현 불가능한 이진트리이므로 종료해야함.
        if(isDummy && binary[mid]) { //루트가 0이면 자식노드들에서 1이나오면 안됨
            result = 0;
            return;
        }

        // 내가 마지막 노드가 아니면 재귀
        isDummy = !binary[mid]; //루트노드가 false라면 더미 노드 true임
        if(s!=e) {
            possible(s, mid-1, isDummy); // 왼쪽
            possible(mid+1, e, isDummy); // 오른쪽
        }

    }
}