<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Title</title>
<link rel="stylesheet" href="css/el/index.css" />
<script src="js/vue/vue.min.js"></script>
<script src="css/el/index.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/axios/0.18.1/axios.js"></script>
<style>
.avatar-uploader .el-upload {
	border: 1px dashed #d9d9d9;
	border-radius: 6px;
	cursor: pointer;
	position: relative;
	overflow: hidden;
}

.avatar-uploader .el-upload:hover {
	border-color: #409EFF;
}

.avatar-uploader-icon {
	font-size: 28px;
	color: #8c939d;
	width: 178px;
	height: 178px;
	line-height: 178px;
	text-align: center;
}

.avatar {
	width: 178px;
	height: 178px;
	display: block;
}
</style>
</head>
<body>
	minio上传头像
	<div id="minio">
		<el-upload class="avatar-uploader" action="/minio/upload"
			:show-file-list="false" :on-success="handleAvatarSuccess"
			:before-upload="beforeAvatarUpload"> <img v-if="imageUrl"
			:src="imageUrl" class="avatar"> <i v-else
			class="el-icon-plus avatar-uploader-icon"></i> </el-upload>
	</div>
	<script>
var Main = {
	    data() {
	      return {
	        imageUrl: ''
	      };
	    },
	    methods: {
	      handleAvatarSuccess(res, file) {
	        this.imageUrl = file.response.data.url;
	        console.log(file.response.data.url);
	      },
	      beforeAvatarUpload(file) {
	        const isJPG = file.type === 'image/jpeg';
	        const isLt2M = file.size / 1024 / 1024 < 2;

	        if (!isJPG) {
	          this.$message.error('上传头像图片只能是 JPG 格式!');
	        }
	        if (!isLt2M) {
	          this.$message.error('上传头像图片大小不能超过 2MB!');
	        }
	        return isJPG && isLt2M;
	      }
	    }
	  }
	var Ctor = Vue.extend(Main)
	new Ctor().$mount('#minio')
</script>

	oss照片墙
	<div id="app">
		<el-upload action="http://xuzhihao-shop.oss-cn-beijing.aliyuncs.com"
			:data="dataObj" list-type="picture-card"
			:before-upload="beforeUpload" :on-remove="handleRemove"
			:on-success="handleAvatarSuccess" :on-preview="handlePreview">
		<i slot="default" class="el-icon-plus"></i> </el-upload>
		<el-dialog :visible.sync="dialogVisible"> <img
			width="100%" :src="dialogImageUrl" alt=""> </el-dialog>
	</div>
	<script>
    var vm = new Vue({
        el:"#app",
        data:{
            dialogVisible:false,
            dialogImageUrl:'',
            dataObj: {
                policy: '',
                signature: '',
                key: '',
                ossaccessKeyId: '',
                dir: '',
                host: '',
                callback: ''
              },
        },
        methods:{
      	 	handlePreview(file){
                this.dialogImageUrl =file.response.data.filename;
                this.dialogVisible = true;
            },
        	handleRemove(file) {
                console.log(file.response.data.filename);
            },
            beforeUpload(file) {
            	let _self = this;
            	return axios.get('/aliyun/oss/policy')
           	      .then((response) => {
           	        _self.dataObj.policy = response.data.data.policy;
                    _self.dataObj.signature = response.data.data.signature;
                    _self.dataObj.ossaccessKeyId = response.data.data.accessKeyId;
                    _self.dataObj.key = response.data.data.dir + '/'+file.name;
                    _self.dataObj.dir = response.data.data.dir;
                    _self.dataObj.host = response.data.data.host;
                    _self.dataObj.callback = response.data.data.callback;
           	      })
           	      .catch((error) => {
           	        console.log(error);//异常
           	      });
           },
           handleAvatarSuccess(res, file) {
               this.dialogImageUrl=this.dataObj.host + '/' + this.dataObj.dir + '/' + file.name;
          }
        }
    })
</script>

	fastdfs缩略图
	<div id="fastdfs">
		<el-upload action="/fastdfs/upload" list-type="picture-card">
		<i slot="default" class="el-icon-plus"></i>
		<div slot="file" slot-scope="{file}">
			<img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
			<span class="el-upload-list__item-actions"> <span
				class="el-upload-list__item-preview"
				@click="handlePictureCardPreview(file)"> <i
					class="el-icon-zoom-in"></i>
			</span> <span v-if="!disabled" class="el-upload-list__item-delete"
				@click="handleDownload(file)"> <i class="el-icon-download"></i>
			</span> <span v-if="!disabled" class="el-upload-list__item-delete"
				@click="handleRemove(file)"> <i class="el-icon-delete"></i>
			</span>
			</span>
		</div>
		</el-upload>
		<el-dialog :visible.sync="dialogVisible"> <img
			width="100%" :src="dialogImageUrl" alt=""> </el-dialog>
	</div>
	<script>
var Main = {
	    data() {
	      return {
	        dialogImageUrl: '',
	        dialogVisible: false,
	        disabled: false
	      };
	    },
	    methods: {
	    	handlePictureCardPreview(file){
                this.dialogImageUrl =file.response.filePath
                this.dialogVisible = true;
            },
        	handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handleDownload(file){
            	window.open('/fastdfs/download?fileId='+file.response.fileId);
            }
	    }
	  }
	var Ctor = Vue.extend(Main)
	new Ctor().$mount('#fastdfs')
</script>

	minio列表缩略图
	<div id="app3">
		<el-upload action="/minio/upload" list-type="picture"
			:file-list="fileList" :on-preview="handlePreview"
			:on-remove="handleRemove"> <el-button size="small"
			type="primary">点击上传</el-button>
		<div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
		</el-upload>
		<el-dialog :visible.sync="dialogVisible"> <img
			width="100%" :src="dialogImageUrl" alt=""> </el-dialog>
	</div>
	<script>
    var app3 = new Vue({
        el:"#app3",
        data:{
            dialogVisible:false,
            dialogImageUrl:'',
            fileList: [{name: '1100.jpg', url: 'https://xuzhihao-shop.oss-cn-beijing.aliyuncs.com/shop/images/20210105/1100.jpg'}]
        },
        methods:{
        	handlePreview(file){
        		 this.dialogImageUrl =file.url?file.url:file.response.data.url;
	              this.dialogVisible = true;
            },
        	handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            beforeRemove(file, fileList) {
              return this.$confirm(`确定移除 ${ file.name }？`);
            }
        }
    })
    </script>
</body>
</html>