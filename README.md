# <strong>💻 </strong>


## 📑 개요


- **개발 기간** : 24.02.26 ~ 24.04.05
- **프로젝트 이름** : 북 편한 세상
- **프로젝트 설명** : 서울 강남구에 있는 도서관들의 책을 모아, 어느 책이 있는지 확인 할 수 있으며, 책의 상태와 리뷰 등을 한눈에 볼 수 있는 플랫폼입니다. 


## 🤺 MovieToGather Team

- <strong>팀장</strong> 전창현
    - [github](https://github.com/MyohanMyolang)
    - 역할
      - OPEN API 활용
      - 책 검색 기능
      - 서버 배포
- <strong>부팀장</strong> 이상혁
    - [github](https://github.com/sanghyuklee1998)
    - 역할
        - Sequrity
        - 로그인
- <strong>팀원</strong> 권순형
    - [github](https://github.com/modern1s)
    - 역할
        - 책 대여, 반납
        - 책 리뷰 
        - 책 찜 기능


## **📚기술스택**
<div>
  이미지 넣을 예정
</div>

### **Backend**

- Spring Boot: 3.2.2
- Kotlin: 1.9.22
- Data
    - Spring JPA: 3.2.3
    - QueryDsl: 5.0.0
- Security
    - Spring Security: 6.2.0
    - JWT: io.jsonwebtoken:jjwt-api:0.12.3
    - Oauth 2.0
### **DB**
- PostgreSql : 14

### **Frontend**
- Html5
- CSS
- JavaScript
- react 18.2.0
- tailwind

### **Collaboration**

- Git, GitHub Issue, Discord

### **의사결정**

- **Kotlin**
  - 코틀린은 불필요한 코드를 최소하할 수 있어서, 개발자는 더 적은 코드로 더 많은 작업을 수행할 수 있으며, 코드의 가독성과 유지보수성을 향상 시킬 수 있게 되어 채택하였습니다.
  - 코틀린은 자바와 100% 상호 운영이 가능하며, 기존 자바 프로젝트에 코틀린 코드를 쉽게 추가할 수 있는 장점이 있어 채택하게 되었습니다.
  - 현대 프로그래밍 언어에서 기대하는 다양한 특징을 제공하여 채택하게 되었습니다. 

- **Spring**
  - 객체 간의 결합도를 낮추어 유지보수성과 테스 용이성을 향상시킬 수 있다고 생각하여 채택하게 되었습니다. 
  - Spring 생태계는 데이터 접근, 메시징, 웹 MVC 및 REST API 개발 보안 배치 처리등 다양한 모듈을 제공하여 손 쉽게 통합하고 확장할 수 있습니다.  

- **Github Actions**
  - 별도의 서비스로 이동할 필요없이 코드를 푸시하고 테스트 및 배포까지 한 곳에서 관리할 수 있어 채택하였습니다. 
  - 복잡한 설정 없이도 CI/CD 파이프라인을 구성할 수 있고, 프로젝트의 요구에 맞춰 유연하게 워크플로워를 설정할 수 있어서 채택하게 되었습니다. 
    
- **Spring JPA**
  - 빠른 개발을 위해서 코드를 단순화하기 위해 필요하다고 생각하여 채택하게 되었습니다. 
    
- **QueryDsl**
  - JPA에서 나올 수 있는 컴파일러 오류를 잡아내기 최적의 기술이라고 생각하여 채택하게 되었습니다. 
  - 동적 쿼리를 구현할때 간단하게 구성할 수 있어서 선택하게 되었습니다. 

- **Spring Security**
  - 

- **JWT**
  - JWT는 JSON 포맷을 기반임으로, 대부분의 프로그래밍 언어와 플랫폼에서 쉽게 사용할 수 있어서 채택하게 되었습니다.
  - 인증 정보가 토큰안에 포함되어 있기 때문에 인증과정이 간단하고 효율적이라고 생각하였습니다. 

- **PostgreSql**
  - 오픈 소스이며 비용 효율적이라고 생각하여 채택을 하게되었습니다. 
  - 높은 수준의 확장성을 제공하여, 대용량의 데이터와 많은 사용자들 처리할 수 있습니다.
  - 또한 확장 모듈을 통해 기능을 추가할 수 있습니다.

- **RestClient**
    - 

## **배포환경**
- Front-End : Vercel
- Back-End : LightSail


## 주요기능

### OPEN API를 통한 책 정보 가져오기
- OPEN API를 통해 책 검색 가능
- 도서관의 책 정보 획득

### 회원
- 회원가입/로그인/유저 정보 확인
- Spring Security 활용
- 소셜 로그인만으로 회원 관리 진행

### 책 대여, 반납, 책 상태 확인
- 책이 있는지 없는지 확인
- 책 대여하여 없을 경우 찜을 한 사용자에게 책 정보를 추후에 알림
- 책의 평점및 리뷰를 확인

## 🏆 프로젝트 산출물

- [팀 노션](https://www.notion.so/BLife-44624db8f22d448aa6c9b59b793bd7ae)