# [Silver III] 2×n 타일링 2 - 11727 

[문제 링크](https://www.acmicpc.net/problem/11727) 

### 성능 요약

메모리: 11992 KB, 시간: 84 ms

### 분류

다이나믹 프로그래밍(dp)

### 문제 설명

<p>2×n 직사각형을 1×2, 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.</p>

<p>아래 그림은 2×17 직사각형을 채운 한가지 예이다.</p>

<p style="text-align: center;"><img alt="" src="https://www.acmicpc.net/upload/images/t2n2122.gif" style="height:59px; width:380px"></p>

### 입력 

 <p>첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)</p>

### 출력 

 <p>첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.</p>

### 풀이방법

- 17:30 ~ 브레인 스토밍
    - 이전에 풀어봤던 DP문제이다.
    
    <aside>
    💡 f1 = 1
    f2 = 3
    f3 = f1*2 + f2
    
    
    </aside>
    -  fn = f(n-2)*2 + f(n-1)
    - 대신 범위를 봤을 때 결과값이 굉장히 큰 수일 수 있기 때문에 int도 못쓰고 long도 못쓴다.
    BigInteger를 써야만 풀 수 있는 문제임
- 17:55 풀이 완료! (25분)
