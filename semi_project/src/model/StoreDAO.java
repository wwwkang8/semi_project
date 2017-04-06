package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class StoreDAO {
	private static StoreDAO storeDAO = new StoreDAO();
	private DataSource dataSource;

	private StoreDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static StoreDAO getInstance() {
		return storeDAO;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public ArrayList<StoreVO> getStoreShowList() throws SQLException {
		ArrayList<StoreVO> storeList = new ArrayList<StoreVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			// select * from store;
			String sql = "select storeName, storeLoc, storePic from store";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeList.add(new StoreVO(rs.getString(1), rs.getString(2),rs.getString(3)));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return storeList;
	}

	// 강정호 제작. 가게 이름으로 가게 정보와 메뉴 정보를 불러오기 위해서 만듬
	public StoreVO getStoreMenuList(String storeName) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StoreVO storeVO=new StoreVO();
		MenuVO menuVO=new MenuVO();
		try{
			con=getConnection();
			String sql="select s.storeName, s.storeLoc, s.storeTel, s.storePic, s.openHour, "
					+ "m.menuNo, m.menuName, m.menuPrice, m.menuPic "
					+ "from store s, menu m "
					+ "where s.storeName=m.storeName and m.storeName=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, storeName);
			rs=pstmt.executeQuery();
			if(rs.next()){
				storeVO.setStoreName(rs.getString(1));
				storeVO.setStoreLoc(rs.getString(2));
				storeVO.setStoreTel(rs.getString(3));
				storeVO.setStorePic(rs.getString(4));
				storeVO.setOpenHour(rs.getString(5));
				//menuVO에 메뉴번호, 메뉴이름, 메뉴가격, 메뉴사진 저장 -강정호-
				menuVO.setMenuNo(rs.getInt(6));
				menuVO.setMenuName(rs.getString(7));
				menuVO.setMenuPrice(rs.getInt(8));
				menuVO.setMenuPic(rs.getString(9));
				// menuVO를 다시 storeVO에 저장한다. -강정호-
				storeVO.setMenuVO(menuVO);
			}
		}finally{
			closeAll(rs,pstmt,con);
		}
		return storeVO;
	}
	// 강정호 제작- 메뉴 번호를 이용해서 메뉴사진 3장을 가져오기 위한 메서드입니다.
	public StoreVO getMenuImgByMenuNo(int menuNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StoreVO storeVO=new StoreVO();
		MenuVO menuVO=new MenuVO();
		try{
			con=getConnection();
			String sql="select menuPic from menu where menuNo=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, menuNo);
			rs=pstmt.executeQuery();
			if(rs.next()){
				menuVO.setMenuPic(rs.getString(1));
				storeVO.setMenuVO(menuVO);
			}
		}finally{
			closeAll(rs, pstmt, con);
		}
		return storeVO;
	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);
	}
	// 페이징 을 위한 위치별 가게의 총 게시물을 구하는 sql -- 지원 
	public int getTotalContentCount(String storeLoc) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int totalCount=0;
		try{
			con=getConnection();
			String sql="select count(*) from store where storeLoc like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,"%"+storeLoc+"%");
			rs=pstmt.executeQuery();
			if(rs.next())
				totalCount=rs.getInt(1);
		}finally{
			closeAll(rs, pstmt, con);
		}
		return totalCount;
	}

	public ArrayList<StoreVO> getStoreList(PagingBean pagingBean, String loc) throws SQLException {
		ArrayList<StoreVO> list=new ArrayList<StoreVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT S.* FROM(");
			sql.append("SELECT row_number() over(order by storename asc) rnum,");
			sql.append("storename,storepic ");
			sql.append("from store where  storeLoc like ?) S ");
			sql.append("where rnum between ? and ?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+loc+"%");
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			System.out.println("startRowNum "+pagingBean.getStartRowNumber());
			System.out.println("endRowNum "+pagingBean.getEndRowNumber());
			rs=pstmt.executeQuery();	
			while(rs.next()){					
				StoreVO vo=new StoreVO();
				vo.setStoreName(rs.getString("storename"));
				vo.setStorePic(rs.getString("storepic"));
				list.add(vo);			
			}		
		}finally{
			closeAll(rs,pstmt,con);
		}
		return list;
	}


}
