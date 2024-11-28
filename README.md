# 환전 시스템 구축하기

## 프로젝트 소개
고객이 원하는 통화(화폐)로의 환전을 도와주는 기능을 하는 시스템이다. 
고객은 원(krw)을 가지고 있고 다른 화폐(ex:달러, 엔화)로 환율에 맞게 환전을 해준다.


## 목표
JPA의 연관관계를 이해하고 예외처리를 잘 다루는 개발자가 되자!


## 개발 환경

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

IDE : Intellij IDEA 2024.2.3

언어 : Java

프레임 워크 : 스프링 부트 3.3.5

JDK : corretto-17.0.13

RDBMS : MYSQL_Server 9.1.0 

버전관리도구 : GitHub


## 1. 요구사항
### Lv 1. 고객(User)과 통화(Currency) 복잡한 연관관계 `필수`

- [ ]  **환전 요청 중간 테이블 생성**
    - [ ]  필드: `고객 고유 식별자`, `환전 대상 통화 식별자`, `환전 전 금액`, `환전 후 금액`, `상태`
    - 예시
        
        
        | 컬럼명 | 설명 | 예시 |
        | --- | --- | --- |
        | id | 환전 요청 ****고유 식별자 | 1 |
        | user_id | 고객 고유 식별자
        (User 테이블 ID) | 1 |
        | to_currency_id | 환전 대상 통화 식별자
        (Currency 테이블 ID) | 1 |
        | amount_in_krw | 환전 전 금액
        (원화 기준) | 10000 |
        | amount_after_exchange | 환전 후 금액 | 6.99 |
        | status | 상태 | normal |
        | created_at | 생성일자 | 2024-11-18 16:42:03.000000 |
        | modified_at | 수정일자 | 2024-11-18 16:42:03.000000 |
- [ ]  **고객과 통화 테이블 간 연관관계**
    - [ ]  한 고객이 여러 통화로 환전할 수 있고 한 통화는 여러 고객들에 의해 환전될 수 있습니다.
    - [ ]  `환전 요청 테이블`은 중간 테이블이고 `User`와 `Currency` 간 관계를 관리합니다.

### Lv 2. 환전 요청 CRUD `필수`

- [ ]  C: 환전 요청 수행
    - [ ]  `환전 대상 통화 식별자`가 `Currency` 테이블에 가지고 있는 환율을 기준으로 환전을 수행합니다.
    - [ ]  환전 후 금액은 환전 전 금액을 환율로 나눈 결과입니다.
        - `환전 후 금액` 계산식 (참고)
            - [ ]  `환전 후 금액 = 환전 전 금액 / 환율`
        - 예시
            
            ```java
            환전 전 금액이 10,000원이고, 환율이 900원/1달러인 경우
            
            환전 후 금액: 10,000원 / 900원 = 11.11
            ```
            
    - [ ]  `환전 후 금액`에 대해 소수점 두번째 반올림 수행
- [ ]  R: `고객 고유 식별자`를 기반으로 특정 고객이 수행한 환전 요청 조회
    - [ ]  조건
        - [ ]  `고객 고유 식별자`: 환전 요청 테이블에 있는 User ID
- [ ]  U: 특정 환전 요청 상태를 취소로 변경
    - [ ]  상태 값은 `normal`, `cancelled`
- [ ]  D: 고객이 삭제될 때 해당 고객이 수행한 모든 환전 요청도 삭제
    - [ ]  키워드
        - [ ]  `영속성 전이`, `cascade`

### Lv 3. 예외 처리 `필수`

- 모든 상황에 대해 유효성 검사와 예외 처리를 구현하지 않아도 됩니다. 핵심적으로 중요하다고 생각하는 1~2가지 항목에 대해 수행하면 충분합니다.
- [ ]  **유효성 검사 추가**: 입력 값에 대한 유효성 검사를 추가하여 정책에 맞는 데이터만 취합
    - [ ]  `3-Layered Architecture` 에서 유효성 검사가 어떤 계층의 책임인지 고민해보세요!
    - [ ]  이메일, 일자 데이터, 길이 등
    - [ ]  키워드
        - [ ]  `Validation`
