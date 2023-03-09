package org.ruoyi

//获取文件内容
def GetRepoFile(projectId,filePath, branchName ){
   //GET /projects/:id/repository/files/:file_path/raw
   apiUrl = "/projects/${projectId}/repository/files/${filePath}/raw?ref=${branchName}"
   response = HttpReq('GET', apiUrl)
   return response
}

//更新文件内容
def UpdateRepoFile(projectId,filePath,fileContent, branchName){
    apiUrl = "projects/${projectId}/repository/files/${filePath}"
    reqBody = """{"branch": "${branchName}","encoding":"base64", "content": "${fileContent}", "commit_message": "update a new file"}"""
    response = HttpReqByPlugin('PUT',apiUrl,reqBody)
    println(response)

}

//创建文件
def CreateRepoFile(projectId,filePath,fileContent, branchName){
    apiUrl = "projects/${projectId}/repository/files/${filePath}"
    reqBody = """{"branch": "${branchName}","encoding":"base64", "content": "${fileContent}", "commit_message": "update a new file"}"""
    response = HttpReqByPlugin('POST',apiUrl,reqBody)
    println(response)

}

// 封装HTTP
def HttpReqByPlugin(reqType, reqUrl,reqBody ){
    def gitServer = "http://192.168.150.22:8076/api/v4"
    withCredentials([string(credentialsId: '058b7907-ebe2-4d14-9b91-1ac72e071c59', 
                            variable: 'GITLABTOKEN')]) {
        response = httpRequest acceptType: 'APPLICATION_JSON_UTF8', 
                    consoleLogResponseBody: true, 
                    contentType: 'APPLICATION_JSON_UTF8', 
                    customHeaders: [[maskValue: false, name: 'PRIVATE-TOKEN', value: "${GITLABTOKEN}"]], 
                    httpMode: "${reqType}", 
                    url: "${gitServer}/${reqUrl}", 
                    wrapAsMultipart: false,
                    requestBody: "${reqBody}"

    }
    return response
}

//发起HTTP请求
def HttpReq(method, apiUrl){
    response = sh  returnStdout: true, 
        script: """ 
            curl --location --request ${method} \
            http://192.168.150.22:8076/api/v4/${apiUrl} \
            --header 'PRIVATE-TOKEN: aajUzz7t1AbNZBQsc5Ny' 
        """
    try {
        response = readJSON text: response - "\n"
    } catch(e){
        println(e)
    }
    
    return response

}
