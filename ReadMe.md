# 챕터 3 지도앱

검색어를 통한 구글맵 지도 검색, 내위치를 표시할 수 있는 앱

## 시작
안드로이드 프로젝트 생성.
프로젝트에서 코루틴 이용한 비동기 프로그래밍 설정
API 연결을 위한 okhttp3, retrofit2 라이브러리
구글맵 사용
인텐트를 통한 지도와 관련된 데이터 넘기기

## 화면
검색화면 (찾고싶은 위치 검색)
지도 위치 화면 (지도, 현재 내 위치 찾기 기능)

### 검색화면
- POI
- Retrofit (이용 시 API를 통해 받아오므로 IO쓰레드 사용 해야함. (코루틴) )
- GSON
- RecyclerView
- Coroutines

### 지도 위치 화면
- Google Map
- Intent

### 현재 내 위치 기능
- Google Map
- POI Geo Reverse (POI API를 통해 내 위치 받아오기)
- Retrofit
- GSON

## 구현 단계
1. 리사이클러뷰 구현 Mocking 데이터 뿌리기
2. Tmap POI 데이터
3. GoogleMap
4. 추가 기능 구현


