const funcs = {
    convertToTree(array, idFieldName, parentFieldName, childrenFieldName) {
        // 배열 복사
        let cloned = array.slice();

        // 끝부터 0까지 배열의 요소마다
        for (let i = cloned.length - 1; i > -1; --i) {
            // 부모 아이디
            let parentId = cloned[i][parentFieldName];
            
            // 부모가 있다면 (자식이면)
            if(parentId){
                // 그 부모를 구함
                let filtered = array.filter(element =>
                    element[idFieldName].toString() == parentId.toString()
                );
                
                // 부모 존재 확인
                if(filtered.length){
                    // 부모 변수
                    let parent = filtered[0];

                    // 부모의 자식이 이미 있으면
                    if(parent[childrenFieldName]){
                        // 맨 앞에 삽입
                        parent[childrenFieldName].unshift(cloned[i]);
                    }else {
                        // 아니면 부모의 첫 자식으로 들어간다
                        // parent[childrenFieldName] = []; cloned[i];
                        parent[childrenFieldName] = [];
                        parent[childrenFieldName].unshift(cloned[i]);
                    }
                }
                // 처리한 요소는 제거
                cloned.splice(i, 1);
            }
        }

        return cloned;
    },
}

export default funcs