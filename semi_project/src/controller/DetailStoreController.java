package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberVO;
import model.MenuVO;
import model.PagingBean;
import model.ReviewDAO;
import model.ReviewListVO;
import model.ReviewVO;
//github.com/limkyoungsoo/semi_project.git
import model.StoreDAO;
import model.StoreVO;

public class DetailStoreController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// session 이 없다면 main.jsp로 돌아가겠습니당 ~ ㅋㅋㅋㅋ by 임경수
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("member") == null) {
			return "/board/detailShowFail.jsp";
		}
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member.getmGrant().equals("준회원")){
			return "/board/grantFail.jsp";
		}

		String storeName = request.getParameter("storeName");
		// 강정호. storeName을 이용해서 store, menu 정보를 가져오는 메서드입니다

		StoreVO storeVO = StoreDAO.getInstance().getStoreMenuList(storeName);
		System.out.println(storeName);

		// menuList : list page 의 전체 식당 보여줌

		// 강정호. storeName을 이용해서 메뉴 사진 3개를 불러오는 메서드입니다
		ArrayList<MenuVO> menuImgList = StoreDAO.getInstance().getMenuImgByStoreName(storeName);
		request.setAttribute("menuList", storeVO);

		int totalCount = ReviewDAO.getInstance().getTotalReivewCount(storeName);

		String pno = request.getParameter("pageNo");
		PagingBean pagingBean = null;
		if (pno == null) {
			pagingBean = new PagingBean(totalCount);
		} else {
			pagingBean = new PagingBean(totalCount, Integer.parseInt(pno));
		}

		ArrayList<ReviewVO> list = ReviewDAO.getInstance().getAllReviewList(pagingBean, storeName);
		ReviewListVO listVO = new ReviewListVO(list, pagingBean);
		request.setAttribute("rlistVO", listVO);

		ArrayList<ReviewVO> reviewList = ReviewDAO.getInstance().getReviewList(storeName);
		// reviewList : 해당식당의 전체 review 보여줌
		request.setAttribute("reviewList", reviewList);
		// storeMenuName : reviewList 의 조건 설정의 menuName 보여줌

		request.setAttribute("menuImgList", menuImgList);

		// detailStore 에 딱 들어간다.
		// 딱 보여지는 가게의 총 별점
		int totalAvg = new ReviewVO().calAvg(reviewList);
		System.out.println("총 평점 : " + totalAvg);
		request.setAttribute("totalAvg", totalAvg);
		return "/board/detailStore.jsp";
	}
}
