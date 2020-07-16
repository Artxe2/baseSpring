package mvc.board;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardLogic {
	Logger logger = LoggerFactory.getLogger(BoardLogic.class);
	private BoardDao boardDao = null;

	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Pointcut
	public List<Map<String, Object>> boardList() {
		logger.info("boardList 호출 성공");
		List<Map<String, Object>> list = null;
		list = boardDao.boardList();
		return list;
	}
}
