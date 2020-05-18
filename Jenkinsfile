pipeline {
  agent any
  stages {
    stage('stage1') {
      parallel {
        stage('stage1') {
          steps {
            echo 'stage1'
            sleep 10
          }
        }

        stage('stage2') {
          steps {
            echo 'stage2'
            sleep 20
          }
        }

        stage('stage3') {
          steps {
            echo 'stage3'
            sleep 20
          }
        }

        stage('') {
          steps {
            sleep 15
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