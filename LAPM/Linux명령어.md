### 리눅스 명령어

[WEBDIR :: 리눅스 디렉토리 구조 (tistory.com)](https://webdir.tistory.com/101)

- ls - 현재 위치의 파일목록을 조회하는 명령어
    - 주요 옵션으로는
    - ls -l : 파일들의 상세정보를 나타낸다.
    - ls -a : 숨어있는 파일들도 표시한다.
    - ls -t : 파일들을 생성된 시간별로(최신것 부터) 표시
    - ls -rt : 파일들을 오래된 시간부터 표시
    - ls -F : 파일을 표시할 때 마지막에 유형을 나타내는 파일명을 끝에 표시 (’/’ - 디렉토리, ‘*’ - 실행파일, ‘@’ - 링크)
- cd - 디렉토리를 이동하는 명령어
    - cd ~  :  홈디렉토리로 바로 이동
    - cd .. : 상위 디렉토리로 이동
    - cd /dir : 절대경로 dir로 이동할 경우 사용
    - cd - : 이동하기 바로전의 디렉토리로 이동
- touch - 파일의 용량이 0인 파일을 생성, 날짜를 변경하는 명령어
    - touch filename : filename의 파일을 생성한다.
    - touch -c filename : filename의 시간을 현재시간으로 변경.
    - touch -t 200001011200 filename : filename의 시간을 날짜정보(YYYYMMDDhhmm)로 변경
    - touch -r filename1 filename2 : filename2의 날짜정보를 filename1의 날짜정보와 같게 변경
- mkdir - 디렉토리를 생성하는 명령어
    - mkdir은 새로운 디렉토리를 만들 때 사용
    - mkdir -p dirname/subdname : 존재하지 않는 디렉토리의 하위 디렉토리까지 생성
    - mk -m 644 dirname : 특정 퍼미션을 갖는 디렉토리를 생성한다.
- rmdir - 빈 디렉토리를 삭제할 경우에 사용
- cp - 파일을 복사하는 명령어
    - cp file cfile : file을 cfile이라는 이름으로 복사한다.
    - cp -f file cfile : 복사할 때 복사대상이 있으면 지우고 강제로 복사한다.
    - cp -R dir cdir : 디렉토리를 복사할 때 사용하며, 폴더 안의 모든 하위경로와 파일들을 모두 복사한다.
- mv - 파일을 이동시키는 명령어 or 이름을 바꾸는 명령어
    - mv fname mfname : fname의 파일을 mfname의 이름으로 이동/변경 한다.
    - mv -b fname mfname : mfname의 파일이 존재하면 mfname을 백업한뒤에 이동한다.
    - mf -f fname mfname : mfname의 파일이 존재하면 백업 없이 덮어쓴다.
- rm - 파일을 제거하는 명령어
    - rm fname : fname를 삭제한다.
    - rm -f name : fname을 묻지 않고 삭제한다.
    - rm -r dir : 폴더dir을 삭제한다. (디렉토리는 -r 옵션을 주어야 한다.)
- cat - 파일의 내용을 화면에 출력하거나 파일을 만드는 명령어
    - cat fname : fname의 내용을 출력
    - cat fname1 fname2 : fname1과 fname2의 내용을 이어서 출력한다.
    - cat fname1 fname2 | more : fname1, fname2를 출력하는데 페이지별로 출력한다.
    - cat fname1 fname2 | head : fname1, fname2를 출력하는데 처음부터 10번째 줄 까지만 출력한다.
    - cat - fname1 fname2 | tail : fname1, fname2를 출력하는데 끝에서부터 10번째 줄 까지만 출력한다
        
        ```powershell
        $ ls
        file1  file2  file3
        
        $ cat file1
        1
        
        $ cat file2
        2
        
        $ cat file3
        3
        
        $ cat file1 file2 > file1_2
        $ ls
        file1  file1_2  file2  file3
        
        $ cat file1_2
        1
        2
        
        $ cat file1 >> file2
        $ cat file2
        2
        1
        
        $ cat > file4
        hello
        world
        (작성이 끝나면 ctrl +d 로 파일 저장)
        
        $ cat file4
        hello
        world
        ```
        
- redirection - 화면에 출력되는 결과를 파일로 저장하는 명령어
    - 리눅스 스트림의 방향을 조정하는 명령어
    - 명령 > 파일 : 명령의 결과를 파일로 저장한다.
        - cat fname1 fname2 > fname3 : fname1, fname2를 출력하고,fname3이라는 파일에 저장한다.
    - 명령 >> 파일 : 명령의 결과를 파일에 추가한다.
        - cat fname4 >> fname3 : fname3에 fname4의 내용을 추가한다.
    - 명령 < 파일 : 파일의 데이터를 명령에 입력한다.
        - cat < fname1 : fname1의 내용을 출력한다.
    - ex ) cat < fname1 > fname2 : fname1의 내용을 출력하는 결과물을 fname2에 저장한다.
