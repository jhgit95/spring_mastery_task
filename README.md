

# 😊  Schedular 😊

### 📌 프로젝트 설명
***Spring Mastery Task from the NBC***

숙련 과제

<br></br>

---

### 📌 과제 진행 시 조건사항
*	일정 작성, 수정, 조회 시 반환 받은 일정 정보에 비밀번호는 제외
*	일정 수정, 삭제 시 선택한 일정의 비밀번호와 요청할 때 함께 보낸 비밀번호가 일치할 경우에만 가능 (비밀번호가 일치하지 않을 경우 적절한 오류 코드 및 메세지를 반환)
*	CRUD 필수 기능은 모두 데이터베이스 연결 및 JDBC 를 사용해서 개발
*	3 Layer Architecture 에 따라 각 Layer의 목적에 맞게 개발

<br></br>
---

## 📝 API 명세서 ( 수정 필요 )

#### 일정 등록
- POST
-  http://localhost:8080/schedules

|  Request | Response | 상태 코드 |
|:---|:---|:---|
 |요청 body  | 등록된 일정 정보 | 200 ok |
 |정보가 누락된 body  | 정보 누락 메세지 | 400 bad request |

<br></br>

#### 일정 조회
- GET
-  http://localhost:8080/schedules/{id} 

| Request | Response | 상태 코드 |
|:---|:---|:---|
| 요청 param | 선택한 일정 |200 ok |

<br></br>

#### 일정 목록 조회
- GET
- http://localhost:8080/schedules/search

| Request | Response | 상태 코드 |
|:---|:---|:---|
|요청 body  | 조건에 맞는 일정 | 200 ok |

<br></br>

#### 일정 수정
- PUT
-  http://localhost:8080/schedules/{id} 

 | Request | Response | 상태 코드 |
|:---|:---|:---|
 |요청 body | 수정된 일정 정보 |200 ok |
 |틀린 pw  | 일치하지 않은 pw |400 bad request |
 |누락된 정보 | 정보 등록 요청 메세지 |400 bad request |

<br></br>

#### 일정 삭제
- DELETE
-  http://localhost:8080/schedules/{id}

| Request | Response | 상태 코드 |
|:---|:---|:---|
 |요청 body  | 삭제 결과 |200 ok |
 |틀린 pw  | 일치하지 않은 pw |400 bad request|

<br></br>

#### 페이지네이션
- GET
- http://localhost:8080/schedules/pagination 

 | Request | Response | 상태 코드 |
|:---|:---|:---|
 |요청 param  | 페이지네이션 |200 ok |

<br></br>
---
## 📋 ERD





![erd담당자추가](https://github.com/user-attachments/assets/85e845e1-8ea4-4558-912f-c58ab2f29511)
