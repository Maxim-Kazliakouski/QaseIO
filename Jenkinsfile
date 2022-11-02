pipeline {
   agent any

   tools {
      // Install the Maven version configured as "M3" and add it to the path.
      maven "M2"
   }
//     triggers {
//         cron('0 8 * * *')
//     }
    parameters {
      gitParameter ( branch: '',
                     branchFilter: 'origin/(.*)',
                     defaultValue: 'master',
                     description: '',
                     name: 'BRANCH',
                     quickFilterEnabled: true,
                     selectedValue: 'NONE',
                     sortMode: 'NONE',
                     tagFilter: '*',
                     type: 'PT_BRANCH' )
    }

   stages {
//       stage('Testing: UI tests') {
//
//          steps {
//
//           script {
//
//           try {
//
//             // Get some code from a GitHub repository
//             git branch: "${params.BRANCH}",  url: 'https://github.com/Maxim-Kazliakouski/QaseIO.git'
//
//             withCredentials ([
//             string(credentialsId: 'qase_token',
//             variable: 'TOKEN_CREDENTIALS'),
//             string(
//             credentialsId: 'qase_password',
//             variable: 'PASSWORD_CREDENTIALS')
//             ]) {
//
//             // Run Maven on a Unix agent.
//             sh "mvn clean -Dsurefire.suiteXmlFiles=src/test/resources/chromeLaunchTest.xml \
//                 -P UI -Dbrowser=$BROWSER \
//                 -Dheadless=$HEADLESS \
//                 -Dqase.username=$USERNAME \
//                 -Dqase.password=$PASSWORD_CREDENTIALS \
//                 -Dtoken=$TOKEN_CREDENTIALS \
//                 -DtestRun=$TESTRUN \
//                 -DcodeProject=$CODEPROJECT test"
//                }
//           }
//                    catch (Exception error) {
//           unstable('Testing failed')
//          }
//      }
// }
//
//             // To run Maven on a Windows agent, use
//             // bat "mvn -Dmaven.test.failure.ignore=true clean package"
//
//          post {
//             // If Maven was able to run the tests, even if some of the test
//             // failed, record the test results and archive the jar file.
//             success {
//                junit '**/target/surefire-reports/TEST-*.xml'
//             }
//          }
//      }

      stage('Generating Allure report') {
         steps {
             script {
                     allure([
                             includeProperties: false,
                             jdk: '',
                             properties: [],
                             reportBuildPolicy: 'ALWAYS',
                             results: [[path: 'target/allure-results']]
                     ])
              }
          }
      }

      stage('Testing: API tests') {
         steps {

          script {

               try {
          // Get some code from a GitHub repository
          git branch: "${params.BRANCH}", url: 'https://github.com/Maxim-Kazliakouski/QaseIO.git'

            withCredentials ([
            string(credentialsId: 'qase_token',
            variable: 'token')
            ]) {

            // Run Maven on a Unix agent.
            sh "mvn clean -Dsurefire.suiteXmlFiles=src/test/resources/APItests.xml \
             -P API -Dtoken=$token \
             -Dapi=$API test"

            // To run Maven on a Windows agent, use
            // bat "mvn -Dmaven.test.failure.ignore=true clean package"
                }
           }
               catch (Exception error) {
          unstable('Testing failed')
         }
    }
}

         post {
            // If Maven was able to run the tests, even if some of the test
            // failed, record the test results and archive the jar file.
            success {
               junit '**/target/surefire-reports/TEST-*.xml'
                    }
               }
          }

      stage('Generating HTML report') {
         steps {
                  publishHTML([allowMissing: false,
                  alwaysLinkToLastBuild: false,
                  keepAll: true,
                  reportDir: 'reports',
                  reportFiles: 'index.html',
                  reportName: 'API Report',
                  reportTitles: '',
                  useWrapperFileDirectly: true])
            }
         }
      }
   }