- alias - 자주 수행하는 명령어들을 쉽게 사용할 수 있도록 설정하는 명령어
    - alias는 자주 사용하는 명령어를 간단한 명령어로 설정하는 명령어 해제 하고자할 때는 unalias를 사용한다.
    - ex) alias ls =’ls -l’ : ls를 실행하면 -l옵션을 갖는 ls를 실행
    - alias : 현재  alias목록을 출력한다.
    - unalias new : new 라는 alias를 해제 한다.
- head - 파일의 앞부분을 보고싶은 줄 수 만큼 보여준다. 옵션 미지정 = 상위 파일 10 줄 출력
    
    ```powershell
    $ cat testfile
    1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    12
    13
    14
    15
    
    $ head -3 testfile
    1
    2
    3
    
    $ head testfile
    1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    ```
    
- tail - 파일의 뒷부분을 보고싶은 줄 수 만큼 보여준다. 옵션 미지정 = 파일 하위 10줄 출력
    
    tail -F :ㅇ 옵션을 주고 실행하면 파일 내용을 화면에 계속 띄워주고 파일이 변경하게 되면 새로운 업데이트 내용을 갱신해준다. 주로 실시간으로 내용이 추가되는 로그파일을 모니터링할 때 유용 
    
    ```
    $ cat testfile
    1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    12
    13
    14
    15
    
    $ tail -3 testfile
    13
    14
    15
    
    $ tail testfile
    6
    7
    8
    9
    10
    11
    12
    13
    14
    15
    
    $ tail -F testfile
    6
    7
    8
    9
    10
    11
    12
    13
    14
    15
    (명령어가 종료되지 않고 계속 해당 화면을 출력하며, 파일 내용 변경시 자동으로 갱신해준다)
    ```
    
- find - 특정 파일이나 디렉토리 검색. 기본적인
    
    find [검색경로] -name [파일명] : 파일명은 직접 풀네임을 입력해도 되지만, 예제 처럼 특정 조건을 적용해 검색할 수 있다. 주로 특정 확장자명을 찾기 위해 많이 사용 
    
    ```powershell
    $ ls
    dir1/  dir3/  file1  file3  picture1.jpg  picture3.jpg
    dir2/  dir4/  file2  file4  picture2.jpg  picture4.jpg
    
    $ find ./ -name 'file1'
    ./file1
    
    $ find ./ -name "*.jpg"
    ./picture1.jpg
    ./picture2.jpg
    ./picture3.jpg
    ./picture4.jpg
    ```
    
    여기서 그치지 않고, 확장자가 .jpg인 파일만 찾아서 바로 삭제할 수도 있다. exec옵션을 사용해 다음과 같이 처리하면 된다.
    
    ```powershell
    $ find ./ -name "*.jpg" -exec rm {} \;
    $ ls
    dir1/  dir2/  dir3/  dir4/  file1  file2  file3  file4
    ```
    
    그리고 다음과 같이 -type 옵션을 주면, 디렉토리나 파일만 지정해서 검색할수도 있다.
    
    ```powershell
    $ find ./ -type d
    ./
    ./dir1
    ./dir2
    ./dir3
    ./dir4
    
    $ find ./ -type f
    ./file1
    ./file2
    ./file3
    ./file4
    ```
    
    다음과 같이 wc -l 옵션과 같이 사용하면, 특정 디렉토리에 find 조건에 맞는 결과 값이 몇개 존재하는지 숫자로 간편히 알아볼 수 있다.
    
    ```powershell
    $ find ./ -type f | wc -l
    4
    ```
    
    지금처럼 파일 갯수가 4개밖에 없을땐 그냥 일일이 세면 되지만, 파일이 수백, 수천, 수십만 개가 있을땐 아주 유용
    
- lsblk - 리눅스 디바이스 정볼르 출력하는 명령어 blkid보다 더 상세정보 출력
    
    옵션없이 사용하면 모든 스토리지 디바이스를 출력한다.
    
    - lsblk -a : 모든 장치들을 출력한다.
    - lsblk -f : 옵션을 사용하면 파일 시스템 정보까지 출력한다.
    - lsblk -t : 옵션을 사용하면 topology정보도 출력한다.
    - lsblk -l : 포맷한 디스크 목록을 출력한다.
