package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MenuVO;
import model.ReviewDAO;
import model.ReviewVO;
import model.StoreDAO;
import model.StoreVO;

public class DetailStoreController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// session 이 없다면 main.jsp로 돌아가겠습니당 ~ ㅋㅋㅋㅋ by 임경수
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("member") == null) {
			return "redirect:main.jsp";
		}

		String storeName = request.getParameter("storeName");
		// 강정호. storeName을 이용해서 store, menu 정보를 가져오는 메서드입니다

		StoreVO storeVO = StoreDAO.getInstance().getStoreMenuList(storeName);
		System.out.println(storeName);

		// menuList : list page 의 전체 식당 보여줌

		// 강정호. storeName을 이용해서 메뉴 사진 3개를 불러오는 메서드입니다
		ArrayList<MenuVO> menuImgList = StoreDAO.getInstance().getMenuImgByStoreName(storeName);

		request.setAttribute("menuList", storeVO);

		ArrayList<ReviewVO> reviewList = ReviewDAO.getInstance().getReviewList(storeName);
		// reviewList : 해당식당의 전체 review 보여줌
		request.setAttribute("reviewList", reviewList);
		// storeMenuName : reviewList 의 조건 설정의 menuName 보여줌

		request.setAttribute("menuImgList", menuImgList);
		
		//detailStore 에 딱 들어간다.
				//딱 보여지는 가게의 총 별점
		int totalAvg = new ReviewVO().calAvg(reviewList);
		request.setAttribute("totalAvg", totalAvg);
		return "/board/detailStore.jsp";
	}

}
