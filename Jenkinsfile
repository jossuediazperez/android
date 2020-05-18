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

      }
    }

  }
}