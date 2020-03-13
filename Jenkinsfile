pipeline {

    agent any

    environment {

        DBTOKEN     = credentials('cce01329-56d1-4ed4-a5ba-c38fcb0b041f')
        DBURL = credentials('6c94b074-def3-41e9-b35e-7eadca905747')
        REPO_NOTEBOOK_PATH = 'notebooks/'
        DEV_LIBRARY_PATH = 'dbfs:/offshore-demo/dev/libraries/'
	      CERT_LIBRARY_PATH = 'dbfs:/offshore-demo/cert/libraries/'
	      PROD_LIBRARY_PATH = 'dbfs:/offshore-demo/prod/libraries/'

    }

stages {

    stage('Configure databricks cli') {

      steps{

        sh '''
            echo "$DBURL
            $DBTOKEN" | databricks configure --token
        '''
      }

    }

    stage('Configure databricks cli') {

      steps{

        sh '''
            echo "$DBURL
            $DBTOKEN" | databricks configure --token
        '''
      }

    }

    stage('Deploy notebooks') {

      steps{

        script {

          if ("$GIT_BRANCH" == "origin/develop") {
            stage ('Deploy notebooks to CERT') {
              sh '''
                databricks workspace import_dir $REPO_NOTEBOOK_PATH $WORKSPACE_CERT_NOTEBOOK_PATH --overwrite
              '''
            }
          }

          if ("$GIT_BRANCH" == "origin/master") {
            stage ('Deploy notebooks to PROD') {
              sh '''
                databricks workspace import_dir $REPO_NOTEBOOK_PATH $WORKSPACE_PROD_NOTEBOOK_PATH --overwrite
              '''
            }
          }

        }
        
      }
    }
  }
}

//added comment
