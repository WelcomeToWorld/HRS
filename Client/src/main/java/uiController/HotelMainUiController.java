package uiController;

import HotelWorkerView.BrowseOrderView;
import HotelWorkerView.HotelMainView;
import HotelWorkerView.MakeHotelPromotionView;
import HotelWorkerView.ProcessOrderView;
import HotelWorkerView.UpdateHotelInfoView;
import common.UserType;
import UserView.AdminRoomView;
import runner.ClientRunner;
import uiService.AdminRoomUiService;
import uiService.BrowseOrderUiService;
import uiService.HotelMainUiService;
import uiService.MakeHotelPromotionUiService;
import uiService.ProcessOrderUiService;
import uiService.UpdateHotelInfoUiService;

public class HotelMainUiController implements HotelMainUiService {

	private HotelMainView view;
	
	
	public void setView(HotelMainView view){
		this.view = view;
	}
	
	public void toLogView() {
		//跳转到初始登录界面
	}
	
	public void toUpdateHotelInfoView() {
		UpdateHotelInfoUiService controller = new UpdateHotelInfoUiController();
		UpdateHotelInfoView view = new UpdateHotelInfoView(controller);
		controller.setView(view);
		ClientRunner.change(view);
	}

	public void toAdminRoomView() {
		AdminRoomUiService controller = new AdminRoomUiController();
		AdminRoomView view = new AdminRoomView(controller);
		controller.setView(view);
		ClientRunner.change(view);
		
	}

	public void toMakeHotelPromotionView() {
		MakeHotelPromotionUiService controller = new MakeHotelPromotionController();
		MakeHotelPromotionView view = new MakeHotelPromotionView(controller);
		controller.setView(view);
		ClientRunner.change(view);
	}

	@Override
	public void toProcessOrderOrderView() {
		// TODO Auto-generated method stub
		ProcessOrderUiService controller=new ProcessOrderUiController(1,UserType.Hotelworker);//系统自动获取酒店工作人员账号，这里用1代替
		ProcessOrderView view=new ProcessOrderView(controller);
		view.disableCancel();
		controller.setView(view);
		ClientRunner.change(view);
	}

}