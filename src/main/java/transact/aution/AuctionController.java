package transact.aution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aution/*")
public class AuctionController {
	@Autowired(required=false)
	private AuctionLogic auctionLogic = null;
	@Autowired(required=false)
	private ProductLogic productLogic = null;
	@Autowired(required=false)
	private SeedLogic seedLogic = null;
	
}
