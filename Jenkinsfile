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
            bat 'javac -encoding UTF-8 -d classes test\\SearchPerformanceTest.java'
           
        	}
  
        }
        stage('Test'){
            steps{
              script {
                    // JUnit 플랫폼 콘솔 런처 JAR 파일 경로 설정
                    def junitJarPath = "plugins/junit-platform-console-standalone-1.7.1.jar"
                    // JUnit 5 테스트 실행
                    bat "java -cp classes;${junitJarPath} org.junit.platform.console.ConsoleLauncher --scan-classpath > Junit_test.txt"
                }
            }

        }
        stage('Performance_Test') {
            steps {
                script {
                    // JUnit 플랫폼 콘솔 런처 JAR 파일 경로 설정
                    def junitJarPath = "plugins/junit-platform-console-standalone-1.7.1.jar"
                    // 성능 테스트 실행
                    bat "java -cp classes;${junitJarPath} SearchPerformanceTest > Performance_test.txt"
                }
            }
        }
        
    }
    post{
            always{
                // 테스트 결과 파일을 저장하기 위해 아카이브
            	archiveArtifacts 'Junit_test.txt'
                archiveArtifacts 'Performance_test.txt'
            }
            failure{
                echo 'Build or Test failed'
                
            }
            success{
                echo 'Build and Test succeeded' 
            }

        }
  
}
