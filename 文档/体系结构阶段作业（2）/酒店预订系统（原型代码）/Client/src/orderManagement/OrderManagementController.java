package orderManagement;

import java.rmi.RemoteException;
import java.sql.Time;
import java.util.ArrayList;

import Object.Hotel;
import VO.HotelVO;
import VO.OrderVO;
import VO.UserVO;
import common.Choice;
import common.ResultMessage;
import common.UserType;
import orderBLService.OrderBLService_realize;
public class OrderManagementController extends OrderBLService_realize{
	/**
     * 根据选择的类型查找所有订单，并显示
     * 
     * @param type String型，一种订单的类型
     * @return ArrayList<OrderVO>，一个订单值对象的列表
     * @see bussinesslogic.Order
     */
	public ArrayList<OrderVO> findByType(String type){
		return new ArrayList<OrderVO>();
	}

	/**
     * 恢复客户信用值，更新会员等级
     * 
     * @param vo OrderVO型，订单的值对象
     * @param choice Choice型，一个枚举值
     * @see bussinesslogic.Order
     */
	public void regain(OrderVO vo,Choice choice){
		System.out.println("update success");
	}
	
}
