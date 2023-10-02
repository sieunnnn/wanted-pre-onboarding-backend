<br>

# 프리온보딩 백엔드 인턴십 선발과제

<br>

## ✳️ Entity Diagram
<img width="626" alt="스크린샷 2023-10-02 오후 1 16 34" src="https://github.com/sieunnnn/wanted-pre-onboarding-backend/assets/119668620/eb2975d5-7321-47ea-9f0d-90776abdd969">

- User : JobPosting = N : 1
- JobPosting : Company = N : 1

<br>
<br>

## *️⃣ 사용기술
<img src="https://img.shields.io/badge/Java-0B2C4A?style=flat&logo=java&logoColor=white" height="25px"/> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=springboot&logoColor=white" height="25px"/> <img src="https://img.shields.io/badge/JUnit5-25A162?style=flat&logo=junit5&logoColor=white" height="25px"/> <img src="https://img.shields.io/badge/Hibernate-59666C?style=flat&logo=hibernate&logoColor=white" height="25px"/> <img src="https://img.shields.io/badge/IntelliJ IDEA-black?style=flat&logo=intellijidea&logoColor=white" height="25px"/>

<br>
<br>

## 1️⃣ 채용공고 등록 / 수정
### 요구사항
회사는 채용공고를 등록 / 수정 합니다.

### 구현과정
  - 엔티티 설계
    - `@Setter` 를 사용하지 않기 위해 _JobPosting Entity_ 에 _updateJobPosting_ 메서드를 만들어 필드를 수정하였습니다. 
    - 수정 시에는 특정값만 변경하기 때문에 null 이 아닌 값은 테이블에 반영이 되지 않도록 하였습니다.
  - dto 생성
    - _JobPostingRequest_ 를 두어 등록과 사용에 둘 다 사용하였습니다.
  - service / repository 생성
  - controller 생성
    - 채용 공고 생성 Endpoint
      - `POST` job-posting/{companyId}
      - 반환값은 따로 두지 않았습니다.
    - 채용 공고 수정 Endpoint
      - `PATCH` job-posting/{companyId}/{jobPostingId}
      - 반환값은 따로 두지 않았습니다.

<br>
<br>

## 2️⃣ 채용공고 삭제
### 요구사항
회사는 채용공고를 삭제 합니다.
### 구현과정
  - 엔티티 설계
  - dto 생성
    - _JobPostingDeleteRequest_ 를 두어 삭제할 공고의 인덱스를 받았습니다.
  - service / repository 생성
  - controller 생성
    - Endpoint : `DELETE` job-posting
    - 반환값은 따로 두지 않았습니다.

<br>
<br>

## 3️⃣ 채용공고 목록
### 요구사항
채용공고를 리스트로 반환 합니다.
### 구현과정
  - 엔티티 설계
  - dto 생성
    - _JobPostingResponse_ 를 두어 요구사항에 해당하는 데이터만 가져왔습니다.
  - service / repository 생성
    - 모든 공고를 가져와 response 에 알맞은 값을 넣어 리스트 형태로 반환하였습니다.
  - controller 생성
    - Endpoint : `GET` job-posting
    - 반환값
      ```json
      [
        {
          "jobPostingId": 1,
          "companyName": "wanted",
          "national": "대한민국",
          "region": "서울",
          "recruitmentPosition": "백엔드 신입 개발자",
          "recruitmentBonus": 1500000,
          "stack": "Java"
        },
        {
          "jobPostingId": 2,
          "companyName": "wanted",
          "national": "대한민국",
          "region": "서울",
          "recruitmentPosition": "백엔드 신입 개발자",
          "recruitmentBonus": 1500000,
          "stack": "Java"
        },
        {
          "jobPostingId": 3,
          "companyName": "wanted",
          "national": "대한민국",
          "region": "서울",
          "recruitmentPosition": "프론트엔드 신입 개발자",
          "recruitmentBonus": 1500000,
          "stack": "Java"
        },
        {
          "jobPostingId": 4,
          "companyName": "wanted",
          "national": "대한민국",
          "region": "서울",
          "recruitmentPosition": "백엔드 주니어 개발자",
          "recruitmentBonus": 1000000,
          "stack": "Java"
        },
        {
          "jobPostingId": 5,
          "companyName": "wanted",
          "national": "대한민국",
          "region": "서울",
          "recruitmentPosition": "프론트엔드 주니어 개발자",
          "recruitmentBonus": 1000000,
          "stack": "Java"
        }
      ]
      ```

