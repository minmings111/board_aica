# Board API - 게시판 REST API 프로젝트

Spring Boot와 MyBatis를 활용한 RESTful 게시판 API 서버입니다. 게시글 CRUD, 댓글 기능, 페이징 및 검색 기능을 제공합니다.

## 📋 목차
- [기술 스택](#-기술-스택)
- [주요 기능](#-주요-기능)
- [프로젝트 구조](#-프로젝트-구조)
- [데이터베이스 설정](#-데이터베이스-설정)
- [설치 및 실행](#-설치-및-실행)
- [API 문서](#-api-문서)

## 🛠 기술 스택

### Backend
- **Java 17**
- **Spring Boot 3.4.9**
- **MyBatis 3.0.5**
- **Lombok**

### Database
- **Oracle Database**
  - JDBC Driver: `ojdbc6 11.2.0.4`

### Build Tool
- **Maven**

## ✨ 주요 기능

### 1. 게시판 기능 (Board)
- ✅ 게시글 등록 (Create)
- ✅ 게시글 조회 (Read)
  - 전체 목록 조회
  - 단건 조회
  - 페이징 처리된 목록 조회
  - 검색 기능 (제목/내용/작성자)
- ✅ 게시글 수정 (Update)
- ✅ 게시글 삭제 (Delete)

### 2. 댓글 기능 (Reply)
- ✅ 댓글 등록
- ✅ 댓글 조회 (게시글별)
- ✅ 댓글 수정
- ✅ 댓글 삭제

### 3. 검색 및 페이징
- **검색 타입**: 제목(T), 내용(C), 작성자(W) 및 복합 검색 지원
- **페이징**: 페이지 번호와 페이지당 항목 수 설정 가능
- **동적 쿼리**: MyBatis Dynamic SQL을 활용한 유연한 검색

## 📁 프로젝트 구조

```
board_aica/
├── src/
│   ├── main/
│   │   ├── java/com/example/board/
│   │   │   ├── controller/          # REST API 컨트롤러
│   │   │   │   ├── BoardController.java
│   │   │   │   └── ReplyController.java
│   │   │   ├── service/             # 비즈니스 로직
│   │   │   │   ├── BoardService.java
│   │   │   │   ├── BoardServiceImpl.java
│   │   │   │   ├── ReplyService.java
│   │   │   │   └── ReplyServiceImpl.java
│   │   │   ├── mapper/              # MyBatis 매퍼 인터페이스
│   │   │   │   ├── BoardMapper.java
│   │   │   │   └── ReplyMapper.java
│   │   │   ├── domain/              # 도메인 모델 (VO)
│   │   │   │   ├── BoardVO.java
│   │   │   │   ├── ReplyVO.java
│   │   │   │   └── Criteria.java   # 페이징/검색 조건
│   │   │   └── BoardApplication.java
│   │   └── resources/
│   │       ├── mapper/              # MyBatis XML 매퍼
│   │       │   ├── BoardMapper.xml
│   │       │   └── ReplyMapper.xml
│   │       └── application.yml      # 애플리케이션 설정
│   └── test/                        # 테스트 코드
├── pom.xml                          # Maven 설정 파일
└── README.md
```

### 주요 컴포넌트 설명

#### Domain (VO)
- **BoardVO**: 게시글 엔티티 (번호, 제목, 내용, 작성자, 등록일, 수정일)
- **ReplyVO**: 댓글 엔티티 (댓글번호, 게시글번호, 댓글내용, 작성자, 등록일, 수정일)
- **Criteria**: 페이징 및 검색 조건 (페이지번호, 페이지크기, 검색타입, 키워드)

#### Mapper
- MyBatis를 통한 SQL 매핑
- XML 기반 쿼리 정의
- 동적 SQL을 활용한 검색 기능 구현

## 🗄 데이터베이스 설정

### Oracle Database (현재 설정)
```yaml
spring:
  datasource:
    driver-class-name: oracle.jdbc.driver.OracleDriver
    url: jdbc:oracle:thin:@localhost:1521:XE
    username: agent2
    password: 1234
```

### 테이블 구조

#### TBL_BOARD (게시판)
```sql
CREATE TABLE tbl_board (
    bno NUMBER PRIMARY KEY,           -- 게시글 번호
    title VARCHAR2(200) NOT NULL,     -- 제목
    content VARCHAR2(2000),            -- 내용
    writer VARCHAR2(50) NOT NULL,     -- 작성자
    regdate DATE DEFAULT SYSDATE,     -- 등록일
    updatedate DATE DEFAULT SYSDATE   -- 수정일
);

CREATE SEQUENCE seq_board;
```

#### TBL_REPLY (댓글)
```sql
CREATE TABLE tbl_reply (
    rno NUMBER PRIMARY KEY,            -- 댓글 번호
    bno NUMBER NOT NULL,               -- 게시글 번호 (FK)
    reply VARCHAR2(1000) NOT NULL,     -- 댓글 내용
    replyer VARCHAR2(50) NOT NULL,     -- 댓글 작성자
    replydate DATE DEFAULT SYSDATE,    -- 등록일
    updatedate DATE DEFAULT SYSDATE,   -- 수정일
    FOREIGN KEY (bno) REFERENCES tbl_board(bno)
);

CREATE SEQUENCE seq_reply;
```

## 🚀 설치 및 실행

### 사전 요구사항
- Java 17 이상
- Maven 3.6 이상
- Oracle Database (또는 MySQL)

### 1. 프로젝트 클론
```bash
git clone <repository-url>
cd board_aica
```

### 2. 데이터베이스 설정
1. Oracle Database 설치 및 실행
2. 위의 테이블 및 시퀀스 생성 스크립트 실행
3. `src/main/resources/application.yml`에서 데이터베이스 연결 정보 수정

### 3. 빌드 및 실행

#### Maven을 이용한 실행
```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

#### JAR 파일 빌드 후 실행
```bash
# 빌드
mvnw.cmd clean package

# 실행
java -jar target/board-0.0.1-SNAPSHOT.jar
```

### 4. 애플리케이션 접속
- 서버 포트: `8084`
- Base URL: `http://localhost:8084`

## 📡 API 문서

### Board API
- `GET /board/list` - 전체 게시글 목록 조회
- `GET /board/list/{pageNum}/{type}/{keyword}` - 페이징 + 검색 목록 조회
- `GET /board/get/{bno}` - 게시글 상세 조회
- `POST /board/register` - 게시글 등록
- `PUT /board/modify/{bno}` - 게시글 수정
- `DELETE /board/remove/{bno}` - 게시글 삭제

### Reply API
- `GET /replies/list/{bno}` - 댓글 목록 조회 (게시글별)
- `GET /replies/get/{rno}` - 댓글 상세 조회
- `POST /replies/new/{bno}` - 댓글 등록
- `PUT /replies/modify/{rno}` - 댓글 수정
- `DELETE /replies/delete/{rno}` - 댓글 삭제

---

## 📝 라이선스

이 프로젝트는 학습 목적의 데모 프로젝트입니다.
