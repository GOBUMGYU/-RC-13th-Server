# MySQL 설치

<details>
    <summary><b>설치과정</b></summary>
    <div markdown="1">  
    
   - **참고 사이트 (URL)**    
   https://vanillacreamdonut.tistory.com/208
   
**필수 패키지 설치**
    
  ```powershell
  & sudo su
  /usr/local# apt-get update
  /usr/local# apt-get install cmake
  /usr/local# apt-get install libssl-dev
  /usr/local# apt-get install libboost-all-dev
  /usr/local# apt-get install libncurses5-dev libncursesw5-dev
  /usr/local# apt-get install make
  /usr/local# apt-get install gcc
  /usr/local# apt-get install g++
  /usr/local# apt-get install perl
  ```
      
      
**소스설치 파일 다운로드 및 압축해제**

  ```powershell
  /usr/local# wget https://dev.mysql.com/get/Downloads/MySQL-8.0/mysql-8.0.19.tar.gz
  /usr/local# tar xvfz mysql-8.0.19.tar.gz
  ```
    
**MySQLl 설치 (Mysql-8.0.19)**
    
  ```powershell
  /usr/local# cd mysql-8.0.19
  /usr/local/mysql-8.0.19# mkdir dir_mysql
  /usr/local/mysql-8.0.19# cd ..
  /usr/local# cd src
  /usr/local/src# sudo wget https://boostorg.jfrog.io/artifactory/main/release/1.70.0/source/boost_1_70_0.tar.gz
  /usr/local/src# sudo tar xvfz boost_1_70_0.tar.gz
  /usr/local/src# cd ..
  /usr/local# cd /usr/local/mysql-8.0.19/dir_mysql
  /usr/local/mysql-8.0.19/dir_mysql# cmake \.. \-DCMAKE_INSTALL_PREFIX=/usr/local/mysql \-DMYSQL_DATADIR=/usr/local/mysql/data \-DMYSQL_UNIX_ADDR=/usr/local/mysql/mysql.sock \-DMYSQL_TCP_PORT=3306 \-DDEFAULT_CHARSET=utf8 \-DDEFAULT_COLLATION=utf8_general_ci \-DSYSCONFDIR=/etc \-DWITH_EXTRA_CHARSETS=all \-DWITH_INNOBASE_STORAGE_ENGINE=1 \-DWITH_ARCHIVE_STORAGE_ENGINE=1 \-DWITH_BLACKHOLE_STORAGE_ENGINE=1 \-DDOWNLOAD_BOOST=1 \-DWITH_BOOST=/usr/local/src/boost_1_70_0 \-DWITH_SSL=/usr/local/openssl
  **// cmake 오류 시 에러사항 항목 보기**
  /usr/local/mysql-8.0.19/dir_mysql# make
  **// make 오류 시 에러사항 항목 보기**
  /usr/local/mysql-8.0.19/dir_mysql# make install
  ```
    
**MySQL DB초기화**
    
  1. **MySQL그룹 및 유저 생성** 

      ```powershell
      /usr/local/mysql-8.0.19/dir_mysql# groupadd mysql
      /usr/local/mysql-8.0.19/dir_mysql# useradd -r -g mysql -s /bin/false mysql
      ```

  2. **디렉토리 생성** 

      ```powershell
      /usr/local# cd mysql
      /usr/local/mysql# mkdir mysql-files (이름 아무거나 상관없음)
      ```

  3. **권한 주기**

      ```powershell
      /usr/local/mysql# chown -R mysql:mysql /usr/local/mysql
      /usr/local/mysql# chown mysql:mysql mysql-files
      /usr/local/mysql# chmod 750 mysql-files
      ```

  4. **기본 DB 생성**
      ```powershell
      /usr/local/mysql# bin/mysqld --initialize --user=mysql \
      --basedir=/usr/local/mysql \
      --datadir=/usr/local/mysql/data
      ```
      
**MySQL 서버 실행 및 비밀번호 재설정**

  1. **MySQL 서버 실행**

      ```powershell
      /usr/local/mysql# bin/mysqld_safe --user=mysql &
      #bin/mysql -u root -p
      #Enter password: 비밀번호 입력
      다른터미널 창에서
      $ ps -ef | grep mysqld
      -->실행되고 있는 것이 보임
      ```

  2. **비밀번호 재설정 및 서버 종료** 

      ```powershell
      mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'root-password';
      -->여기에는 각자의 자기 비밀번호 넣으면 됨 ''따옴표를 잊지말자!
      mysql> exit
      /usr/local/mysql# bin/mysqladmin -u root -p shutdown
      ```
    

**명령어로 MySQL Server 시작하고 종료하는 방법**  

  ```powershell  
  $ service mysql start
  $ service mysql stop
  $ service mysql restart //서버를 중지했다가 다시 시작
  $ service mysql status //서버 상태 확인. 나가려면 q  
  
  // 명령어 위치 : /usr/local/mysql  
  ```
</details>

<details>
    <summary><b>결과</b></summary>
    <div markdown="1">  
    
