# PHP 설치

<details>
    <summary>결과</summary>
    <div markdown="1">  
    
  - <b>참고사이트 URL</b>
    [[Ubuntu] Ubuntu-20.04에 APM 소스 설치(수동 설치)하기 - 3 (PHP) (tistory.com)](https://yeni-days.tistory.com/8)
    
    
**필수 패키지 설치**

  ```powershell
  & sudo su
  /usr/local# apt-get install libxml2-dev
  /usr/local# apt-get install libjpeg-dev
  /usr/local# apt-get install libpng-dev
  /usr/local# apt-get install libsqlite3-dev
  ```

**소스파일 다운로드 및 압축해제**

  ```powershell
  /usr/local# wget https://www.php.net/distributions/php-8.1.0.tar.gz
  /usr/local# tar xvfz php-8.1.0.tar.gz
  ```

**PHP 설치 (php-8.1.0)**

  ```powershell
  /usr/local# cd php-8.1.0
  /usr/local/php-8.1.0# ./configure \
  --with-apxs2=/usr/local/apache2.4/bin/apxs \ 
  --enable-mysqlnd \ 
  --with-mysql-sock=mysqlnd \ 
  --with-mysqli=mysqlnd \ 
  --with-pdo-mysql=mysqlnd \ 
  --with-imap-ssl \ 
  --with-iconv \ 
  --enable-gd \ 
  --with-jpeg \ 
  --with-libxml \ 
  --with-openssl
  /usr/local/php-8.1.0# make
  /usr/local/php-8.1.0# make test
  /usr/local/php-8.1.0# make install
  ```

**Apache, PHP 연동**

1. **모듈 설치 확인**

**아파치 설정 파일인 httpd.conf 파일을 열어 PHP모듈이 설치 됐는지 확인한다.**    


   
    /usr/local# cd /apache2.4/conf
    /usr/local/apache2.4/conf# vim httpd.conf
    

![php묘듈확인](https://user-images.githubusercontent.com/106207558/209917134-bb531a18-e979-473d-af18-c0a6e5fd415f.png)  
<b>잘 설치되어 있다면 mime-module에 사진과 같이 작성한다.</b> 

![php모듈수정](https://user-images.githubusercontent.com/106207558/209917137-485c36d0-8d4d-47b8-8b7c-3e463e434403.png)

2. **PHP.ini 파일 세팅**  

- **production 시스템용 설정 파일인 php.ini-production 파일을 /usr/local/lib/php.ini에 복사한다.**
- **php configure 시에 옵션으로 php.ini의 위치를 지정하지 않으면 기본으로 /usr/local/lib/php.ini를 사용한다.**
- **php.ini은 PHP설정 파일인데 php-8.1.0 디렉토리로 가면 php.ini-development와 php.ini-production 두개의 파일이 있다. development는 개발용, production은 프로덕션 시스템용 버전으로 개발용 같은 경우 더 많은 오류와 경고를 표시해주지만 보안상 문제가 생길 수 있으므로 개발 환경에서만 사용해야한다**
    
    ```powershell
    /usr/local# cd php-8.1.0
    /usr/local/php-8.1.0# cp php.ini-production /usr/local/lib/php.ini
    ```
    
3. **테스트용 php파일 작성**
    
    **아파치 웹 콘텐츠(html, php파일 등)는 htdocs디렉토리에 위치한다.**
    
    ```powershell
    /usr/local# cd apache2.4/htdocs
    /usr/local/apache2.4/htdocs# vim phpinfo.php
    ```
    
    ![테스트용 PHP작성](https://user-images.githubusercontent.com/106207558/209917718-4681cf19-55a2-44aa-82c0-8694641f942e.png)  
    
**연결 확인** 

**먼저 아파치를 재실행 시킨 후 [http://127.0.1.1/phpinfo.php로](http://127.0.1.1/phpinfo.php로) 접속하여 설치 정보가 출력되면 성공 !**

  ```powershell
  /usr/local# apache2.4/bin/httpd -k start
  /usr/local# ps -ef | grep httpd | grep -v grep
  /usr/local# sudo netstat -anp | grep httpd
  /usr/local# sudo curl http://127.0.0.1
  ```  
  
</details>

<details>
    <summary>결과</summary>
    <div markdown="1">  
  
  ![PHP실행결과](https://user-images.githubusercontent.com/106207558/209917914-1baf7c4b-190c-48c0-a2fe-8387347b45f6.png)

</details>

<details>
    <summary>에러사항</summary>
    <div markdown="1">  
    
**연결 확인 - apache2.4/bin httpd -k start 명령어 입력 시 오류**

**AH00558: httpd: Could not reliably determine the server's fully qualified domain name, using 127.0.1.1. Set the 'ServerName' directive globally to suppress this message
httpd (pid 67696) already running**

**httpd.conf의 ServerName 옵션의 주석을 해제하고 localhost나 127.0.0.1을 입력**
</details>