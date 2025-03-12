# 🏝️ 여행 역할 
![image (1)](https://github.com/user-attachments/assets/66052c3e-929d-467f-a84a-d4d28a62599a)

# 📌 서비스 소개
- "여행 역할"은 사용자가 쉽고 편리하게 여행 일정을 계획, 예약, 회계 등을 할 수 있도록 도와주는 서비스입니다.
- 2023.04.14 ~ 2023.06.03

# 👥 팀원

| 역할 | 이름 | Github |
|------|------|------|
| **Backend** | 유해찬 | [GitHub](https://github.com/kwYoohae) |
| **Backend** | 임강호 | [GitHub](https://github.com/Icln) |
| **Backend** | 김현진 | [GitHub](https://github.com/guswls159357) |
| **Frontend** | 정찬우 | [GitHub](https://github.com/Chanwoo-Jeong) |
| **Frontend** | 유진 | [GitHub](https://github.com/imsmallgirl) |
| **Frontend** | 심채윤 | [GitHub](https://github.com/chaeyun-sim) |
| **Designer** | 홍승현 | - |
| **Designer** | 황리하 | - |
| **PM** | 김문경 | - |


# ✨ 주요 기능
### 1️⃣ 여행 일정 관리
- 사용자가 직접 여행 계획을 생성하고, 일자별 일정을 추가할 수 있습니다.
- 팀원들과 공유 가능한 여행 일정을 생성하고, 역할을 배정할 수 있습니다.
- 여행 장소 정보, 참여 인원 및 역할을 한눈에 확인할 수 있습니다.

### 2️⃣ 예약 및 회계 기능
- 예약 페이지에서 숙소, 교통편 등의 예약 정보를 체크할 수 있습니다.
- 회계 페이지에서 일정별 지출 금액을 기록하고, 여행 경비를 효율적으로 관리할 수 있습니다.
- 결제 수단별 정산을 지원하며, 공동 경비와 개인 지출 내역을 구분할 수 있습니다.

### 3️⃣ 지도 및 위치 기반 서비스
- 카카오 지도 API를 활용하여 여행 장소를 쉽게 추가할 수 있습니다.
- 찜 목록을 저장하여 빠르게 장소를 선택할 수 있습니다.

### 4️⃣ 초대 기능
- 초대 링크를 통해 팀원을 추가하고, 역할을 배정할 수 있습니다.
- 총무 역할 배정 기능을 통해 회계를 담당할 팀원을 설정할 수 있습니다.
- 댓글 작성 기능을 통해 원할한 소통이 가능합니다.

### 5️⃣ 여행 준비 체크리스트
- 여행에 필요한 필수 준비물을 추천해줍니다.
- 사용자가 직접 준비물을 추가할 수 있으며, 체크 기능을 통해 준비 여부를 관리할 수 있습니다.

# 🛠 기술 스택
- Backend:	Java 11, Spring Boot 2.7, MySQL, JPA, QueryDSL
- Frontend:	React.js, TypeScript
- Infra:	AWS (EC2, S3), Docker, Nginx

# 🎨 화면 구성 (UI)
### 랜딩 페이지
![image (2) (1)](https://github.com/user-attachments/assets/19419ce4-9b1b-44c7-9dcd-091863372c3f)


### 🏠 메인 페이지 & 방 목록 페이지
| 메인 페이지 | 방 목록 페이지 |
|------------|------------|
| ![image (1)](https://github.com/user-attachments/assets/dc1b007c-0a8e-461f-a6e6-3d3ad4ce12be) |  ![image (7)](https://github.com/user-attachments/assets/37552d0b-7478-45e6-bba4-ff58596156ec) |

---

### 🌍 모든 여행 & 일정 페이지
| 모든 여행 페이지 | 일정 페이지 |
|----------------|------------|
| <img width="950" height="250" alt="image (4)" src="https://github.com/user-attachments/assets/c055b415-929f-4059-858e-8dec6ce0bc5f" /> | <img width="950" height="250" alt="image (3)" src="https://github.com/user-attachments/assets/bfc751d7-d96b-4a5a-8e5a-fe58d6fcb0d1" /> |

---

### 🏨 예약 & 💰 회계 & 📋 준비물 페이지
| 예약 페이지 | 회계 페이지 | 준비물 페이지 |
|------------|------------|------------|
| ![image (6)](https://github.com/user-attachments/assets/fb2cee71-5b79-4b85-8ece-2f24e32018d1) | ![image (5)](https://github.com/user-attachments/assets/72d78717-13af-48db-9939-6098fe38bfe8) | ![image (8)](https://github.com/user-attachments/assets/6392f032-b2b0-4c92-b9ea-2ac1973a44a1) |



# 🚀 구현 기술
### ✅ 여행 일정 관리 최적화
- N+1 문제 해결 → FetchJoin 및 BatchSize 적용으로 쿼리 최적화
- No-Offset 방식 도입 → 페이징 성능 개선
### ✅ 예약 및 회계 시스템 구축
- JPA + QueryDSL 활용 → 동적 쿼리를 통해 다양한 정산 방식 지원
- JMeter를 활용한 성능 테스트 → 데이터 처리 속도 및 API 응답 시간 최적화

# 🔗 관련 문서
- [IA 문서](https://www.notion.so/kwyoohae/409fd47468604a74961fa50f55566ebc)
- [API 문서](https://www.notion.so/kwyoohae/409fd47468604a74961fa50f55566ebc)
- [ERD 문서](https://www.notion.so/kwyoohae/409fd47468604a74961fa50f55566ebc)
- [기능 명세서](https://www.notion.so/kwyoohae/409fd47468604a74961fa50f55566ebc)

![image (2)](https://github.com/user-attachments/assets/c6edadf4-dcbd-442e-aee1-a4be0fcbfaf8)

<img width="1000" alt="image" src="https://github.com/user-attachments/assets/293df3d3-a699-4702-a547-a9a952ab2ad7" />

