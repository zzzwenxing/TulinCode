/**
 * 商城Vuex-actions
 */
export default {
  saveToken(context,token){
    context.commit('saveToken',token);
  },
  saveUserName(context,username){
    context.commit('saveUserName', username);
  },
  saveCartCount(context, count) {
    context.commit('saveCartCount', count);
  },
  saveItemids(context,itemids){
    context.commit('saveitemids',itemids);
  }
}