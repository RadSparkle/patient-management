
# 프로젝트 상세정보

## 개요

Rest API로 환자 관리 시스템 개발



## 실행방법
1. 클린
   
   - ./gradlew clean

2. querydsl 파일 생성을위한 컴파일

   - ./gradlew compileQuerydsl

3. Spring run


## 개발환경
- Java 11
- Spring boot 2.7
- JPA, QueryDsl
- H2 (embedded)
- Port 7050

## DB
- resources/database/data.sql - 기본 데이터 입력

- resources/database/schema.sql - 기본 테이블 생성

## 패키지 구조 
com.hd.patient
  - api - 환자, 병원, 방문 에 대한 API
  - common - 기본 리턴 객체클래스파일
  - config - JPA 설정파일 및 스웨거 설정파일
  - enums - 검색 관련 Enum 클래스



## 단위 테스트
- PatientRepositoryTest.class
  1. 환자 리스트 전체 조회
  2. 환자 리스트 조건 조회
  3. 환자 생성
  4. 환자 수정
  5. 환자 삭제

- Visi tRepositoryTest.class
  1. 방문 등록


## API 엔드포인트 

### swagger 주소
(http://localhost:7050/swagger-ui/)


### 1. 환자 리스트 전체 조회 
- (GET /api/1.0/search/name)


### 2. 환자 리스트 조건 조회 
- (GET /api/1.0/search/name?keyword=홍길동)


### 2. 환자 정보 입력 
- (POST /api/1.0/patient)


### 3. 환자 정보 삭제 
- (DELETE /api/1.0/patient)


### 4. 환자 정보 수정 
- (PATCH /api/1.0/patient)


### 5. 병원 정보 입력 
- (POST /api/1.0/hospital)


### 6. 병원 정보 삭제 
- (DELETE /api/1.0/hospital)


### 7. 병원 정보 수정 
- (PATCH /api/1.0/hospital)

### 8. 방문 정보 입력
- (POST /api/1.0/visit)


