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
export const getAllCourse = params =>{
  return axios.get(`backage-api/course/course`)
}

