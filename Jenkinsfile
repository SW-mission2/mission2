pipeline {
    agent any
    
    stages {
        
        stage('Checkout'){
            steps{
            //Jenkins SCM 설정을 사용
            //jenkins server approachs remote repository
            //소스코드 Checkout 후 workspace에 저장
            checkout scm
            }

        }
        stage('Build'){
        	steps{
        	//javac -d <output_directory> <source_files>
        	//<source_files> in Jenkins workspace
        	//Jenkins workspace : 협업자 각 개인의 local
        	//Windows와 Mac에 따라 분기
        	
        	bat 'javac -encoding UTF-8 -d classes src/*.java'
        	}
  
        }
        stage('Test'){
            steps{
            
              
                

                // JUnit 5 테스트 실행
                bat """
                    java -cp ${classpath} org.junit.platform.console.ConsoleLauncher --scan-classpath > test_results.txt
                """
			
            }

        }
       
        post{
            always{
                // 테스트 결과 파일을 저장하기 위해 아카이브
            	archiveArtifacts 'test_results.txt'
            }
            failure{
                echo 'Build or Test failed'
                
            }
            success{
                echo 'Build and Test succeeded' 
            }

        }
    }
  
}
