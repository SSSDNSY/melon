// 模态框
export const remoteData = {
    methods: {
      getRemoteData(){
            //动态数据
            if(this.conf.dataType === 'dynamic'){
            //   this.get(this.Apis.dictDataList+"?access_token="+localStorage.getItem("token"), {dictType:this.conf.action}, res => {
              //     if(res.code=="200"){
              //       if(this.conf.options.length===0){
            //         this.conf.options = this.conf.options.concat(res.result);
            //       }
            //       //this.conf.options =this.conf.options.concat(res.result);
            //     }
            //   });
                // this.$axios.get(this.conf.action).then(res => {
                //     if(this.conf.options.length===0){
                //         this.conf.options = this.conf.options.concat(res.data);
                //     }
                // })
            }
        },
      upDownLoad(fileParams){
        this.$axios({
          method:'POST',
          url:"/proc-api/"+this.Apis.downFiles+"?access_token="+util.cookies.get('token'),
          data:{
            fileName: fileParams.fileName,
            filePath:fileParams.filePath
          },
          responseType: 'blob'
        })
          .then(response => {
            var patt = new RegExp('filename=([^;]+\\.[^\\.;]+);*')
            var contentDisposition = decodeURI(response.headers['content-disposition'])
            var result = patt.exec(contentDisposition)
            var fileName = result[1]

            let  resdata = response.data;
            const blob = new Blob([resdata],{
              type: 'application/octet-stream;charset=utf-8'
            })

            // 从响应头中获取文件名称
            if ('download' in document.createElement('a')) {
              // 非IE下载
              const elink = document.createElement('a')
              elink.download = fileName
              elink.href = URL.createObjectURL(blob)
              elink.style.display = 'none'
              document.body.appendChild(elink)
              elink.click()
              URL.revokeObjectURL(elink.href) // 释放URL 对象
              document.body.removeChild(elink)
            } else {
              // IE10+下载
              navigator.msSaveBlob(blob, fileParams.fileName)
            }
          })
      }
    }
}

export const changeId = {
    methods: {
        handlerChangeId(val){
            let idArray = this.getFormId(this.props._id);
            console.log(idArray);
            if(idArray.includes(val)){  //如果存在id相等，则提示
                this.$message.error('该ID已经存在，请修改');
                this.props.id=this.props._id;
            }else{
                this.props._id=val;
            }
        }
    }
}
