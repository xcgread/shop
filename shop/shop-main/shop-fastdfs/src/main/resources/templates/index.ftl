<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/el/index.css" />
    <script src="js/vue/vue.min.js"></script>
    <script src="css/el/index.js"></script>
</head>
<body>
<div id="app">
    <el-upload
            action="/upload"
            list-type="picture-card"
            :on-preview="handlePictureCardPreview"
  			:on-remove="handleRemove"
  			:before-remove="beforeRemove">
        <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
</div>
<div id="app2">
	<el-upload action="/upload" list-type="picture-card">
	    <i slot="default" class="el-icon-plus"></i>
	    <div slot="file" slot-scope="{file}">
	      <img class="el-upload-list__item-thumbnail" :src="file.response.filePath" alt="" >
	      <span class="el-upload-list__item-actions">
	        <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
	          <i class="el-icon-zoom-in"></i>
	        </span>
	        <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleDownload(file)">
	          <i class="el-icon-download"></i>
	        </span>
	        <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
	          <i class="el-icon-delete"></i>
	        </span>
	      </span>
	    </div>
	</el-upload>
	<el-dialog :visible.sync="dialogVisible">
	  <img width="100%" :src="dialogImageUrl" alt="">
	</el-dialog>
</div>
<script>
    var vm = new Vue({
        el:"#app",
        data:{
            dialogVisible:false,
            dialogImageUrl:''
        },
        methods:{
        	handlePictureCardPreview(file){
                this.dialogImageUrl =file.response.filePath
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
            	window.open('/download?fileId='+file.response.fileId);
            }
	    }
	  }
	var Ctor = Vue.extend(Main)
	new Ctor().$mount('#app2')
</script>
</body>
</html>