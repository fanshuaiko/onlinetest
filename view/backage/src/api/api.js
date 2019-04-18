import axios from 'axios';

let backage_base = 'backage-api/test';

//根据id删除考试
export const deleteTestByIds = params => {
  return axios.delete(`${backage_base}/test/` + params)
};

//根据教师编号分页查询考试
export const pageQueryTest = params => {
  return axios.get(`${backage_base}/test/` + params.pageNum + '/' + params.pageSize + '/' + params.username);
};

//查询所有课程
export const getAllCourse = params => {
  return axios.get(`backage-api/course/course`)
}

//新建考试
export const createTest = params => {
  return axios.post(`${backage_base}/test`, params)
}

//根据考试编号查询该场考试的试题
export const getQuestionByTestNo = params => {
  return axios.get(`${backage_base}/questionDetail/` + params)
}

//多条件分页查询选择题
export const choicePageQuery = params => {
  return axios.get(`backage-api/question/choice`, {params: params})
}

//多条件分页查询主观题
export const subjectivePageQuery = params => {
  return axios.get(`backage-api/question/subjective`, {params: params})
}

//修改选择题
export const updateChoice = params => {
  return axios.put(`backage-api/question/choice`, params)
}

//根据id删除当前选择题
export const deleteChoiceById = params => {
  return axios.delete(`backage-api/question/choice/` + params)
}

//分页查询PaperStatus
export const pageQueryPaperStatus = params => {
  return axios.get(`backage-api/paper/paperStatus/` + params.pageNum + '/' + params.pageSize)
}

//批改选择题
export const markChoice = params => {
  return axios.post(`backage-api/paper/choice/` + params)
}

//查询未批改的一道主观题
export const queryScoreDetail = params => {
  return axios.get(`backage-api/paper/scoreDetail/` + params)
}

//保存当前批改题目的得分
export const saveStudentScoreDetail = params => {
  return axios.post(`backage-api/paper/scoreDetail/` + params.id + '/' + params.score)
}

//模糊查询专业名称
export const likeQueryMajorName = params => {
  return axios.get(`backage-api/class/major/name`, {params: params})
}

//多条件分页查询分数
export const pageQueryStudentScore = params => {
  return axios.get(`backage-api/paper/score`, {params: params})
}

//根据多条件导出分数
export const exportScoreExcel = params => {
  return axios.get(`backage-api/file/score`, {params: params})
}

//获取考试分析数据
export const getTestAnalyzeData = params => {
  return axios.get(`backage-api/test/analyze/` + params)
}
