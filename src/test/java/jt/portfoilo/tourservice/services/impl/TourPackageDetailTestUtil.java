package jt.portfoilo.tourservice.services.impl;

import jt.portfoilo.tourservice.domain.*;
import jt.portfoilo.tourservice.web.rest.model.DailyTourDetailDTO;
import jt.portfoilo.tourservice.web.rest.model.PackagePricingDTO;
import jt.portfoilo.tourservice.web.rest.model.TourPackageDetailDTO;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

/**
 * Created by JT on 4/5/2019
 */
public final class TourPackageDetailTestUtil {
	
	public static final TourPackageDetailDTO createDTO(UUID fakeId) {
		
			TourPackageDetailDTO dto = new TourPackageDetailDTO();
			dto.setId(fakeId);
			dto.setVersion(1);
			dto.setCreatedDate(OffsetDateTime.of(0,12,1,0,0,0,0, ZoneOffset.UTC));
			dto.setLastModifiedDate(OffsetDateTime.of(0,12,1,0,0,0,0, ZoneOffset.UTC));
			dto.setTourPackageInfoId(UUID.randomUUID());
			dto.setDescription("test");
			dto.setTourTitle("test1");
			dto.setDuration(1);
			dto.setDepartureCity("test2");
			dto.setOfferStartDate(new Date());
			dto.setOfferEndDate(new Date());
			dto.setDepartureDateInWeek(DayOfWeek.THURSDAY);
			
			DailyTourDetailDTO dailyTourDetailDTO = new DailyTourDetailDTO();
			dailyTourDetailDTO.setId(UUID.randomUUID());
			dailyTourDetailDTO.setVersion(1);
			dailyTourDetailDTO.setCreatedDate(OffsetDateTime.of(0,12,1,0,0,0,0, ZoneOffset.UTC));
			dailyTourDetailDTO.setLastModifiedDate(OffsetDateTime.of(0,12,1,0,0,0,0, ZoneOffset.UTC));
			dailyTourDetailDTO.setTourPackageDetailId(fakeId);
			dailyTourDetailDTO.setDayOfTrip(2);
			dailyTourDetailDTO.setDescription("");
			
			dto.setDailyTourDetailDTOS(Arrays.asList(dailyTourDetailDTO));
			
			PackagePricingDTO packagePricingDTO = new PackagePricingDTO();
			packagePricingDTO.setId(UUID.randomUUID());
			packagePricingDTO.setVersion(1);
			packagePricingDTO.setCreatedDate(OffsetDateTime.now());
			packagePricingDTO.setLastModifiedDate(OffsetDateTime.now());
			packagePricingDTO.setTourPackageDetailId(fakeId);
			packagePricingDTO.setRoomType(RoomTypeEnum.FOUR_PER_ROOM);
			packagePricingDTO.setRoomPrice(400.0);
			
			dto.setPackagePricingDTOS(Arrays.asList(packagePricingDTO));
			return dto;
		}
	
	public static final TourPackageDetail create(UUID fakeId) {
		
		TourPackageDetail tpd = new TourPackageDetail();
		tpd.setId(fakeId);
		tpd.setVersion(1L);
		tpd.setCreatedDate(new Timestamp(new Date().getTime()));
		tpd.setLastModifiedDate(new Timestamp(new Date().getTime()));
		tpd.setTourPackageInfo(TourPackageInfo.builder().id(UUID.randomUUID()).tourCode("test1").cityOfInterest("Los Angeles").build());
		tpd.setDescription("test");
		tpd.setTourTitle("test1");
		tpd.setDuration(1);
		tpd.setDepartureCity("test2");
		tpd.setOfferStartDate(new Date());
		tpd.setOfferEndDate(new Date());
		tpd.setDepartureDateInWeek(DayOfWeek.THURSDAY);
		
		DailyTourDetail dailyTourDetail = new DailyTourDetail();
		dailyTourDetail.setId(UUID.randomUUID());
		dailyTourDetail.setVersion(1L);
		dailyTourDetail.setCreatedDate(Timestamp.from(Instant.ofEpochMilli(0)));
		dailyTourDetail.setLastModifiedDate(Timestamp.from(Instant.ofEpochMilli(0)));
		dailyTourDetail.setTourPackageDetail(tpd);
		dailyTourDetail.setDayOfTrip(2);
		dailyTourDetail.setDescription("");
		
		tpd.setDailyTourDetails(new HashSet<>(Arrays.asList(dailyTourDetail)));
		
		PackagePricing packagePricing = new PackagePricing();
		packagePricing.setId(UUID.randomUUID());
		packagePricing.setVersion(1L);
		packagePricing.setCreatedDate(new Timestamp(new Date().getTime()));
		packagePricing.setLastModifiedDate(new Timestamp(new Date().getTime()));
		packagePricing.setTourPackageDetail(tpd);
		packagePricing.setRoomType(RoomTypeEnum.FOUR_PER_ROOM);
		packagePricing.setRoomPrice(400.0);
		
		tpd.setPackagePricings(new HashSet<>(Arrays.asList(packagePricing)));
		return tpd;
	}

}
