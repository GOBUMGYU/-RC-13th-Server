# Local 서버 구축 /Window + Apache PHP MySQL(XAMPP)
## <b>XAMPP란?</b>  

<b>웹 애플리케이션 개발을 위해 서버 관련 소프트웨어로 apache, php, mysql, fillzilla, tomcat 등을 지원하는 통합툴이라고 보면된다.</b>
<details>
<summary><b>진행과정</b></summary>  
<div markdown="1"></br>  

### 로컬 서버 구축

**다운로드 사이트(URL) 접속 후 XAMPP다운로드**

[Apache Friends](https://www.apachefriends.org/)

### 외부에서 Local 서버로 접속해 **phpinfo 띄우기 (by 포트 포워딩)**

**1.** (**C:\xampp\htdocs)폴더 안에 html이나 php, css등을 만들기**  
  
![image](https://user-images.githubusercontent.com/106207558/211189827-a3a75a58-4e4f-4ca8-8994-45d60157d338.png)  
  
**2.** **XAMPP에서 Apache와 MySQL 실행 (오류 시 에러사항 참고)**

**3. 브라우저에 192.168.0.1 검색  → 로그인 → 공유기 관리도구 → 고급 설정 → NAT/라우터 관리 → 포트포워드 설정** 

**참고영상 URL**  

[포트포워딩(port forwarding)](https://www.opentutorials.org/course/3265/20038)
</div>
</details>

<details>
<summary><b>결과</b></summary>  
<div markdown="1"></br>  

### XAMPP 구축  
  
![image](https://user-images.githubusercontent.com/106207558/211189988-cebc65d0-0a6f-4240-bed3-ada551dbedfb.png)  
  
### 외부 Local 접속  
  
![image](https://user-images.githubusercontent.com/106207558/211190066-0bf75e4f-49d6-4173-b225-45f5dc0e3db6.png)  
  
![image](https://user-images.githubusercontent.com/106207558/211190078-a0878537-9a24-4a52-a29b-bec385db2674.png)

</div>
</details>


<details>
<summary><b>에러사항</b></summary>  
<div markdown="1"></br>  
  <b>MySQL 3306포트 이미 실행중이라고 하는 것 같다.</b>    
  
  ![image](https://user-images.githubusercontent.com/106207558/211190114-a5879310-6522-44ec-9ffa-d8a2855c8050.png)  
  <b>서비스 → MySQL80 서비스 중지 후 다시 재실행</b>  
  ![image](https://user-images.githubusercontent.com/106207558/211190133-f2930413-006b-44b8-a8c0-9eb2614ba118.png)  
  
  <b>해결!</b>  
  ![image](https://user-images.githubusercontent.com/106207558/211190170-53257ea8-09b8-4eae-a97d-13b5dc358e19.png)

</div>
</details>
