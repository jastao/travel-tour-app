package jt.portfoilo.tourservice.web.rest.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Created by JT on 3/31/2019
 */
@Component
public class DateMapper {
	
	Timestamp asOffsetDateTime(OffsetDateTime offsetDateTime) {
		
		if(offsetDateTime != null) {
			
			return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
			
		} else {
			return null;
		}
	}
	
	OffsetDateTime asTimestamp(Timestamp timestamp) {
	
		if(timestamp != null) {
			
			return OffsetDateTime.of(timestamp.toLocalDateTime().getYear(),
									 timestamp.toLocalDateTime().getMonthValue(),
									 timestamp.toLocalDateTime().getDayOfMonth(),
									 timestamp.toLocalDateTime().getHour(),
									 timestamp.toLocalDateTime().getMinute(),
									 timestamp.toLocalDateTime().getSecond(),
									 timestamp.toLocalDateTime().getNano(),
			  						 ZoneOffset.UTC);
			
		} else {
			return null;
		}
	}
}
