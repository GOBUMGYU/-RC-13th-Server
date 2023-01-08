# **AWS 서버 구축 / Linux + Nginx PHP MySQL + phpinfo 띄우기**

**비즈니스와 개발자가 웹 서비스를 사용하여 확장 가능하고 정교한 애플리케이션 구축하도록 지원하여 준다. 여기서 클라우드 컴퓨팅이란 인터넷을 통해 IT 리소스와 애플리케이션을 온디맨드로 제공하는 서비스 이다.**


<details>
<summary><b>진행과정</b></summary>
<div markdown="1"></br>  

### AWS 설치 및 탄력 적 IP주소 할당  
  
**1.** **AWS URL 접속 후 회원가입** 

[무료 클라우드 컴퓨팅 서비스 - AWS 프리 티어](https://aws.amazon.com/ko/free/?trk=fa2d6ba3-df80-4d24-a453-bf30ad163af9&sc_channel=ps&s_kwcid=AL!4422!3!563761819834!e!!g!!aws&ef_id=Cj0KCQiAtbqdBhDvARIsAGYnXBMnU6nmVd8Bp9jHJwNETT7mSfu6D7O4cn2e5uMNJTA_s272JJgwGaYaAjNHEALw_wcB:G:s&s_kwcid=AL!4422!3!563761819834!e!!g!!aws&all-free-tier.sort-by=item.additionalFields.SortRank&all-free-tier.sort-order=asc&awsf.Free%20Tier%20Types=*all&awsf.Free%20Tier%20Categories=*all)

**2.** **데이터 센터 위치를 서울로 변경, 검색창에 EC2를 검색 → 인스턴트 시작 버튼 클릭**  
  
  ![image](https://user-images.githubusercontent.com/106207558/211190582-4df6b66e-aba5-426e-bd82-f0c4a044daad.png)  
  
**3.** **OS는 Ubuntu로 설정 후 인스턴스는 micro로 하여 프리티어 사용가능 확인하기**    
  
  ![image](https://user-images.githubusercontent.com/106207558/211190599-9cec6ae0-5398-4aa5-849a-3ecef532e30a.png)  
  
**4.** **키 페어 생성**   
  
  ![image](https://user-images.githubusercontent.com/106207558/211190630-0d7b1542-f8f3-423a-aa09-a26306c5588e.png)  
  
**5.** **스토리지 구성 (최대 30GB까지 모든 인스턴스 통틀어)**  
  
  ![image](https://user-images.githubusercontent.com/106207558/211190645-74462ffd-2faf-40c5-bfa4-2991bb3278ec.png)  
  
**6.** **인바운드 보안 그룹 설정 (과제 중 사용할 수 있는 3306포트와 443 포트도 열어 놓기)**    
   **ssh와 mysql은 내 ip만 접속 가능하도록 하고 http와 https는 모든 ip에서 접속 가능하도록 설정**  
  
  ![image](https://user-images.githubusercontent.com/106207558/211190688-b7e1c33f-deb6-436e-989a-4a26ceff334b.png)
  ![image](https://user-images.githubusercontent.com/106207558/211190702-7cfba4ac-3c75-4ecf-bd51-6f108e2b8bea.png)

<hr>  

### 탄력적 IP

[[AWS] 📚 EIP(탄력적 IP) 개념 & 사용 세팅 정리](https://inpa.tistory.com/entry/AWS-%F0%9F%93%9A-%ED%83%84%EB%A0%A5%EC%A0%81-IP-Elastic-IP-EIP-%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80)

**인스턴스의 고정IP를 부여받아 IP가 바뀌어 문제가 생기는 상황을 방지 (탄력적 IP : EIP)**

**AWS 에서 설정해야 함 - 자동할당 인줄 알고 있었는데 접속안되서 다시함**

- **해당 인스턴스에 접속하기 위해 Winscp & PuTTY설치 및 실행**
    
    [WinSCP :: Official Site :: Download](https://winscp.net/eng/download.php)
    
    [Download PuTTY: latest release (0.78)](https://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html)
    
- **winscp에서 자신의 aws 서버로 로그인 과정 중 개인 키 파일(키 페어)을 첨부하기**
    - **winscp - AWS에서 임대한 서버 컴퓨터의 디렉토리 구조를 직관적으로 파악              Putty - 서버 컴퓨터를 조작하기 위한 터미널**   
  
![image](https://user-images.githubusercontent.com/106207558/211191038-45413d10-a00b-4ebb-83c9-7b0155b9c5d7.png)  

  <b>확인 후 로그인</b>  
  
  ![image](https://user-images.githubusercontent.com/106207558/211191103-0b2b4473-575a-4e41-965b-32f624261635.png)

  - <b>Nginx 설치 및 접속확인</b>  

 ```powershell
  $ sudo apt update
  $ sudo apt install nginx
 ```  
  
![image](https://user-images.githubusercontent.com/106207558/211191270-5e4fe6be-e7b7-4a4b-bf00-e1978febad12.png)
  
  - **MySQL 설치**   
  
   ```powershell
  $ sudo apt install mysql-server
  $ mysql -u root -p
  mysql> ALTER user 'root'@'localhost' identified with mysql_native_password by '변경 비밀번호';
 ```    
  
![image](https://user-images.githubusercontent.com/106207558/211191323-0279d245-164a-42b2-938c-8cad74d223f0.png)

- **PHP설치**  
  
 ```powershell
  $ sudo apt install php8.1-fpm php-mysql
  $ sudo apt install php-fpm

  $ cd /var/www/html
  $ sudo vi index.php
  index.php 내부에는 phpinfo();
 ```  
  
```powershell  
📌 Nginx의 루트 경로에 index.php 파일을 생성하고 다시 접속해보니 화면은 바뀌지 않고  
  index.php 파일이 다운로드 된다 → php-fpm과 연동하지 않았기 때문

 ```   
    
```powershell
  $ sudo vi /etc/nginx/sites-available/default
  $ sudo service nginx restart
```   
  
  ![image](https://user-images.githubusercontent.com/106207558/211191623-dcf0614c-0fce-4daa-833d-affcb1369fad.png)

```powershell
  📌 Nginx 설정파일에서 php와 연동하기 위한 모듈의 주석을 해제한다  
  이때 내가 사용하는 sphp 버전으로 수정해줘야 함
```   
  
**실행**  
    
```powershell
  $ sudo service nginx start 
```   
> **클라우드 서버 : AWS EC2  
  WebServer : Nginx  
  BackEnd Language : PHP   
  DBMS : MySQL**
>
  
</div>
</details>

<details>
<summary><b>결과</b></summary>  
<div markdown="1"></br>   
  
### 인스턴스 시작 완료  
  
  ![image](https://user-images.githubusercontent.com/106207558/211192001-d8bf921c-d443-4a8a-85fa-af1950ea732f.png)  
  
### Local서버 접속  
  
  ![image](https://user-images.githubusercontent.com/106207558/211192026-687be65a-4926-4176-9499-d9cb41f5647f.png)  
  
### 외부에서 phpinfo 띄우기  
  
  ![image](https://user-images.githubusercontent.com/106207558/211192052-f3737dc1-ba9c-4127-80af-8e13c149dfd3.png)

</div>
</details>


<details>
<summary><b>에러사항</b></summary>  
<div markdown="1"></br>  
  
**MySQL 플러인문제 비밀번호 밑 사이트 보고 해결** 

[[mariaDB] ERROR 1698 (28000): Access denied for user 'root'@'localhost' 문제 해결](https://velog.io/@hm5395/mariaDB-ERROR-1698-28000-Access-denied-for-user-rootlocalhost-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0)

</div>
</details>
