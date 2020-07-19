package project.web.base;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/*", produces = "text/plain;charset=UTF-8")
public class RestBaseController {
	@GetMapping(value = "current_time")
	public String current_time(Locale locale) {
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		return dateFormat.format(new Date());
	}
}