- mount - 보조기억장치(하드디스크, usb등)을 디렉토리로서 사용할 수 있게 하는 명령어
    - mount [option] [device] [directory]
    - -a : /etc/fstab에 기록되어 있는 모든 파일시스템을 마운트할 때 사용
    - -t 파일시스템_타입 : 파일시스템의 유형을 지정하는 옵션, 지정하지 않으면 /etc/fstab참조
- df - 리눅스 시스템 전체의(마운트 된) 디스크 여유 공간 확인
    - 파일시스템, 디스크크기, 사용량, 여유공간, 사용률, 마운트지점 순으로 나타남
    - df -a : 모든 파일 시스템 출력
    - df -h : 사람이 읽기 쉬운 형태(단위)로 출력(기본 킬로바이트 단위)
    - df -t : 보여주는 목록을 파일시스템 타입으로 제한
    - df -l : 출력하는 목록을 로컬 파일 시스템으로만 제한
    - FileSystem = 리눅스에 마운트된 파일 시스템 목록
    - SIZE(1k-blocks) = 전체용량
    - Used = 사용량
    - Available = 남은용량
    - Use% = 용량 대비 사용량에 대한 퍼센트
    - Mounted on  = 마운트된 지점 (경로)
- ps - 현재 실행중인 프로세스 목록과 상태를 보여준다.
    - ps [option]
    - ps -A : 모든 프로세스를 출력한다.
    - ps a : 터미널과 연관된 프로세를 출력하는 옵션. 보통 x옵션과 연계하여 모든 프로세스를 출력할 때 사용
    - ps -a : 세션 리더(일반적으로 로그인 셸)을 제외하고 데몬 프로세스처럼 터미널에 종속되지 않은 모든 프로세스 출력
    - ps -e : 커널 프로세스를 제외한 모든 프로세스 출력
    - ps -f : 풀 포맷으로 보여준다. 유닉스 스타일로 출력해주는 옵션으로 UID,PID,PPID등이 함께 표시된다.
    - ps -l : 긴 포맷으로 보여준다. 프로세스의 정보를 길게 보여주는 옵션으로 우선순위와 관련된 PRI와 NI값을 확인할 수 있다.
    - ps -o 값: 출력 포맷을 지정하는 옵션으로 pid,tty,time,cmd등을 지정할 수 있다.
    - ps -M : 64비트 프로세스들을 보여준다.
    - ps -m : 프로세스들 뿐만 아니라 커널 스레드들도 보여준다.
    - ps - p : 특정 PID를 지정할 때 사용한다.
    - ps -r : 현재 실행 중인 프로세서를 보여준다.
    - ps u : 프로세스의 소유자를 기준으로 출력.
    - ps -u : 특정 사용자의 프로세스를 확인할 때 사용. 사용자를 지존하지 않으면 현재 사용자를 기준으로 정보를 출력
    - ps x : 데몬프로세스처럼 터미널에 종속되지 않는 프로세스를 출력. 보통 a옵션과 결합하여 모든 프로세스를 출력할 때 사용
    - ps -x : 로그인 상태에 있는 동안 아직 완료되지 않은 프로세서들을 보여준다.
    - 특정 프로세스를 확인하는데 grep라는 명령어와 함께 사용한다.
        - System V계열에서는 ps -ef를 가장 많이 사용
        - ps -ef | grep ‘프로세스명’
        - BSD계열에선 ps aux를 가장 많이 사용
        - ps aux | grep ‘프로세스명’
    - UID = SYSTEM V 계열에서 나타나는 항목으로 프로세스 소유자의 이름
    - PID = 프로세스의 식별번호
    - PPID = 부모 프로세스 ID
    - STAT = 현재 프로세스의 상태코드
    - CP = 짧은 기간 동안의 CPU 사용률
- kill - 프로세스에 특정한 signal을 보낸다.  종료되지 않는 프로세스를 종료 시킬 때 사용
    - kill -l  : kill 옵션 정보를 확인할 수 있다.
    - kill -[옵션 or 시그널 번호] PID
    - 1번 연결끊기. 프로세스의 설정파일을 다시 읽음
    - 2번 인터럽터
    - 3번 종료
    - 4번 잘못된 명령
    - 5번 트렙추적
    - 7번 버스 에러
    - 9번 죽이기
    - 15번 소프트웨어 종료 시그널
    - 19번 정지