![MySQL 실행결과](https://user-images.githubusercontent.com/106207558/209914219-225c57e2-0897-43b9-bc2a-6f8855f92163.png)
</details>

<details>
    <summary><b>에러사항</b></summary>
    <div markdown="1">  
  
  - **참고 사이트 (URL)**    
   https://www.notion.so/mysql-cmake-openssl-1-0a2018f48e414ec89cbed628e27d780d
</details>

**MySQL cmake openssl 에러** 

**MySQL 설치 (Mysql-8.0.19)**

**/usr/local/mysql-8.0.19/dir_mysql# cmake \.. \-DCMAKE_INSTALL_PREFIX=/usr/local/mysql \-DMYSQL_DATADIR=/usr/local/mysql/data \-DMYSQL_UNIX_ADDR=/usr/local/mysql/mysql.sock \-DMYSQL_TCP_PORT=3306 \-DDEFAULT_CHARSET=utf8 \-DDEFAULT_COLLATION=utf8_general_ci \-DSYSCONFDIR=/etc \-DWITH_EXTRA_CHARSETS=all \-DWITH_INNOBASE_STORAGE_ENGINE=1 \-DWITH_ARCHIVE_STORAGE_ENGINE=1 \-DWITH_BLACKHOLE_STORAGE_ENGINE=1 \-DDOWNLOAD_BOOST=1 \-DWITH_BOOST=/usr/local/src/boost_1_70_0**

![MySQL cmake openssl에러](https://user-images.githubusercontent.com/106207558/209915934-5fe1c701-8007-4233-b4cb-f700376f22ef.png)

- **Openssl 설치 [Openssl설치관련링크](https://susoterran.github.io/websrv/openssl_install/)**
    
    ```powershell
    ## OPENSSL 1.1.1 설치
    // https://www.openssl.org/source/
    
    $ wget https://www.openssl.org/source/openssl-1.1.1o.tar.gz
    $ tar xvfz openssl-1.1.1o.tar.gz
    $ cd openssl-1.1.1o/
    // 64bit OS일 경우 
    $ ./config -fPIC --prefix=/usr/local/openssl
    $ make
    $ make install
    ```
    
    - **에러**
    
    ```powershell
    	~# 로 가기위해 cd만 치면 된다. 
    
    [root@localhost ~]# /usr/local/openssl/bin/openssl version //버전확인
    
    ### 라이브러리 설정 및 등록
    [root@localhost ~]# vi /etc/ld.so.conf
    include ld.so.conf.d/*.conf
    /usr/local/openssl/lib  -> 추가 후 저장
    
    	##vi 에디터 방향키 오류시 해결 방법
      cd~명령어를 입력하여 home 디렉터리로 이동
    	vi .exrc 파일 생성 후 아래와 같이 입력
    	set bs=2
      set nocp 
      :wq로 나와서 source .exrc 명령어를 사용하여 설정 적용
      
     
    	
    [root@localhost ~]# ldconfig 
    [root@localhost ~]# /usr/local/openssl/bin/openssl 
    OpenSSL> version
    OpenSSL 1.0.2s  20 Nov 2018
    [root@localhost ~]# /usr/local/openssl/bin/openssl version
    OpenSSL 1.1.1d  10 Sep 2019
    ```
    
- `Need to specify openssl path -DWITH_SSL=/usr/local/openssl`
    - **기존의 cmake를 진행했을 때 결과에서 openssl major, minor, fix version가 공백으로 남겨져있다고 하는 것 같다고 함. 즉 openssl의 경로를 제대로 찾지 못해서 그런 것 같다.**
    - **/usr/local/openssl 말고도 system등 여러 옵션이 있는 것 같다.**
    - **MySQL 컴파일 설치 시, -DWITH_SSL 옵션으로 지정된 컴파일 설치한 OpenSSL라이브러리를 사용할 수 있다. [(관련 링크)](https://susoterran.github.io/mysql/mysql_ssl/)**
    - **문제해결 : cmake .. -DWITH_SSL=/usr/local/openssl작성**
        - **/usr/local/mysql-8.0.19/dir_mysql# cmake \.. \-DCMAKE_INSTALL_PREFIX=/usr/local/mysql \-DMYSQL_DATADIR=/usr/local/mysql/data \-DMYSQL_UNIX_ADDR=/usr/local/mysql/mysql.sock \-DMYSQL_TCP_PORT=3306 \-DDEFAULT_CHARSET=utf8 \-DDEFAULT_COLLATION=utf8_general_ci \-DSYSCONFDIR=/etc \-DWITH_EXTRA_CHARSETS=all \-DWITH_INNOBASE_STORAGE_ENGINE=1 \-DWITH_ARCHIVE_STORAGE_ENGINE=1 \-DWITH_BLACKHOLE_STORAGE_ENGINE=1 \-DDOWNLOAD_BOOST=1 \-DWITH_BOOST=/usr/local/src/boost_1_70_0**
        - **위에에서 명령어에서 추가적으로 \ -DWITH_SSL=/usr/local/openssl 입력**
        
![MySQL cmake openssl에러2](https://user-images.githubusercontent.com/106207558/209916251-b80ac812-df86-46f5-bb25-ddd5ced86b20.png)  

**해결 안될 시 apt install pkg-config 하고서 다시 입력**  
![cmake해결](https://user-images.githubusercontent.com/106207558/209916261-fb97a49c-9649-46aa-ba89-ed261c9cf9df.png)
**나오면 해결!**

### make시 **error: 'numeric_limits' is not a member of 'std'**

(**gcc 버전 문제  해결 방법)**

**sql-common디렉터리 이동 후 sql_string.cc에 vi로 들어가서** 

**#include <limits>** 

**#include<stdexcept> 추가**

**완료!!**

### **make시 ‘size_t’ ..does not name a type 에러가 발생시**

**# cd /usr/local/mysql-8.0.19/include/mysql/components/services 디럭터리로 이동 후** 

**vi page_track_service.h 파일 vi로 들어가서**

  ```powershell
  다음중 아무거나 include하자
  Defined in header <cstddef>
  Defined in header <cstdio>
  Defined in header <cstring>
  Defined in header <ctime>
  Defined in header <cwchar>
  ```