package transact.aution;

import java.sql.Connection;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuctionLogic {
	@Autowired(required=false)
	private AuctionDao auctionDao = null;
	@Autowired(required=false)
	private SeedDao seedDao = null;
	@Autowired(required=false)
	private ProductDao productDao = null;
	Connection con = null;
	//@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=(DataAccessException.class))
	//@Pointcut(value="execution(* transaction.auction.*Logic.*(..))")
	public int methodA() {
		int result = 0;//1이면 업무처리완료 0이면 업무처리 실패
		//진입 바로 직전 - 자동트랜잭션 기능을 꺼두기
		try {
			
			con.setAutoCommit(false);
			int r1 = auctionDao.auctionINS();
			int r2 = seedDao.seedUPD();
			int r3 = productDao.productDEL();
			if(r1==0 && r2==0 && r3==0) {
				result = 1;
				con.commit();
			}
			else {
				con.rollback();
			}
		}catch(DataAccessException da) {
			throw da;
		}catch(Exception e) {
			
		}
		return result;
	}
}
