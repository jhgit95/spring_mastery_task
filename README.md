

# 😊  Schedular 😊

### 📌 프로젝트 설명
***Spring Mastery Task from the NBC***

숙련 과제

<br></br>

---

### 📌 과제 진행 시 조건사항 ( 수정 필요 )
*	일정 작성, 수정, 조회 시 반환 받은 일정 정보에 비밀번호는 제외
*	일정 수정, 삭제 시 선택한 일정의 비밀번호와 요청할 때 함께 보낸 비밀번호가 일치할 경우에만 가능 (비밀번호가 일치하지 않을 경우 적절한 오류 코드 및 메세지를 반환)
*	CRUD 필수 기능은 모두 데이터베이스 연결 및 JDBC 를 사용해서 개발
*	3 Layer Architecture 에 따라 각 Layer의 목적에 맞게 개발

<br></br>
---

## 📝 API 명세서 ( 수정 필요 )

#### 사용자

| Function | Method | URI                 | Request                                                           | Response                                                                                                                       | Status Code                           |
|----------|--------|---------------------|-------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|---------------------------------------|
| 회원 가입    | POST   | localhost:8080/users/zz/sign-up          |{"userName": "크리링","email": "크리크리링","regDate": "2024-08-28", "modDate": "2024-08-28","pw": "크리","auth":"nomal"} | {"userId":18,"userName":"크리링","email":"크리크리링","regDate":"2024-08-28","modDate":"2024-08-28","auth":"nomal"} | 200: OK                               |
| 로그인    | GET    |localhost:8080/users/zz/login |{"email":"이메일","pw":"비번"} | "로그인 완료"   | 200: OK<br>500: Internal Server Error |
|사용자 조회 | GET    | localhost:8080/users/{id}         | -  | {"userId":20,"userName":"이름","email":"이메일","regDate":"2024-08-28","modDate":"2024-08-28","auth":"nomal"} | 200: OK                               |
| 사용자 수정    | PUT    |localhost:8080/users | {"userId":18,"userName":"미친유저초각성","email":"crazy@슈퍼미친유저3.미친놈","regDate":"2024-08-21","modDate":"2024-08-21","auth":"nomal"}| {"userId":18,"userName":"미친유저초각성","email":"crazy@슈퍼미친유저3.미친놈","regDate":"2024-08-21","modDate":"2024-08-21","auth":"nomal"}  | 200: OK<br>500: Internal Server Error |
| 사용자 삭제    | DELETE | localhost:8080/users/{id} | - | -    | 200: OK<br>500: Internal Server Error |


#### 일정


| Function | Method | URI                 | Request                                                           | Response                                                                                                                       | Status Code                           |
|----------|--------|---------------------|-------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|---------------------------------------|
| 일정 작성    | POST   |localhost:8080/schedules         |{"user":{"userId":19},"title":"일정만들기","regDate":"2024-08-29","modDate":"2024-08-29","content":"ㅋㅋ"} |{"scheduleId":39,"title":"일정만들기","content":"ㅋㅋ","regDate":"2024-08-29","modDate":"2024-08-29","assignee":null,"userId":19}| 200: OK                               |
| 일정 단건 조회    | GET    |localhost:8080/schedules/{id} |- |{"scheduleId":38,"title":"일정만들기","content":"ㅋㅋ","regDate":"2024-08-29","modDate":"2024-08-29","assignees":[{"userId":19,"assigneeId":17,"userName":"크리링","userEmail":"크리크리링"},{"userId":19,"assigneeId":18,"userName":"크리링","userEmail":"크리크리링"},{"userId":20,"assigneeId":19,"userName":"이름","userEmail":"이메일"}]} | 200: OK<br>500: Internal Server Error |
| 일정 전체 조회 | GET    |localhost:8080/schedules/all         |{"title":"빨리제출","regDate":"2024-08-22","modDate":"2024-08-29","content":"빨리빨리"} |[{"scheduleId":1,"title":"왜다뒤엎어야하는거야","content":"다시테스트","regDate":"2024-08-22","modDate":"2024-08-22"},{"scheduleId":39,"title":"일정만들기","content":"ㅋㅋ","regDate":"2024-08-29","modDate":"2024-08-29"}]| 200: OK                               |
| 일정 수정    | PUT    |localhost:8080/schedules/{id} | {"userId":18,"userName":"미친유저초각성","email":"crazy@슈퍼미친유저3.미친놈","regDate":"2024-08-21","modDate":"2024-08-21","auth":"nomal"}| {"scheduleId":37,"title":"빨리제출","content":"빨리빨리","regDate":"2024-08-22","modDate":"2024-08-29","assignees":[{"userId":19,"assigneeId":16,"userName":"크리링","userEmail":"크리크리링"}]} | 200: OK<br>500: Internal Server Error |
| 일정 삭제    | DELETE | localhost:8080/schedules/{id} | - | "삭제 완료"    | 200: OK<br>500: Internal Server Error |

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




![mastery_spring_addPw](https://github.com/user-attachments/assets/f61971ac-b0c6-4ca5-bc01-a003490d42c9)

