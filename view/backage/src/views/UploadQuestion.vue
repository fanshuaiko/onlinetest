<template>
  <section>
    <!--    单选题上传-->
    <el-upload
      class="upload-demo1"
      drag
      action="backage-api/question/choice/file"
      :data="uploadQuestionType.single"
      :on-success="uploadSuccess"
      :on-error="uploadFail"
      multiple>
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将选择题文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只能上传xls/xlsx文件，且不超过500kb</div>
      <div class="el-upload__tip" slot="tip">
        <el-button size="mini" type="primary">
          <a href="/backage-api/file/single" style="color: white;text-decoration: none">
            下载选择题模板
          </a>
          <i class="el-icon-download el-icon--right"></i></el-button>
      </div>
    </el-upload>

    <!--    判断题上传-->
    <el-upload
      class="upload-demo2"
      drag
      action="backage-api/question/choice/file"
      :data="uploadQuestionType.judge"
      :on-success="uploadSuccess"
      :on-error="uploadFail"
      multiple>
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将判断题文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只能上传xls/xlsx文件，且不超过500kb</div>
      <div class="el-upload__tip" slot="tip">
        <el-button size="mini" type="primary">
          <a href="/backage-api/file/judge" style="color: white;text-decoration: none">
            下载判断题模板
          </a>
          <i class="el-icon-download el-icon--right"></i></el-button>
      </div>
    </el-upload>

    <!--    多选题上传-->
    <el-upload
      class="upload-demo3"
      drag
      action="backage-api/question/choice/file"
      :data="uploadQuestionType.multiple"
      :on-success="uploadSuccess"
      :on-error="uploadFail"
      multiple>
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将多选题文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只能上传xls/xlsx文件，且不超过500kb</div>
      <div class="el-upload__tip" slot="tip">
        <el-button size="mini" type="primary">
          <a href="/backage-api/file/multiple" style="color: white;text-decoration: none">
            下载模多选题板
          </a>
          <i class="el-icon-download el-icon--right"></i></el-button>
      </div>
    </el-upload>

    <!--    主观题上传-->
    <el-upload
      class="upload-demo4"
      drag
      action="backage-api/question/subjective/file"
      :data="uploadQuestionType.subjective"
      :on-success="uploadSuccess"
      :on-error="uploadFail"
      multiple>
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将主观题文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只能上传xls/xlsx文件，且不超过500kb</div>
      <div class="el-upload__tip" slot="tip">
        <el-button size="mini" type="primary">
          <a href="/backage-api/file/subjective" style="color: white;text-decoration: none">
            下载主观题模板
          </a>
          <i class="el-icon-download el-icon--right"></i></el-button>
      </div>
    </el-upload>
  </section>
</template>

<script>
  export default {
    name: "UploadQuestion",

    data() {
      return {
        //上传题目类型
        uploadQuestionType: {
          single: {type: '1'},
          judge: {type: '2'},
          multiple: {type: '3'},
          subjective: {type: '4'}
        },
      }
    },

    methods: {
      //上传成功后执行的方法
      uploadSuccess(res, file) {
        console.log('uploadSuccess:res::'+JSON.stringify(res))
        if (res['code'] == '0') {
          this.$alert('成功上传'+JSON.stringify(res['data'])+'道题目')
        } else {
          this.uploadFail(res,file)
        }
      },

      //上传失败后执行的方法
      uploadFail(res, file) {
        this.$alert(res['message'])
      }
    }


  }
</script>

<style scoped>
  .upload-demo1 {
    float: left;
    height: 20%;
    width: 20%;
    text-align: center;
    margin-left: 28%;
    margin-right: 4%;
    margin-top: 10%;
  }

  .upload-demo2 {
    float: left;
    height: 20%;
    width: 20%;
    margin-top: 10%;
    text-align: center;

  }

  .upload-demo3 {
    clear: left;
    float: left;
    width: 20%;
    text-align: center;
    margin-left: 28%;
    margin-right: 4%;
    margin-top: 3%;
  }

  .upload-demo4 {
    float: left;
    height: 20%;
    width: 20%;
    margin-top: 3%;
    text-align: center;
  }


</style>