- [ ]  **예외 처리 강화**: 적절한 예외 처리 로직을 추가하여 오류 발생 시 사용자에게 명확한 피드백 제공
    - [ ]  API 예외처리
        - [ ]  키워드
            - [ ]  `GlobalExceptionHandler`
    - [ ]  단순히 400, 500 에러코드만 보내지 않고 더 많은 정보를 포함하여 에러 메세지를 전달
        - [ ]  아래는 예시 형식입니다.
            
            ```jsx
            {
                "errorCode": "ERR001",
                "errorMessage": "요청값의 형식이 맞지 않습니다."
            }
            ```
            
            - [ ]  요청값의 형식이 맞지 않습니다.
            - [ ]  네트워크 요청에 실패했습니다. 다시 시도해주시기 바랍니다.
            - [ ]  코드 형태를 고민해봐도 좋겠습니다.

### Lv 4. PostConstruct 적용 `도전`

- [ ]  조건
    - [ ]  스프링이 구동될 때 통화 테이블에 있는 환율이 0이거나 음수이거나 지정된 범위를 벗어나는 경우
- [ ]  유효하지 않은 값으로 간주하고 로그를 기록하세요.
- [ ]  키워드
    - [ ]  `@Component`, `@Slf4j`

### Lv 5. JPQL `도전`

- [ ]  고객의 모든 환전 요청을 그룹화하여 조회
- [ ]  반환해야하는 컬럼
    - [ ]  해당 고객이 수행한 환전 요청 데이터들의 총 row 수
    - [ ]  해당 고객이 환전을 요청한 총 금액
    - [ ]  예시
        
        ```
        [
            {
                "count": 3,
                "totalAmountInKrw": 50000.00
            }
        ]
        ```
        
- [ ]  키워드
    - [ ]  `@Query`, `Group By`, `SUM`, `COUNT`

### Lv 6. 달러 이외 통화를 환전할 수 있도록 수정 `도전`

- [ ]  `Currency` 테이블에 다른 통화에 대한 데이터를 추가합니다.
- [ ]  현재는 달러를 기준으로 개발하였습니다. 그래서 다른 통화로 환전하면 자리수가 맞지 않는 경우가 존재합니다.
    - [ ]  각 통화마다 다른 자리수를 적용하도록 개발합니다.
    - [ ]  예시
        
        ```java
        환전 후 금액 = 환전 전 금액 / 환율
        
        10000원에 대해 900원 환율을 적용해서 엔화로 바꾸면 1111円이 나와야 하지만
        현재 구조에서는 11.11로 출력됩니다.
        
        달러인 경우 그대로 11.11$ 출력하고 엔화인 경우에는 1111円 형식으로 출력되어야 합니다.
        ```





## 2. API 명세서


## 3. ERD

![ERD_currency](https://github.com/user-attachments/assets/10cf48db-64ab-4b5c-bec5-a8dc8f785290)

        
## 4. 예시 API 결과 이미지

- 환전 정보 생성
![환전 정보 생성](https://github.com/user-attachments/assets/ac7956ba-4704-41cb-970a-aa60346d40b2)

- 환전 요청
![환전 요청](https://github.com/user-attachments/assets/1fd77e92-5e63-4d25-9853-f3b9de96af68)

- 환전 요청 조회
![환전 요청 조회](https://github.com/user-attachments/assets/70097477-bd21-47e9-a420-486a7ddf9f0e)

- 환전 취소
![환전 취소](https://github.com/user-attachments/assets/c7ff90f7-95d9-4c8a-b919-d9ea83e92655)

- 고객 삭제 > 환전 요청 삭제
![DB](https://github.com/user-attachments/assets/1bac3dc4-725d-4849-b275-068b72d07d70)
  
![삭제](https://github.com/user-attachments/assets/46a86f08-3daf-4748-bd7e-7c51339dea9c)

![DB 삭제](https://github.com/user-attachments/assets/5585403f-3b4e-4108-8c71-d538a93da814)

- 다른 통화 기능 가능
![엔화](https://github.com/user-attachments/assets/1467a2f6-290f-40d7-a922-c32cafcc7209)

