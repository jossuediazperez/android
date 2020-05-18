pipeline {
  agent any
  stages {
    stage('stage1') {
      parallel {
        stage('stage1') {
          steps {
            echo 'stage1'
            sleep 30
          }
        }

        stage('stage2') {
          steps {
            echo 'stage2'
            sleep 40
          }
        }

        stage('stage3') {
          steps {
            echo 'stage3'
            sleep 40
          }
        }

        stage('error') {
          steps {
            sleep 25
          }
        }

      }
    }

    stage('second') {
      steps {
        withGradle() {
          sh 'echo "11"'
        }

      }
    }

  }
}