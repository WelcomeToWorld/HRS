package promotionWeb;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import PromotionBLServiceImpl.AddWebPromotion;
import PromotionBLServiceImpl.DeleteWebPromotion;
import PromotionBLServiceImpl.GetMemberLevelSystem;
import PromotionBLServiceImpl.SearchWebPromotion;
import VO.MemberLevelSystemVO;
import VO.WebPromotionVO;
import userBLServiceImpl.Credit;
public class PromotionWebController{
	/**
     * 添加一种新的网站营销策略，并公布
     * 
     * @param vo WebPromotionVO型，界面输入的营销策略
     * @see bussinesslogic.Promotion
     */
	public void madebyweb(String promotionnumber,String promotionname,Calendar promotionbegintime,Calendar promotionendtime,String applycity,String applybussinesscircle,int applymembergrade) {
        AddWebPromotion.getAddWebPromotionInstance().addWebPromotion(promotionnumber,promotionname,promotionbegintime,promotionendtime,applycity,applybussinesscircle,applymembergrade);
	} 
	/**
     * 得到所有的网站营销策略
     */
	public Vector<Vector<String>> getAllWebPromotion() {
		return  SearchWebPromotion.getSearchWebPromotionInstance().getAllWebPromotion();
	}
	/**
	 * 通过策略ID查找策略
     */
	public WebPromotionVO getWebPromotionByPromotionNumber(String promotionnumber) {
		return SearchWebPromotion.getSearchWebPromotionInstance().getWebPromotionByPromotionNumber(promotionnumber);
		
	}
	/**
	 * 得到在下订单时客户能获得的网站营销策略折扣
	 * @throws RemoteException 
     */
	public double getWebPromotionDiscount(String userID,String city,String bussinesscircle,Calendar orderbuildtime) throws RemoteException {
		return SearchWebPromotion.getSearchWebPromotionInstance().getWebPromotionDiscount(userID,city,bussinesscircle,orderbuildtime);
	
	}
	/**
	 * 根据策略编号删除策略
	 * @return 
     */
	public boolean deleteWebPromotion(String promotionnumber){
		return DeleteWebPromotion.getDeleteWebPromotionInstance().deleteWebPromotion(promotionnumber);
		
	}
}
