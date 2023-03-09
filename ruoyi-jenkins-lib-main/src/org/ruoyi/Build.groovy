package org.ruoyi

/*UnitTest
后端项目运行单元测试
service, web
*/
def UnitTest(){

    if ( "${JOB_NAME}".endsWith("-service")){
        switch("${env.buildTools}") {
            case "maven":
                sh "mvn test"
                break
            case "gradle":
                sh "gradle test"
                break
            default:
                echo "error" 
                break                
        }
    }
}

// build
def Build(){
    // 构建阶段
    switch("${env.buildTools}") {
        case "npm":
            sh "cd ${env.ModDir} && npm install  && npm run build:prod"
            break   
        case "yarn":
            sh "yarn build"
            break  
        case "maven":
            sh "mvn clean package -Dmaven.test.skip=true"
            break
        case "gradle":
            sh "gradle clean build -x test"
            break
        default:
            echo "error" 
            break                         
    }

    //前端项目跳过单元测试
    //service, web
    if ( "${JOB_NAME}".endsWith("-web")){
        env.skipUnitTest = 'true'
    } else {
        //后端项目运行单元测试
        env.skipUnitTest = 'false'
    }
}

