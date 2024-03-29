# [Silver I] 나이트의 이동 - 7562 

[문제 링크](https://www.acmicpc.net/problem/7562) 

### 성능 요약

메모리: 60576 KB, 시간: 364 ms

### 분류

너비 우선 탐색(bfs), 그래프 이론(graphs), 그래프 탐색(graph_traversal)

### 문제 설명

<p>체스판 위에 한 나이트가 놓여져 있다. 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다. 나이트가 이동하려고 하는 칸이 주어진다. 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?</p>

<p><img alt="" src="https://www.acmicpc.net/upload/images/knight.png" style="height:172px; width:175px"></p>

### 입력 

 <p>입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.</p>

<p>각 테스트 케이스는 세 줄로 이루어져 있다. 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다. 체스판의 크기는 l × l이다. 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다. 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.</p>

### 출력 

 <p>각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.</p>

### 출력 

 <p>이전 탐색은 상하좌우 4방이였는데, 나이트는 8방으로 탐색해야한다.</p>
 <p>8방으로 탐색해서 방문했는지 여부를 확인하고, 만약 방문했었다면 return하여 그 방향은 더 탐색하지 않도록 한다.</p>
 <p>단 엉뚱한 방향으로 가지 않게 이동한 방향은 r방향이든 c방향이든 남은 거리가 줄어들어야만 한다. (둘중 한방향은 늘어나도 다른 한방향만 줄어들기만 하면 된다!)</p>
 <p>높이가 한개씩 늘어날때마다 거리가 하나씩 늘어나는 것이므로, 도착점에 도달했을 때의 높이를 출력하면 최소 이동횟수가 된다.</p>
 <p>▼반례에 대한 조건 추가▼</p>
 <p>위에서 엉뚱한 방향으로 가지 않기 위한 조건은 도착점으로부터 1칸 떨어져있는 경우에는 제외시켜줘야한다. 그렇지 않으면 최소거리를 만족하지 못하게 된다.</p>
