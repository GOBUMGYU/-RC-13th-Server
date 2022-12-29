# Apache 설치
<details>
<summary><b>결과</b></summary>  
<div markdown="1"></br>  

![Apache실행결과 png](https://user-images.githubusercontent.com/106207558/209910004-92a9ce65-cf62-4cfd-b09d-82c22eddab03.jpg)  
**웹 브라우저 localhost(127.0.0.1) 검색 결과**
![localhost실행결과](https://user-images.githubusercontent.com/106207558/209910629-46135a5d-bdb0-4bd9-9ec9-9f7c9d6634cc.png)
</details>

<details>
<summary>설치과정</summary>
<div markdown="1">  
  <details>
    <summary><b>참고 사이트 URL</b></br></summary>
    <div markdown="1">    
    https://yeni-days.tistory.com/2
  </details>
  <b>필수 패키지 설치</b>  
  
  
  ```bash
  $ sudo su
  # apt-get install make
  # apt-get install build-essential
  # apt-get install gcc
  # apt-get install --reinstall make
  # apt-get install libexpat1-dev
  # apt-get install g++ 
  # apt-get install net-tools
  # apt-get install curl
  ```
  
  - 소스설치 관례
    
    /usr/local에 설치 하는 것이 관례라고 함
    
    1. 소스 파일을 내려 받고
    2. ./configure로 설정
    3. make로 컴파일을 한 뒤 
    4. make install로 설치한다.
  
  <b>소스설치 파일 다운로드 및 압축해제</b>  
  
  ```bash
  $ sudo su
  /usr/local# mkdir apache
  1) apr-1.7.0
  /usr/local# wget http://mirror.navercorp.com/apache//apr/apr-1.7.0.tar.gz
  2) apr-util-1.6.1
  /usr/local# wget http://mirror.navercorp.com/apache//apr/apr-util-1.6.1.tar.gz
  3) pcre-8.45
  /usr/local# wget https://sourceforge.net/projects/pcre/files/pcre/8.45/pcre-8.45.tar.gz/
  4) apache-2.4.54
  /usr/local# wget https://dlcdn.apache.org//httpd/httpd-2.4.54.tar.gz

  압축해제
  /usr/local# tar xvfz apr-1.7.0.tar.gz
  /usr/local# tar xvfz apr-util-1.6.1.tar.gz
  /usr/local# tar xvfz pcre-8.45.tar.gz
  /usr/local# tar xvfz httpd-2.4.54.tar.gz
  ```
  - wegt : 웹 서버로부터 파일을 다운로드받는다.
  - tar xvfz : tar.gz압축을 한번에 풀어 준다.
  - APR : APR(아파치 포터블 런타임)은 아파치 HTTP서버 2.x의 핵심이며 휴대용 라이브러리이다. 이런 APR은 고급 IO기능 (예 : sendfile, epoll and OpenSSL 등)에 대한 접근을 포함하여 OS 수준의 기능(난수 생성, 시스템 상태), 그리고 기본 프로세스 처리(공유 메모리, NT 파이프와 유닉스 소켓) 등 많은 용도로 사용되고 있다.
  - PCRE : PCRE(Perl Compatible Regular Expressions)는 펄 호환 정규표현식으로서, 정규식 패턴 일치를 구현하는 함수의 집합이다. 요즘에는 Apache, PHP, KDE등을 포함한 오픈 소스 프로젝트에서 사용되고 있으며,  아파치 2.4 버전을 설치할 때는 pcre를 설치해야 한다.  
  
  <b>apr 설치</b>  
    
  ```bash
  /usr/local# cd apr-1.7.0
  /usr/local/apr-1.7.0# ./configure --prefix=/usr/local/apr
  여기서 오류가 나면 
  # cp -arp libtool libtoolT 다운로드를 해준다. (오류안났음)
  /usr/local/apr-1.7.0# make
  /usr/local/apr-1.7.0# make install
  ```
  <b>apr-util 설치</b>  
    
  ```bash
  /usr/local# cd apr-util-1.6.1
  /usr/local/apr-util-1.6.1# ./configure --prefix=/usr/local/apr-util --with-apr=/usr/local/apr
  /usr/local/apr-util-1.6.1# make
  /usr/local/apr-util-1.6.1# make install
  ```
  
  <b>pcre 설치</b>  
    
  ```bash
  /usr/local# cd pcre-8.45
  /usr/local/pcre-8.45# ./configure --prefix=/usr/local/pcre
  /usr/local/pcre-8.45# make
  /usr/local/pcre-8.45# make install
  ```
  
   <b>Apache 설치 (apache 2.4.54)</b>  
    
  ```bash
  /usr/local# cd httpd-2.4.54
  /usr/local/httpd-2.4.54# ./configure --prefix=/usr/local/apache2.4 \
  --enable-module=so --enable-rewrite --enable-so \
  --with-apr=/usr/local/apr \
  --with-apr-util=/usr/local/apr-util \
  --with-pcre=/usr/local/pcre/bin/pcre-config \   
  --enable-mods-shared=all
  /usr/local/httpd-2.4.54# make
  /usr/local/httpd-2.4.54# make install
  ```
  
   <b>apache 실행</b>  
    
  ```bash
  실행: httpd -k start, 종료: httpd -k stop
  /usr/local# sudo apache2.4/bin/httpd -k start
  /usr/local# ps -ef | grep httpd | grep -v grep
  /usr/local# sudo netstat -anp | grep httpd
  /usr/local# sudo curl http://127.0.0.1
  ```
  
</details>  

<details>
<summary>에러사항</summary>
  <details>
    <summary><b>참고 사이트 URL</b></br></summary>
    <div markdown="1">  
    
    https://security-nanglam.tistory.com/322  
    https://ppost.tistory.com/entry/%EC%9A%B0%EB%B6%84%ED%88%AC-APM-%EC%86%8C%EC%8A%A4%EC%84%A4%EC%B9%98-%EC%8B%9C-%EC%9D%BC%EC%96%B4%EB%82%98%EB%8A%94-%EC%98%A4%EB%A5%98%EB%93%A4-%EB%B0%8F-%ED%95%B4%EA%B2%B0%EB%B0%A9%EC%95%88
  </details>
<div markdown="1">  

  <b>패키지 실행 시 (make 시) 밑의 에러 발생</b>  
    
  ```bash
  E: Could not get lock /var/lib/dpkg/lock-frontend. It is held by process 3436 (unattended-upgr) - open (11: Resource temporarily unavailable)
  N: Be aware that removing the lock file is not a solution and may break your system.
  E: Unable to acquire the dpkg frontend lock (/var/lib/dpkg/lock-frontend), is another process using it?
  ```
  
  <b>해당 에러는 다른 사용자가 apt-get을 사용중일 때 접근 시 발생하기도 하고, 아무도 사용하지 않는 경우도 뜰 경우가 있다고 한다.</br> locak 파일 삭제로 해결 가능
  </br></br> 우선 apt, apt-get의 프로세스를 모두 죽이기  
  
  ```bash
  sudo killall apt apt-get
  ```
  
  **위 명령어 입력 후 진행 중인 프로세스가 없다고 하면 아래 처럼 하나씩 삭제**

  - **sudo rm/lib/apt/lists/lock**
  - **sudo rm/var/chache/apt/archives/lock**
  - **sudo rm/var/lib/dpkg/lock***

  **위와 같이 모두 삭제를 해준 뒤 아래 명령어 까지 입력해주면 해결**

  **sudo dpkg —configure -a**
  
  ***  
  
  **아파치 설정파일 설정 시 .cofigure**

  **error : Did not find pcre-config script at 경로**   
  

  **pcre부분을 찾은 다음 pcre-config 파일이 있는 경로나 파일단위 직접 지정**

  **—with -pcre=/usr/local/pcre/**    

 ↓   
  
error 나왔는데 make시 make : ***타켓이 지정되지 않았고 메이크파일이 없습니다.**

  **—with -pcre=/usr/local/pcre/bin/pcre-config 로 바꿔주면 해결**
</details>