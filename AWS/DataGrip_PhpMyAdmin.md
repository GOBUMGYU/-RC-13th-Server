# MySQL 외부에서 접속하기 (DataGrip or WorkBench) + phpMyAdmin 설치 

<details>
<summary><b>MySQL외부에서 접속하기 (DataGrip)</b></summary>
<div markdown="1"></br>  
  
**1.** **AWS 서버에 깔아 놓은 MySQL에 접속 후 유저를 따로 설정하기**

```bash
# user_name에는 원하는 계정 이름을 입력. @ 뒤의 %는 모든 호스트에서의 접속을 허용.
mysql > CREATE USER 'user_name'@'%' IDENTIFIED BY 'password';
# 계정을 생성한 뒤 바로 권한 설정. *.*는 모든 데이터베이스와 테이블에 대한 접근 권한을 주는 것.
mysql > GRANT ALL PRIVILEGES ON *.* TO 'user_name'@'%';
# 권한 설정 후 테이블을 갱신.
mysql > FLUSH PRIVILEGES;
```

**2.** **우분투 방화벽에 3306 포트 허용**

```bash
$ sudo ufw allow 330
```

**3.** **mysqld.cnf 파일에서 bind-address 부분을 ‘127.0.0.1’ → ‘0.0.0.0’으로 수정**

```bash
$ sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf
```  
  
  ![image](https://user-images.githubusercontent.com/106207558/211192442-76618322-5067-4f70-8256-19b9a7bec465.png)

**4.** **MySQL 재시작**  
  
```bash
$ sudo service mysql restart
```
  
**5.** **Local에 있는 DATA GRIP 에서 AWS에 있는 MySQL로 접속 (아래 URL 참고)**

[2-3 MySQL 외부에서 접속](https://luminitworld.tistory.com/82)

  -  <b>결과물</b>  
  
![image](https://user-images.githubusercontent.com/106207558/211192501-cd55a9a5-df80-4027-8d78-d60023a4e910.png)

</div>
</details>

<details>
<summary><b>PhpMyAdmin 설치</b></summary>  
<div markdown="1"></br>  
  
**1.** **phpmyadmin 설치**

```bash
$ sudo apt update
$ sudo apt install phpmyadmin
```

**2. 설치 과정 중 아래와 같은 질문을 하는데 현재 웹 서버는 Nginx로 설치했으니 tab 키로 OK만 선택해서 그냥 넘어가기.**   
  
  ![image](https://user-images.githubusercontent.com/106207558/211192836-7543a124-70d9-4b9c-806e-375dc01d361e.png)

**3. MySQL 이미 설치했으니 → NO 선택**  
  
  ![image](https://user-images.githubusercontent.com/106207558/211192613-4a155f3a-ef70-478f-bd17-fc4b82d6bd72.png)

```bash
$ sudo ln -s /usr/share/phpmyadmin /var/www/html/pma 
# phpmyadmin 은 해커들이 접근하기 좋다고 함 -> pma
$ sudo service nginx restart
```
  
**4.** **Symbolic 링크를 만드는 것을 통한 phpmyadmin 설정하기**    
  
```bash
$ sudo vi /etc/nginx/sites-available/default
```  
  
  ![image](https://user-images.githubusercontent.com/106207558/211192666-5f818a44-2cc2-4578-a162-c668b852f6f8.png)

**6.** **Nginx 다시 시작하기**  
  
```bash
sudo service nginx restart
```  
  
- **결과물 = ‘자신의 AWS 서버 ip 주소/phpmyadmin’ (pma)으로 접속하기**  
  
  ![image](https://user-images.githubusercontent.com/106207558/211192706-97de2a62-0a7b-44ed-bb3c-eba696a47e59.png)
  ![image](https://user-images.githubusercontent.com/106207558/211192724-28411d35-5d2b-496f-9d34-1437a0c7668a.png)

</div>
</details>
