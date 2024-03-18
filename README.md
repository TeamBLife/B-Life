## 📑 개요


- **개발 기간** : 24.02.26 ~ 24.04.05
- **프로젝트 이름** : 북 편한 세상
- **프로젝트 설명** : 도서관들의 책을 모아, 어느 책이 있는지 확인 할 수 있으며, 책의 상태와 리뷰 등을 한눈에 볼 수 있는 플랫폼입니다. 


## Blife

- <strong>팀장</strong> 전창현
    - [github](https://github.com/MyohanMyolang)
    - 역할
      - OPEN API 활용
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

- **Spring**

- **Github Actions**
    
- **Spring JPA**
    
- **QueryDsl**

- **Spring Security**

- **JWT**

- **PostgreSql**

- **RestClient**

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

## 🏆 Notion

- [팀 노션](https://www.notion.so/BLife-44624db8f22d448aa6c9b59b793bd7ae)
