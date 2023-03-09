package org.ruoyi

// echo "${JOB_NAME}" // JOB 名称 ruoyi-test1/ruoyi2-system-service
// echo "${JOB_NAME.split('-')[2]}" //system  视情况而定

def getDir(){
    env.ModName = "${JOB_NAME.split('-')[2]}"
    switch("${ModName}"){
        case "ui":
            env.ModDir = "ruoyi-ui/"
            break
        case "monitor":
            env.ModDir = "ruoyi-visual/ruoyi-monitor"
            break
        case "gateway":
            env.ModDir = "ruoyi-gateway/"
            break
        case "auth":
            env.ModDir = "ruoyi-auth/"
            break
        case "system":
            env.ModDir = "ruoyi-modules/ruoyi-system/"
            break
        case "gen":
            env.ModDir = "ruoyi-modules/ruoyi-gen/"
            break
        default:
            echo "DIR ERROR"
            break
    }
}

