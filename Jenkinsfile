pipeline {
  agent any
  stages {
    stage('stage1') {
      parallel {
        stage('stage1') {
          steps {
            echo 'stage1'
          }
        }

        stage('stage2') {
          steps {
            echo 'stage2'
          }
        }

        stage('stage3') {
          steps {
            echo 'stage3'
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

    stage('third') {
      steps {
        waitUntil(initialRecurrencePeriod: 12)
      }
    }

  }
}