<br>
<br>

## 4️⃣ 채용 상세 페이지
### 요구사항
  - 채용공고를 리스트로 반환 합니다.
  - 해당 회사가 올린 다른 채용 공고가 추가적으로 포함 되어야 합니다.
### 구현과정
  - 엔티티 설계
  - dto 생성
    - _JobPostingDetailResponse_ 를 두어 요구사항에 해당하는 데이터만 가져왔습니다.
  - service / repository 생성
    - 모든 공고를 가져와 response 에 알맞은 값을 넣어 리스트 형태로 반환하였습니다.
    - **[ 가산요소 ]** : 해당 회사가 올린 다른 채용 공고가 추가적으로 포함되어야 하므로 아래와 같이 쿼리를 작성하였습니다.
      ```java
      @Query("SELECT jp.id " +
            "FROM JobPosting jp " +
                "WHERE jp.company.id = :companyId " +
                "AND jp.id != :jobPostingId"
      )
      List<Long> findOtherJobPostingsByCompanyId(@Param("companyId") Long companyId, @Param("jobPostingId") Long jobPostingId);
      ```
  - controller 생성
    - Endpoint : `GET` job-posting/detail/{jobPostingId}
    - 반환값
      ```json
      {
        "jobPostingId": 2,
        "companyName": "wanted",
        "national": "대한민국",
        "region": "서울",
        "recruitmentPosition": "백엔드 신입 개발자",
        "recruitmentBonus": 1500000,
        "stack": "Java",
        "recruitmentDetails": "원티드랩에서 백엔드 신입 개발자를 채용합니다. 자격 요건은 ...",
        "otherJobPostings": 
        [
          1,
          3,
          4,
          5
        ]
      }
      ```
<br>
<br>

## 5️⃣ 채용 공고 지원
### 요구사항
  - **가산 요소** 
  - 사용자는 채용 공고에 지원합니다.
### 구현과정
  - 엔티티 설계
  - dto 생성
    - _ApplyRequest_ 를 두어 지원할 공고 인덱스와 사용자 인덱스를 받았습니다.
  - service / repository 생성
    - JobPosting 엔티티와 User 엔티티가 1 : N 관계 이므로 JobPosting 의 유저 리스트에서 해당하는 유저가 존재하는지 여부를 검사하였습니다.
    - 유저는 1번만 지원 가능하므로, 해당 유저가 리스트에 이미 존재하는 경우 예외를 던지도록 구현하였습니다. 
  - controller 생성
    - Endpoint : `POST` user/apply
    - 반환값은 따로 두지 않았습니다.

<br>
<br>

## ✅ Unit Test
### _ApplyControllerTest_
### _JobPostingControllerTest_
- 채용공고 리스트 컨트롤러 테스트
- 채용공고 상세보기 컨트롤러 테스트
- 채용공고 수정 컨트롤러 테스트

추가 / 삭제의 경우 단순해서 위 세개의 컨트롤러만 테스트 하였습니다.

<br>
<br>

## ✴️ Commit Message Convention
| **convention** | **설명**                  |
|:--------------:|:------------------------|
|    Setting     | 프로젝트 설정 관련 작업을 하는 경우 사용 |
|      Feat      | 새로운 기능 추가 시 사용          |
|      Test      | 테스트 코드 생성 / 수정 시 사용     |
|     Refac      | 서비스 로직이나 기능 수정 시 사용     |
|      Fix       | 오류 수정 시 사용              |
|     Rename     | 폴더 이동이나 파일 이름 변경 시 사용   |
|      Docs      | 리드미 업데이트 시 사용           |

<br>
<br>
