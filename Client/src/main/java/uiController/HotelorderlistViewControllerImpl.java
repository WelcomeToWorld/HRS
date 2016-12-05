package uiController;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import UserView.HotelorderlistView;
import VO.OrderVO;
import hotelBLService.HotelBLService;
import hotelBLService.HotelBLServiceController;
import uiService.HotelorderlistViewControllerService;

public class HotelorderlistViewControllerImpl implements HotelorderlistViewControllerService {
	private HotelorderlistView view;
	private HotelBLService hotel;
	String hotelid;
	String userid;
	public HotelorderlistViewControllerImpl(String HotelID,String UserID){
		hotelid=HotelID;
		userid=UserID;
		hotel=new HotelBLServiceController();
	}
	@Override
	public void setView(HotelorderlistView view) {
		this.view=view;
	}

	@Override
	public void exit() {
		view.exit();
	}

	@Override
	public List<OrderVO> getOrderList() {
		List<OrderVO> list=new ArrayList<OrderVO>();
		list=hotel.findByHotelID(userid, hotelid);
		for(OrderVO vo: list){
			vo.addorderNumber();
			vo.addorderState();
			vo.addnumOfPerson();
			vo.addorderValue();
			vo.addroomType();
			vo.addroomNumber();
			vo.addexpectedCheckIn();
			vo.addexpectedCheckOut();
		}
		return list;
	}
	@Override
	public String getUserID() {
		return userid;
	}
	@Override
	public String getHotelID() {
		return hotelid;
	}
    public void exit2(){
    	view.exit2();
    }
	@Override
	public void showDetail(String id) {
		try {
			view.showDetail(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
