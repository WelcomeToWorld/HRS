package orderBLImpl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import PO.OrderPO;
import VO.OrderStateVO;
import dataService.DataFactoryService;
import dataService.HotelDataService;
import dataService.OrderDataService;
import dataService.UserDataService;
import rmi.RemoteHelper;
/**
 * 订单状态信息的处理
 * @author Administrator
 *
 */
public class OrderState {
	private DataFactoryService DataFactory;
    private OrderDataService orderData;
    private HotelDataService hotelData;
    private UserDataService userData;
    private OrderPO order;
    private OrderStateVO state;
    public OrderState(String orderID) {
 	 try {   
 		 DataFactory=RemoteHelper.getInstance().getDataFactoryService();   
 	   //orderData= (OrderDataService) DataFactory.getDataService("Order");
 	   //userData= (UserDataService) DataFactory.getDataService("User");	  
		order=orderData.find(orderID);
 	   state=new OrderStateVO(order);
 	   } catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
	/**
     * 客户撤销订单(将订单状态改为已撤销，增加信用值请使用updateCredit方法)
     * 
     * @return boolean 如果需要扣除信用值则返回true，否则返回false
	 * @ 
     */
	public boolean cancel() {
		try {
		state.orderState=4;
		Calendar cal=state.latest;
		cal.add(Calendar.HOUR, -6);
		Calendar rightnow=Calendar.getInstance();
		state.Update();
		
			orderData.update(order);
		
		if(cal.compareTo(rightnow)==-1){
			return true;
		}
		else{
			return false;
		}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * 处理未执行订单(将订单状态改为已执行，增加信用值请使用updateCredit方法)
     * 
     * @return boolean值,处理成功返回true，否则返回false
	 * @ 
     * @see bussinesslogic.Order
     */
	public boolean processUnfinishedOrder()  {
		try {
		state.orderState=1;
		state.generationTime=Calendar.getInstance();
		state.Update();
		
			orderData.update(order);
		
		return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	/**
     * 酒店人员处理异常订单，延迟入住(将订单状态改为已 执行，增加信用值请使用updateCredit方法)
     * 
     * @param String delayTime,延时入住的时间
     * @return boolean值,处理成功返回true，否则返回false
	 * @ 
     * @see bussinesslogic.Order
     */
	public boolean processAbnormalOrder(Calendar delayTime)  {
		try {
		state.orderState=2;
		state.latest=delayTime;
		state.Update();
		
			orderData.update(order);
		
		return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * 网站营销人员撤销异常订单(将订单状态改为已执行，增加信用值请使用updateCredit方法)
     * 
	 * @ 
     * @see bussinesslogic.Order
     */
	public void cancelAbnormalOrder()  {
		state.orderState=1;
		state.Update();
		try {
			orderData.update(order);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}