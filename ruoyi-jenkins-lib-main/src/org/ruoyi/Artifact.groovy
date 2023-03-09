package org.devops

// 上传制品
def PushNexusArtifact(repoId, targetDir, pkgPath, pkgName){
    withCredentials([usernamePassword(credentialsId: '48423c55-3ad8-4929-9a83-3bfaafca30dc', \
                                    passwordVariable: 'PASSWD', 
                                    usernameVariable: 'USERNAME')]) {
        sh """
            curl -X 'POST' \
              "http://192.168.150.21:8081/service/rest/v1/components?repository=${repoId}" \
              -H 'accept: application/json' \
              -H 'Content-Type: multipart/form-data' \
              -F "raw.directory=${targetDir}" \
              -F "raw.asset1=@${pkgPath}/${pkgName};type=application/java-archive" \
              -F "raw.asset1.filename=${pkgName}" \
              -u ${USERNAME}:${PASSWD}
        """
    }
}
