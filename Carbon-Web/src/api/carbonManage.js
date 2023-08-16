import request from '@/utils/request'


export default {
  getCarbonList(searchModel){
    return request({
      url: '/sys/list',
      method: 'get',
      params:{
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        monitorName: searchModel.monitorName,
        monitorAddr: searchModel.monitorAddr
      }
    });
    
  },

  addCarbon(carbon){
    return request({
      url: '/sys',
      method: 'post',
      data: carbon
    });
  },

  getCarbonById(warningId){
    return request({
      url: `/sys/${warningId}`,
      method: 'get'
    });
  },
  saveCarbon(carbon){
    if(carbon.warningId == null && carbon.warningId == undefined){
      return this.addCarbon(carbon);
    }
    return this.updataCarbon(carbon);
  },
  updataCarbon(carbon){
    return request({
      url: '/sys',
      method: 'put',
      data: carbon
    });
  },
  deleteCarbonById(warningId){
    return request({
      url: `/sys/${warningId}`,
      method: 'delete'
    });
  }

}
