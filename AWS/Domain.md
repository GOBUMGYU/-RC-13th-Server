# Domain 적용 (가비아) ,HTTPS 적용 (lets’encrypt), Sub Domain 적용 (Dev, Prod), Redirection 적용 (IP to Domain, http → https)

<details>
<summary><b>도메인 적용</b></summary>
<div markdown="1"></br>   
  
**1.** **가비아 회원 가입**

**2. 도메인 구매 (~~~~~~~~~~~.shop = 550원)**  
  
![image](https://user-images.githubusercontent.com/106207558/211193124-3747ccb6-f87d-41a0-8caa-163d444b1ae9.png)
![image](https://user-images.githubusercontent.com/106207558/211193155-3d88cd79-2884-4707-9205-a178a63548c7.png)   
  
**3. DNS 설정’ → ‘DNS 레코드 수정’에서 AWS 서버 IP 주소로 레코드 추가**   
  
  ![image](https://user-images.githubusercontent.com/106207558/211193190-a356a090-4ab0-4652-979d-f9ae265945d2.png)  
  
  - **결과물 = 내가 구매한 도메인을 주소로 입력하면 AWS 웹 서버로 이동하는 것을 볼 수 있다.**   
  
  ![image](https://user-images.githubusercontent.com/106207558/211193214-b9be87d3-65cb-4006-929b-bb3d3cc0867c.png)



</div>
</details>

<details>
<summary><b>HTTPS 적용</b></summary>  
<div markdown="1"></br>  
  
**1.** **cerbot 설치**

```bash
# snap이 최신 버전인지 확인하고 아니면 업데이트
$ sudo snap install core;
$ sudo snap refresh core;

# certbot 패키지 설치
$ sudo snap install --classic certbot

#심볼릭 링크를 이용해 certbot 명령어를 사용할 수 있게 만든다.
$ sudo ln -s /snap/bin/certbot /usr/bin/certbot
```

**2.** **vi 편집기로 서버 설정 파일을 열어서 서브 도메인을 server_name에 설정.**
```bash
sudo vi /etc/nginx/sites-available/default
```  
  
  ![image](https://user-images.githubusercontent.com/106207558/211193453-5df07002-88d6-4a13-a638-281a1759fc71.png)  
  
**3.** **도메인에 https 적용 (*certificate* 얻기)**

```
$ sudo certbot --nginx -d rc13th-server.shop -d www.rc13th-server.shop
```

- **결과물 ([https://www.ssllabs.com/ssltest/](https://www.ssllabs.com/ssltest/)에서 https 적용 여부 확인 가능)**  
  
![image](https://user-images.githubusercontent.com/106207558/211193490-cbe0d91b-7628-4e46-94da-f0ecb2b6f87e.png)

  </div>
</details>  
  
<details>
<summary><b>Sub Domain 적용</b></summary>
<div markdown="1"></br>    
  
**1. 가비아 DNS 레코드 수정에서 서브 도메인 dev와 prod를 추가**  
  
  ![image](https://user-images.githubusercontent.com/106207558/211193525-8ab6ae67-d004-46e3-8e58-38c11744f364.png)

**2.** **AWS 서버 안에 서브 도메인 폴더 만들어 주기**

```bash
$ sudo mkdir /var/www/html/dev
$ sudo mkdir /var/www/html/prod
```

**3. vi 편집기로 서버 설정 파일을 열어서 서브 도메인을 server_name에 설정.**   
  
  ![image](https://user-images.githubusercontent.com/106207558/211193588-a369c2bb-8a78-42ee-9b24-a9eeb6409a11.png)  
  
**4. 서브 도메인들도 https를 적용.**  
  
```bash
$ sudo certbot --nginx -d dev.rc13th-server.shop -d prod.rc13th-server.shop -d rc13th-server.shop -d www.rc13th-server.shop
```  
  
  ![image](https://user-images.githubusercontent.com/106207558/211193669-7a9a7029-5ee1-4e24-b1c9-be25a3fe53c0.png)
  
**5. Local에서 AWS로 파일 전송을 위한 권한 수정하기** 

```bash
$ sudo chown ubuntu:ubuntu /var/www/html/dev -R
$ sudo chown ubuntu:ubuntu /var/www/html/prod -R
```

- **결과물 SubDomain (DEV, PROD)**  
  
  ![image](https://user-images.githubusercontent.com/106207558/211193718-09c89878-fd9d-4a78-9543-7c60e80cdf76.png)

</div>
</details>

<details>
<summary><b>Redirection 적용</b></summary>  
<div markdown="1"></br>   
  
**1.** **편집기로 nginx 설정 파일을 연다.**

```bash
$ sudo vi /etc/nginx/sites-available/default
```

**2.** **아래 코드를 nginx 설정 파일(default)에 추가한다.**   
  
  ![image](https://user-images.githubusercontent.com/106207558/211193763-09336a14-cfac-45f6-a125-cae6ee687aa2.png)

**3.** **nginx를 다시 실행한다.**

```bash
$ sudo service nginx restart
```

- **결과물**

→ **주소 창에 ip 주소를 검색했을 때 도메인 주소로 변경되어 웹 페이지로 이동함을 볼 수 있었다.**   
  
  ![image](https://user-images.githubusercontent.com/106207558/211193788-1e5f8ab5-6752-45ab-8508-b4812e37c5f3.png)

- **http redirection에 대해서**  
  
    **→ https를 적용시킴과 동시에 자동으로 redirection이 설정된 모습을 볼 수 있었다.**

</div>
</details>
